package com.sgwb.saigonwaterbus.Controller;

import com.google.zxing.WriterException;
import com.sgwb.saigonwaterbus.Dao.AccountDao;
import com.sgwb.saigonwaterbus.Model.DTO.ApiResponse;
import com.sgwb.saigonwaterbus.Model.Email;
import com.sgwb.saigonwaterbus.Model.EmailCode.EmailCodeStore;
import com.sgwb.saigonwaterbus.Service.MailService;
import com.sgwb.saigonwaterbus.Util.RandomCodeGmail;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class MailController {
    @Autowired
    private MailService emailService;
    @Autowired
    AccountDao accountDao;
    @CrossOrigin(origins = "*")
    @PostMapping("/api/saigonwaterbus/send-mail")
    private ApiResponse<Email> mailSend(@RequestBody Email email){
        try {
            emailService.sendMailPayment(email);
            return ApiResponse.<Email>builder()
                    .code(1002)
                    .message("done")
                    .result(email)
                    .build();
        }
        catch (Exception exception){
        }
        return null;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/api/saigonwaterbus/send-mail-code")
    private ApiResponse<Email> gmailVerifyCode(@RequestParam String email){
        if(accountDao.findByEmail(email)!=null){
            return ApiResponse.<Email>builder()
                    .code(1006)
                    .message("Gmail này đã được đăng ký!")
                    .result(null)
                    .build();
        }
        try {
            String codeVerify=RandomCodeGmail.generateRandomCode();
            emailService.sendMailCodeVerify(email,codeVerify);
            EmailCodeStore.addCode(email,codeVerify);
            return ApiResponse.<Email>builder()
                    .code(200)
                    .message("done")
                    .result(null)
                    .build();
        }
        catch (Exception exception){
        }
        return null;
    }
    @CrossOrigin("*")
    @GetMapping("/api/saigonwaterbus/send-mailcode-forgetpass")
    private ApiResponse<String> gmailForgetPassCode(@RequestParam String email) throws IOException, MessagingException, WriterException {
        ApiResponse<String> apiResponse= new ApiResponse<>();
        if(accountDao.findByEmail(email)==null){
            apiResponse.setCode(1006);
            apiResponse.setMessage("Không tồn tại tài khoản với email này!");
            apiResponse.setResult(null);
        }else{
          String codeVerify=RandomCodeGmail.generateRandomCode();
          emailService.sendMailCodeVerify(email,codeVerify);
           EmailCodeStore.addCode(email,codeVerify);
           apiResponse.setCode(200)  ;
           apiResponse.setMessage(("Đã gửi mã xác nhận"));
           apiResponse.setResult(null);
        }
        return apiResponse;
    }
}