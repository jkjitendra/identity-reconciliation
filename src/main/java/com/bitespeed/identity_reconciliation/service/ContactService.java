package com.bitespeed.identity_reconciliation.service;

import com.bitespeed.identity_reconciliation.dto.ContactDetails;
import com.bitespeed.identity_reconciliation.dto.ContactResponse;
import com.bitespeed.identity_reconciliation.entity.Contact;
import com.bitespeed.identity_reconciliation.entity.LinkPrecedence;
import com.bitespeed.identity_reconciliation.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ContactResponse identifyContact(String email, String phoneNumber) {
        Optional<Contact> existingContact = findExistingContact(email, phoneNumber);
        if (existingContact.isPresent()) {
            Contact primaryContact = findPrimaryContact(existingContact.get());
            return buildResponse(primaryContact);
        } else {
            Contact newContact = createPrimaryContact(email, phoneNumber);
            return buildResponse(newContact);
        }
    }

    private Optional<Contact> findExistingContact(String email, String phoneNumber) {
        if (email != null) {
            return contactRepository.findByEmail(email);
        } else if (phoneNumber != null) {
            return contactRepository.findByPhoneNumber(phoneNumber);
        }
        return Optional.empty();
    }

    private Contact findPrimaryContact(Contact contact) {
        return contact.getLinkPrecedence() == LinkPrecedence.PRIMARY ? contact : contact.getLinkedContact();
    }

    private Contact createPrimaryContact(String email, String phoneNumber) {
        Contact newContact = new Contact();
        newContact.setEmail(email);
        newContact.setPhoneNumber(phoneNumber);
        newContact.setLinkPrecedence(LinkPrecedence.PRIMARY);
        newContact.setCreatedAt(LocalDateTime.now());
        newContact.setUpdatedAt(LocalDateTime.now());
        return contactRepository.save(newContact);
    }

    private ContactResponse buildResponse(Contact primaryContact) {
        List<Contact> linkedContacts = contactRepository.findByLinkedContact(primaryContact);

        List<String> emails = new ArrayList<>();
        List<String> phoneNumbers = new ArrayList<>();
        List<Long> secondaryContactIds = new ArrayList<>();

        emails.add(primaryContact.getEmail());
        phoneNumbers.add(primaryContact.getPhoneNumber());

        for (Contact contact : linkedContacts) {
            emails.add(contact.getEmail());
            phoneNumbers.add(contact.getPhoneNumber());
            secondaryContactIds.add(contact.getId());
        }

        ContactDetails contactDetails = new ContactDetails(primaryContact.getId(), emails, phoneNumbers, secondaryContactIds);
        return new ContactResponse(contactDetails);
    }
}

