package be.pxl.notificationservice.service;

import be.pxl.notificationservice.domain.Notification;
import be.pxl.notificationservice.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTests {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    @Test
    void add_shouldSaveNotification() {
        Notification notification = new Notification();

        when(notificationRepository.save(notification))
                .thenReturn(notification);

        Notification result = notificationService.add(notification);

        ArgumentCaptor<Notification> captor =
                ArgumentCaptor.forClass(Notification.class);

        verify(notificationRepository).save(captor.capture());

        assertThat(captor.getValue()).isSameAs(notification);
        assertThat(result).isSameAs(notification);
    }
}