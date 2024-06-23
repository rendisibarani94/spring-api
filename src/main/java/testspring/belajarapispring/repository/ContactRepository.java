package testspring.belajarapispring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testspring.belajarapispring.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
}
