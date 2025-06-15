package com.scm.app.controller;


import com.scm.app.model.Notification;
import com.scm.app.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService service;

    @PostMapping
    public Notification create(@RequestBody Notification notification) {
        return service.createNotification(notification);
    }

    @GetMapping("/{accountId}")
    public Notification getById(@PathVariable Long accountId) {
        return service.getNotificationByAccountId(accountId);
    }

    @GetMapping("/getAll")
    public List<Notification> getAll() {
        return service.getAllNotifications();
    }

    @GetMapping("/recipient/{recipient}")
    public List<Notification> getByRecipient(@PathVariable String recipient) {
        return service.getNotificationsByRecipient(recipient);
    }

    @PutMapping("/update/{accountId}")
    public Notification update(@PathVariable Long accountId, @RequestBody Notification notification) {
        return service.updateNotificationByAccountId(accountId, notification);
    }

    @DeleteMapping("/delete/{accountId}")
    public void delete(@PathVariable Long accountId) {
        service.deleteNotificationByAccountId(accountId);
    }
}

