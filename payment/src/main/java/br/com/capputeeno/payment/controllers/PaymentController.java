package br.com.capputeeno.payment.controllers;

import br.com.capputeeno.payment.dtos.CreateReferenceRequestDTO;
import br.com.capputeeno.payment.dtos.CreateReferenceResponseDTO;
import br.com.capputeeno.payment.services.CreatePreferenceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
@Slf4j
public class
PaymentController {

    private final CreatePreferenceService createPreferenceService;

    @GetMapping
    public String hello(){
        return "hello";
    }

    @PostMapping
    public ResponseEntity<CreateReferenceResponseDTO> createPreference(
            @Valid @RequestBody CreateReferenceRequestDTO request
            ){

        CreateReferenceResponseDTO response =  createPreferenceService.execute(request);

        return ResponseEntity.ok(response);
    }

}
