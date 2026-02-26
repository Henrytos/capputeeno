package com.cappuuteeno.notification.controller;

import com.cappuuteeno.notification.dto.SmsRequest;
import com.cappuuteeno.notification.service.ISmsSender;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SmsSenderController {

    private final ISmsSender iSmsSender;

    public SmsSenderController(@Qualifier("twilio") ISmsSender iSmsSender) {
        this.iSmsSender = iSmsSender;
    }

    @PostMapping("/send-sms")
    public void sendSms(
            @RequestBody SmsRequest request
            ){
        this.iSmsSender.sendSms(request);
    }

}
