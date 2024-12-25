package com.sgwb.saigonwaterbus.Service.ServiceImpl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sgwb.saigonwaterbus.Dao.AccountDao;
import com.sgwb.saigonwaterbus.Model.Account;
import com.sgwb.saigonwaterbus.Model.Email;
import com.sgwb.saigonwaterbus.Model.Trip;
import com.sgwb.saigonwaterbus.Service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender mailSender;
    @Autowired
    AccountDao accountDao;
    @Override
    public void sendMailPayment(Email email) throws MessagingException, IOException, WriterException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email.getTo());
        helper.setSubject(email.getSubject());
        helper.setText(email.getBody(), true);

        // Tạo QR code từ nội dung cần mã hóa
        byte[] qrCodeBytes = generateQRCodeBytes(email.getContentForQR());
        String contentId = "qrCodeImage"; // ID của ảnh, sẽ được sử dụng trong thẻ <img> của email
        helper.addInline(contentId, new ByteArrayResource(qrCodeBytes), "image/png");

        mailSender.send(message);
    }

    @Override
    public void sendMailCodeVerify(String email, String code) throws MessagingException, IOException, WriterException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        String body = "<div style=\"font-family: Arial, sans-serif; color: #333; padding: 20px; background-color: #87CEEB;\">\n"
                + "    <img src=\"https://saigonwaterbus.com/wp-content/uploads/2022/06/logo-swb-v-white.png\" alt=\"\" style=\"width: 200px; height: auto; margin-bottom: 20px; display: block; margin-left: auto; margin-right: auto;\">\n"
                + "    <h2 style=\"color: #007bff; margin-bottom: 20px; font-size: 24px;\">Quên mật khẩu Saigonwaterbus</h2>\n"
                + "    <p style=\"font-size: 18px;\">Mã xác nhận đổi mật khẩu của bạn là: <strong>" + code + "</strong></p>\n"
                + "    <hr style=\"border-top: 1px solid #ddd; margin-top: 20px; margin-bottom: 20px;\">\n"
                + "    <p style=\"font-size: 16px; color: #FF3300;\">Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi. Vui lòng giữ mã này lại khi cần.</p>\n"
                + "</div>";
        helper.setTo(email);
        helper.setSubject("Mã xác nhận đăng ký tài khoản");
        helper.setText(body, true);
        mailSender.send(message);
    }
    @Override
    public void sendMailBuyTicket(Trip trip, String name, String seat,String email) throws MessagingException, IOException, WriterException {
        try {
            LocalDate departureDate = trip.getDepartureDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDepartureDate = departureDate.format(formatter);
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            String body = "<div style=\"font-family: Arial, sans-serif; color: #333; padding: 20px; background-color: #87CEEB;\">\n"
                    + "    <img src=\"https://saigonwaterbus.com/wp-content/uploads/2022/06/logo-swb-v-white.png\" alt=\"\" style=\"width: 200px; height: auto; margin-bottom: 20px; display: block; margin-left: auto; margin-right: auto;\">\n"
                    + "    <h2 style=\"color: #007bff; margin-bottom: 20px; font-size: 24px;\">Thông tin vé Saigonwaterbus</h2>\n"
                    + "    <p style=\"font-size: 18px;\"><strong>Kính gửi quý khách hàng:</strong> " + name + "</p>\n"
                    + "    <p style=\"font-size: 18px;\"><strong>Thời gian khởi hành:</strong> " + trip.getDepartureTime() +" - "+ formattedDepartureDate+"</p>\n"
                    + "    <p style=\"font-size: 18px;\"><strong>Tên chuyến:</strong> " + trip.getRoute().getNameRoute() + "</p>\n"
                    + "    <p style=\"font-size: 18px;\"><strong>Bến khởi hành - kết thúc:</strong> " + trip.getRoute().getFromTerminal().getName()+ " - " + trip.getRoute().getToTerminal().getName()+ "</p>\n"
                    + "    <p style=\"font-size: 18px;\"><strong>Số ghế đã đặt:</strong> " + seat + "</p>\n"
                    + "    <hr style=\"border-top: 1px solid #ddd; margin-top: 20px; margin-bottom: 20px;\">\n"
                    + "    <p style=\"font-size: 16px; color: #FF3300;\">Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi. Vui lòng giữ mã QR này lại khi tới bến.</p>\n"
                    + "</div>";
            String content = "Chuyến: " + trip.getRoute().getNameRoute() +
                    "\nKhách hàng: " + name +
                    "\nGhế đặt: " + seat +
                    "\nThời gian khởi hành: " + trip.getDepartureTime() + " " + trip.getDepartureDate();

            byte[] qrCodeBytes = generateQRCodeBytes(content);
            String contentId = "qrCodeImage";
            helper.setTo(email);
            helper.setSubject("Thông tin đặt vé");
            helper.setText(body, true);
            helper.addInline(contentId, new ByteArrayResource(qrCodeBytes), "image/png");
            mailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



@Override
    public void sendMailCodeForgotPass(String email, String code) throws MessagingException, IOException, WriterException {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        String body = "<div style=\"font-family: Arial, sans-serif; color: #333; padding: 20px; background-color: #87CEEB;\">\n"
                + "    <img src=\"https://saigonwaterbus.com/wp-content/uploads/2022/06/logo-swb-v-white.png\" alt=\"\" style=\"width: 200px; height: auto; margin-bottom: 20px; display: block; margin-left: auto; margin-right: auto;\">\n"
                + "    <h2 style=\"color: #007bff; margin-bottom: 20px; font-size: 24px;\">Đăng ký tài khoản Saigonwaterbus</h2>\n"
                + "    <p style=\"font-size: 18px;\">Mã xác nhận của bạn là: <strong>" + code + "</strong></p>\n"
                + "    <hr style=\"border-top: 1px solid #ddd; margin-top: 20px; margin-bottom: 20px;\">\n"
                + "    <p style=\"font-size: 16px; color: #FF3300;\">Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi. Vui lòng giữ mã này lại khi cần.</p>\n"
                + "</div>";
        helper.setTo(email);
        helper.setSubject("Mã xác nhận đăng quên mật khẩu");
        helper.setText(body, true);

        mailSender.send(message);
    }



    private byte[] generateQRCodeBytes(String text){
        Charset charset = StandardCharsets.UTF_8;
        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        hints.put(EncodeHintType.CHARACTER_SET, charset.name());

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 250, 250, hints);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();

    }
}
