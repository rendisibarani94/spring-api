package testspring.belajarapispring.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testspring.belajarapispring.dto.ContactResponse;
import testspring.belajarapispring.dto.CreateContactRequest;
import testspring.belajarapispring.entity.Contact;
import testspring.belajarapispring.entity.User;
import testspring.belajarapispring.repository.ContactRepository;

import java.util.UUID;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    ValidationService validationService;

    @Transactional
    public ContactResponse create(User user , CreateContactRequest request) {
        validationService.validate(request);

        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setUser(user);

        contactRepository.save(contact);

        return ContactResponse.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .phone(contact.getPhone())
                .build();
    }
}
