package com.kirana.register.response;

import java.time.LocalDateTime;
import java.util.Map;

public class CurrencyApiResponse {
    private boolean success;
    private String terms;
    private String privacy;
    private long timestamp;
    private LocalDateTime date;
    private String base;
    public Map<String, Double> rates;

    // getters and setters
}
