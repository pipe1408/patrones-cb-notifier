package com.felipe.patronescbnotifier;

public record ProviderDTO(
        String provider,
        String code,
        String body,
        int retries) {}
