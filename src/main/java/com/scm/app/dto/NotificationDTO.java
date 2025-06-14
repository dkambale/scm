package com.scm.app.dto;


import java.time.LocalDateTime;

public class NotificationDTO {

    private Long accountId;
    private String title;
    private String message;
    private String recipient;
    private String status;

    public NotificationDTO(Long accountId, String title, String message, String recipient, String status) {
        this.accountId = accountId;
        this.title = title;
        this.message = message;
        this.recipient = recipient;
        this.status = status;
    }

    public NotificationDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}

