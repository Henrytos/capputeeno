package com.cappuuteeno.notification.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SmsRequest(
        String sender,
        int ddd,
        int phoneNumber,
        String message
) {

    public SmsRequest(
            @JsonProperty("sender") String sender,
            @JsonProperty("ddd") int ddd,
            @JsonProperty("phoneNumber") int phoneNumber,
            @JsonProperty("message") String message) {
        this.sender = sender;
        this.ddd = ddd;
        this.phoneNumber = phoneNumber;
        this.message = message;
    }
}
