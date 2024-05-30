package com.bitespeed.identity_reconciliation.controller;

import com.bitespeed.identity_reconciliation.dto.ContactRequest;
import com.bitespeed.identity_reconciliation.dto.ContactResponse;
import com.bitespeed.identity_reconciliation.service.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/identify")
    public ResponseEntity<ContactResponse> identifyContact(@RequestBody ContactRequest request) {
        ContactResponse response = contactService.identifyContact(request.getEmail(), request.getPhoneNumber());
        return ResponseEntity.ok(response);
    }
}
