package com.bitespeed.identity_reconciliation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequest {
    private String email;
    private String phoneNumber;
}
