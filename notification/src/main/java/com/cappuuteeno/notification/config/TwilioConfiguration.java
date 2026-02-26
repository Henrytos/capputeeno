package com.cappuuteeno.notification.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfiguration {

    private final String TWILIO_ACCOUNT_SID;

    private final String TWILIO_AUTH_TOKEN;


    public TwilioConfiguration(
            @Value("${twilio.account.sid}")
            String TWILIO_ACCOUNT_SID,
            @Value("${twilio.auth.token}")
            String TWILIO_AUTH_TOKEN) {
        this.TWILIO_ACCOUNT_SID = TWILIO_ACCOUNT_SID;
        this.TWILIO_AUTH_TOKEN = TWILIO_AUTH_TOKEN;
    }

    public String getTWILIO_ACCOUNT_SID() {
        return TWILIO_ACCOUNT_SID;
    }

    public String getTWILIO_AUTH_TOKEN() {
        return TWILIO_AUTH_TOKEN;
    }
}
