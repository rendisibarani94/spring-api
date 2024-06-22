package testspring.belajarapispring.service;

import testspring.belajarapispring.dto.RegisterUserRequest;
import testspring.belajarapispring.entity.User;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import testspring.belajarapispring.repository.UserRepository;
import testspring.belajarapispring.security.BCrypt;

import java.util.Set;

@Service
public class UserService {

    // inject user repository
    @Autowired
    private UserRepository userRepository;

    // inject validator untuk validasi
    @Autowired
    private Validator validator;

    @Transactional
    public void register(RegisterUserRequest request){
        Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

        if(!constraintViolations.isEmpty()){
            throw new ConstraintViolationException(constraintViolations);
        }

        if (userRepository.existsById(request.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST ,"Username already registered");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setName(request.getName());

        userRepository.save(user);

    }
}
