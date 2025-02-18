package dev.ens.join_backend.services;

import dev.ens.join_backend.model.Contact;

import java.util.List;
import java.util.NoSuchElementException;


public interface ContactService {

    List<Contact> getAllContacts();

    Contact getContactById(Long id);

    Contact createContact(Contact contact, String username);

    void deleteContact(Long id);
}