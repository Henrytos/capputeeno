package com.cappuuteeno.notification.config;

import com.twilio.Twilio;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class SenderInitializer {

    private static Logger logger = Logger.getLogger(SenderInitializer.class.getName());

    public SenderInitializer(TwilioConfiguration senderConfiguration) {
        Twilio.init(
                senderConfiguration.getTWILIO_ACCOUNT_SID(),
                senderConfiguration.getTWILIO_AUTH_TOKEN()
        );

        logger.info("config do twilio feito com sucesso!: ");
    }
}
