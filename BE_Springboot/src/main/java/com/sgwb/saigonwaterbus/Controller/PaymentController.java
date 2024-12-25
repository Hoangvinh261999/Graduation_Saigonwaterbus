package com.sgwb.saigonwaterbus.Controller;

import com.sgwb.saigonwaterbus.Model.DTO.AuthenDTO.PaymentRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/saigonwaterbus/payment")
public class PaymentController {
    @Value("${vnpay.tmncode}")
    private String vnp_TmnCode;
    @Value("${vnpay.hashsecret}")
    private String vnp_HashSecret;
    @Value("${vnpay.payurl}")
    private String vnp_PayUrl;
    @PostMapping("/vnpay")
    public String generateVnpayPaymentUrl(@RequestBody PaymentRequest paymentRequest) {
        try {
            SortedMap<String, String> params = new TreeMap<>();
            params.put("vnp_Version", "2.1.0");
            params.put("vnp_Command", "pay");
            params.put("vnp_TmnCode", vnp_TmnCode);
            params.put("vnp_Amount", String.valueOf(paymentRequest.getAmount() * 100));
            params.put("vnp_CurrCode", "VND");
            params.put("vnp_TxnRef", paymentRequest.getOrderId());
            params.put("vnp_OrderInfo", "Thanh toán đơn hàng " + paymentRequest.getOrderId());
            params.put("vnp_OrderType", "billpayment");
            params.put("vnp_Locale", "vn");
            params.put("vnp_ReturnUrl", paymentRequest.getReturnUrl());
            params.put("vnp_IpAddr", "127.0.0.1");
            params.put("vnp_CreateDate", new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            String queryUrl = buildQueryString(params);
            String secureHash = hmacSHA512(vnp_HashSecret, queryUrl);
            String paymentUrl = vnp_PayUrl + "?" + queryUrl + "&vnp_SecureHash=" + secureHash;
            return paymentUrl;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping("/vnpay/return")
    public String handleVnpayReturn(@RequestParam("vnp_ResponseCode") String responseCode) {
        if ("00".equals(responseCode)) {
            return "<script>window.opener.postMessage('payment_success', '*');window.close();window.location.href = 'http://localhost:3000';</script>";
        } else {
            return "<script>window.opener.postMessage('payment_failed', '*');window.close();window.location.href = 'http://localhost:3000';</script>";
        }
    }

    private String buildQueryString(SortedMap<String, String> params) throws Exception {
        StringBuilder query = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (query.length() > 0) {
                query.append("&");
            }
            query.append(URLEncoder.encode(entry.getKey(), StandardCharsets.US_ASCII.toString()))
                    .append("=")
                    .append(URLEncoder.encode(entry.getValue(), StandardCharsets.US_ASCII.toString()));
        }
        return query.toString();
    }

    private String hmacSHA512(String key, String data) throws Exception {
        Mac hmacSHA512 = Mac.getInstance("HmacSHA512");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        hmacSHA512.init(secretKey);
        byte[] hash = hmacSHA512.doFinal(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder hashHex = new StringBuilder();
        for (byte b : hash) {
            hashHex.append(String.format("%02x", b));
        }
        return hashHex.toString();
    }
}
