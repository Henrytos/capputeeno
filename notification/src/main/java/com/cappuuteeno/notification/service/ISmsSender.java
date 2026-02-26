package com.cappuuteeno.notification.service;

import com.cappuuteeno.notification.dto.SmsRequest;

public interface ISmsSender {
    void sendSms(SmsRequest request);
}
