package br.com.capputeeno.payment.dtos;

public record Payer(
        String email,
    String firstName,
    String lastName,
    Identification identificationType

) {

    public record Identification(
        String type,
        String number   
    ){}


}
