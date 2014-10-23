package com.softserveinc.softtour.service;

public interface MailService {
    public void sendMail(String to, String from, String subject, String body, String nameFrom);
}
