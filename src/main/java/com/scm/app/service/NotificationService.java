package com.scm.app.service;


import com.scm.app.model.response.Notification;

import com.scm.app.exception.ResourceNotFoundException;
import com.scm.app.repo.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService  {

    @Autowired
    private NotificationRepository repository;


    public Notification createNotification(Notification notification) {
        return repository.save(notification);
    }


    public Notification getNotificationByAccountId(Long accountId) {
        return repository.findByAccountId(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id " + accountId));
    }

    public List<Notification> getAllNotifications() {
        return repository.findAll();
    }

    public List<Notification> getNotificationsByRecipient(String recipient) {
        return repository.findByRecipient(recipient);
    }

    public Notification updateNotificationByAccountId(Long accountId, Notification updated) {
        Notification existing = getNotificationByAccountId(accountId);
        existing.setTitle(updated.getTitle());
        existing.setMessage(updated.getMessage());
        existing.setRecipient(updated.getRecipient());
        existing.setStatus(updated.getStatus());
        return repository.save(existing);
    }


    public void deleteNotificationByAccountId(Long accountId) {
        repository.deleteByAccountId(accountId);
    }
}


