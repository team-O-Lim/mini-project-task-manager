package org.example.o_lim.service.Impl;

import org.example.o_lim.dto.mail.request.SendMailRequestDto;
import org.example.o_lim.service.MailService;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    @Override
    public void sendEmail(SendMailRequestDto request) {

    }

    @Override
    public void verifyEmail(String token) {

    }
}
