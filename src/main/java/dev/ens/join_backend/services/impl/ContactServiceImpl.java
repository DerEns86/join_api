package dev.ens.join_backend.services.impl;

import dev.ens.join_backend.model.Category;
import dev.ens.join_backend.model.Contact;
import dev.ens.join_backend.model.User;
import dev.ens.join_backend.model.enums.UpdateMessage;
import dev.ens.join_backend.repository.CategoryRepository;
import dev.ens.join_backend.repository.ContactRepository;
import dev.ens.join_backend.repository.UserRepository;
import dev.ens.join_backend.services.CategoryService;
import dev.ens.join_backend.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final UserRepository userRepository;


    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Contact not found with ID: " + id));
    }

    @Override
    public Contact createContact(Contact contact, String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        contact.setCreatedBy(user.getUserId());
        return contactRepository.save(contact);
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
