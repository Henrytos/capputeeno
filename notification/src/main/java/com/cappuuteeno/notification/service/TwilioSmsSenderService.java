package com.cappuuteeno.notification.service;

import com.cappuuteeno.notification.config.TwilioConfiguration;
import com.cappuuteeno.notification.dto.SmsRequest;
import org.springframework.stereotype.Service;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

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

        String phoneNumber = "+55" + request.ddd() + request.phoneNumber(); // +55 11 xxxxxxxxx

        if (isValid(phoneNumber)) {
            Message message = Message.creator(
                            new PhoneNumber(phoneNumber),
                            twilioConfiguration.getTWILIO_MESSAGING_SERVICE_SID(),
                            from.concat(request.message()))
                    .create();
        } else {
            throw new IllegalArgumentException("Invalid phone NUmber" + phoneNumber);
        }

    }

    private boolean isValid(String fullPhoneNUmber) {
        // TODO

        return true;
    }
}
