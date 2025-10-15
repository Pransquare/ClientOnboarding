package com.prannexus.clientonboarding.service;
public interface EmailService {
    void sendEmail(String to, String subject, String body);
}
