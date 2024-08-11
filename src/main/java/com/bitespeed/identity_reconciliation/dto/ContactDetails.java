package com.bitespeed.identity_reconciliation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContactDetails {
    private Long primaryContactId;
    private List<String> emails;
    private List<String> phoneNumbers;
    private List<Long> secondaryContactIds;

    public ContactDetails(Long primaryContactId, List<String> emails, List<String> phoneNumbers, List<Long> secondaryContactIds) {
        this.primaryContactId = primaryContactId;
        this.emails = emails;
        this.phoneNumbers = phoneNumbers;
        this.secondaryContactIds = secondaryContactIds;
    }

}