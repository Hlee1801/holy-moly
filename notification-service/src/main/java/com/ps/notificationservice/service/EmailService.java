package com.ps.notificationservice.service;

import com.ps.notificationservice.event.OrderPlacedEvent;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendOrderEmail(OrderPlacedEvent event) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("hello@demomailtrap.co");
            helper.setTo("hoang.leve1801@gmail.com");
            helper.setSubject("🛒 thông tin tài khoản");

            String body = "số dư : Chào anh Hoàng đẹp trai, and đã có 10 tỷ tình iu đến từ ngọc anh ❤️  ";

            helper.setText(body, true);

            mailSender.send(message);
            System.out.println("📧 Gửi email thành công!");

        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            System.err.println("❌ Gửi email thất bại: " + e.getMessage());
        }
    }
}