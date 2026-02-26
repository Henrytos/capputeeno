package com.cappuuteeno.notification.service;

import com.cappuuteeno.notification.config.TwilioConfiguration;
import com.cappuuteeno.notification.dto.SmsRequest;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;
import com.twilio.rest.api.v2010.account.Message;

@Service("twilio")
public class TwilioSmsSenderService implements ISmsSender {

    private final TwilioConfiguration twilioConfiguration;

    public TwilioSmsSenderService(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    public void sendSms(SmsRequest request) {
        String from = """
                   De: %s Para : %s
                """.formatted(request.sender(), request.phoneNumber());

        PhoneNumber phoneNumber = new PhoneNumber("+55"+request.ddd()+request.phoneNumber()); // +55 11 xxxxxxxxx

        Message message = Message.creator(
                        phoneNumber,
                        twilioConfiguration.getTWILIO_ACCOUNT_SID(),
                        from.concat(request.message()))
                .create();
    }
}
