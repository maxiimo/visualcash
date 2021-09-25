package com.backend.visualcash.email.dto;

public class EmailValuesDTO {

    private String mailFrom;    
    private String mailTo;
    private String subject;
    private String content;

    public EmailValuesDTO() {
    }

    public EmailValuesDTO(String mailFrom, String mailTo, String subject, String content) {
        this.mailFrom = mailFrom;
        this.mailTo = mailTo;
        this.subject = subject;
        this.content = content;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) { this.mailTo = mailTo; }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }
}
