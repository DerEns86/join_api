package dev.ens.join_backend.controller;

import dev.ens.join_backend.model.Contact;
import dev.ens.join_backend.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactService contactService;

    @GetMapping()
    public ResponseEntity<List<Contact>> getAllCategories() {
        return ResponseEntity.ok(contactService.getAllContacts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        return ResponseEntity.ok(contactService.getContactById(id));
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(contactService.createContact(contact, userDetails.getUsername()));
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
       contactService.deleteContact(id);
    }

}
