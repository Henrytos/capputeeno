package br.com.capputeeno.auth.application.port.out;

public interface IPasswordEncoder {

    String encode(String password);

    boolean matches(String password, String encodedPassword);

}
