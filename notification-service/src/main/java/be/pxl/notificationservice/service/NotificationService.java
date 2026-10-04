package be.pxl.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public Notification add(Notification notification) {
        return notificationRepository.save(notification);
    }
}
