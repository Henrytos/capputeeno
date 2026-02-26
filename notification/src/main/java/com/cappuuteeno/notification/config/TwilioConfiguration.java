package com.cappuuteeno.notification.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfiguration {

    private final String TWILIO_ACCOUNT_SID;

    private final String TWILIO_AUTH_TOKEN;

    private final String TWILIO_MESSAGING_SERVICE_SID;

    public TwilioConfiguration(
            @Value("${twilio.account.sid}")
            String TWILIO_ACCOUNT_SID,
            @Value("${twilio.auth.token}")
            String TWILIO_AUTH_TOKEN,
            @Value("${twilio.menssaging.servicde.sid}")
            String TWILIO_MESSAGING_SERVICE_SID) {

        this.TWILIO_ACCOUNT_SID = TWILIO_ACCOUNT_SID;
        this.TWILIO_AUTH_TOKEN = TWILIO_AUTH_TOKEN;
        this.TWILIO_MESSAGING_SERVICE_SID = TWILIO_MESSAGING_SERVICE_SID;
    }

    public String getTWILIO_ACCOUNT_SID() {
        return TWILIO_ACCOUNT_SID;
    }

    public String getTWILIO_AUTH_TOKEN() {
        return TWILIO_AUTH_TOKEN;
    }

    public String getTWILIO_MESSAGING_SERVICE_SID() {
        return TWILIO_MESSAGING_SERVICE_SID;
    }
}
