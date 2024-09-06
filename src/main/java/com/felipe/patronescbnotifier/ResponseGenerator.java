package com.felipe.patronescbnotifier;

public class ResponseGenerator {

    public static ResponseDTO generateResponseDTO(ProviderDTO providerDTO, String requestTime) {
        String result = "❌";

        if (!"ERR".equals(providerDTO.code())) {
            result = "✅";
        }
        return new ResponseDTO(result, providerDTO.code(), providerDTO.provider(), requestTime, providerDTO.body(), providerDTO.retries());
    }
}
