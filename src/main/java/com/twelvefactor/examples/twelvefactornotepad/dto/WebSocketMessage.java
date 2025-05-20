package com.twelvefactor.examples.twelvefactornotepad.dto;

public record WebSocketMessage(String action, Object payload, String processedByHost) {}
