package com.scm.app.service;


import com.scm.app.model.Notification;

import com.scm.app.exception.ResourceNotFoundException;
import com.scm.app.model.User;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import com.scm.app.repo.NotificationRepository;
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

    public PaginatedResponse<Notification> getAll(PaginationRequest request, Integer accountId) {

        Sort sort = request.getSortDir().equalsIgnoreCase("asc") ? Sort.by(request.getSortDir()).ascending() : Sort.by(request.getSortBy()).descending();
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


