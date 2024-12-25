package com.sgwb.saigonwaterbus.Service;

import com.google.zxing.WriterException;
import com.sgwb.saigonwaterbus.Model.Email;
import com.sgwb.saigonwaterbus.Model.Trip;
import jakarta.mail.MessagingException;

import java.io.IOException;


public interface MailService {
    void  sendMailPayment(Email email) throws MessagingException, IOException, WriterException;
    void sendMailCodeVerify(String email,String code) throws MessagingException, IOException, WriterException;
    void sendMailCodeForgotPass(String email, String code) throws MessagingException,IOException, WriterException ;
    void sendMailBuyTicket(Trip trip, String name, String seat,String email) throws MessagingException, IOException, WriterException ;
}
