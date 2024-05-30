package com.bitespeed.identity_reconciliation.repository;

import com.bitespeed.identity_reconciliation.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    Optional<Contact> findByEmail(String email);

    Optional<Contact> findByPhoneNumber(String phoneNumber);

    List<Contact> findByLinkedContact(Contact linkedContact);

}