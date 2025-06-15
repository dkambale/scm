package com.scm.app.repo;


import com.scm.app.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Optional<Notification> findByAccountId(Long accountId);
    void deleteByAccountId(Long accountId);
    List<Notification> findByRecipient(String recipient);
}

