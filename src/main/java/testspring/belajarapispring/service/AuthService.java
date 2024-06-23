package testspring.belajarapispring.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import testspring.belajarapispring.dto.LoginUserRequest;
import testspring.belajarapispring.dto.TokenResponse;
import testspring.belajarapispring.entity.User;
import testspring.belajarapispring.repository.UserRepository;
import testspring.belajarapispring.security.BCrypt;

import java.util.UUID;

@Service
public class AuthService  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public TokenResponse login(LoginUserRequest request) {
        validationService.validate(request);

        User user = userRepository.findById(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or Password wrong"));

        if(BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpiredAt(tokenExpired());
            userRepository.save(user);

            return TokenResponse.builder()
                    .token(user.getToken())
                    .expireAt(user.getTokenExpiredAt())
                    .build();

        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or Password wrong");
        }


    }

    private Long tokenExpired(){
        return System.currentTimeMillis() + (1000 * 60 * 24 * 30);
    }
}
