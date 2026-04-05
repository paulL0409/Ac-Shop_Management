package com.acShop.service;

public interface RateLimitService {
    boolean tryConsumeLoginToken(String clientKey);
}
