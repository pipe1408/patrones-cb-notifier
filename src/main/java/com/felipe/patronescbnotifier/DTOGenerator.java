package com.felipe.patronescbnotifier;

public class DTOGenerator {

    public static ResponseDTO generateResponseDTO(ProviderDTO providerDTO, String requestTime) {
        String result = "❌";

        if (!"ERROR".equals(providerDTO.code())) {
            result = "✅";
        }
        return new ResponseDTO(result, providerDTO.code(), providerDTO.provider(), requestTime, providerDTO.body(), providerDTO.retries());
    }

    public static ProviderDTO generateProviderDTO(String provider, String code, String body, int retries) {
        return new ProviderDTO(provider, code, body, retries);
    }

    public static ResultDTO generateResultDTO(boolean successful, ProviderDTO providerDTO) {
        return new ResultDTO(successful, providerDTO);
    }

}
