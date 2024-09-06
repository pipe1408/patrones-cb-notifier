package com.felipe.patronescbnotifier;

public record ResponseDTO(
        String result,
        String provider,
        String timeReceived,
        String timeResponded,
        String retries) {}