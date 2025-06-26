package com.scm.app.service;


import com.scm.app.model.Assignment;
import com.scm.app.model.Course;
import com.scm.app.model.Notification;

import com.scm.app.exception.ResourceNotFoundException;
import com.scm.app.model.User;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import com.scm.app.repo.NotificationRepository;
import com.scm.app.util.AuditLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService  {

    @Autowired
    private NotificationRepository repository;

    @Autowired
    private AuditLogger auditLogger;

    public Notification createNotification(Notification notification) {


        Notification saved = repository.save(notification);

        //  Audit Log for CREATE
        auditLogger.logAction(
                notification.getAccountId(),
                saved.getId(),
                "Notification",
                "Create",
                notification.getCreatedBy()
        );
        return saved;
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
        Notification updatedd = repository.save(existing);

        //  Audit Log for UPDATE
        auditLogger.logAction(
                existing.getAccountId(),
                updatedd.getId(),
                "Notification",
                "Edit",
               existing.getCreatedBy()
        );

        return updatedd;
    }


    public void deleteNotificationByAccountId(Long accountId) {
        Notification existing = repository.findById(accountId).orElse(null);
        if (existing != null) {
           repository.deleteById(accountId);

            //  Audit Log for DELETE
            auditLogger.logAction(
                    existing.getAccountId(),
                    existing.getId(),
                    "Notification",
                    "Delete",
                    existing.getCreatedBy()
            );
        }
    }

    public PaginatedResponse<Notification> getAll(PaginationRequest request, Integer accountId) {

        Sort sort = request.getSortDir().equalsIgnoreCase("asc") ? Sort.by(request.getSortBy()).ascending() : Sort.by(request.getSortBy()).descending();
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        Page<Notification> userPage =null;
        if(request.getSearch()!= null && request.getSearch().isEmpty()) {
            userPage = repository.findByMessageContainingAndAccountId(request.getSearch(),accountId, pageable);
        } else {
            userPage =repository.findByAccountId(accountId,pageable);
        }

        PaginatedResponse<Notification> response = new PaginatedResponse<>();
        response.setContent(userPage.getContent());
        response.setPageNumber(userPage.getNumber());
        response.setPageSize(userPage.getSize());
        response.setTotalElements(userPage.getTotalElements());
        response.setTotalPages(userPage.getTotalPages());
        response.setLastPage(userPage.isLast());

        return response;
    }
}


