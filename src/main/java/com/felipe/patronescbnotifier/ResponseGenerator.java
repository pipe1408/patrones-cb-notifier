package com.felipe.patronescbnotifier;

public class ResponseGenerator {

    public static ResponseDTO generateResponseDTO(ProviderDTO providerDTO, String requestTime) {
        if (!"-".equals(providerDTO.body())) {
            return new ResponseDTO("✅", providerDTO.code(), providerDTO.provider(), requestTime, providerDTO.body(), providerDTO.retries());
        }
        return new ResponseDTO("❌", providerDTO.code(), providerDTO.provider(), requestTime, providerDTO.body(), providerDTO.retries());
    }
}
