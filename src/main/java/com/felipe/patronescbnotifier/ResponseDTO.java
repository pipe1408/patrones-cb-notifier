package com.felipe.patronescbnotifier;

public record ResponseDTO(
        String result,
        String code,
        String provider,
        String timeReceived,
        String timeResponded) {}