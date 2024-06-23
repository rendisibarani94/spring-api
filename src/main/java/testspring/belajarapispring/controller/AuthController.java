package testspring.belajarapispring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import testspring.belajarapispring.dto.LoginUserRequest;
import testspring.belajarapispring.dto.TokenResponse;
import testspring.belajarapispring.dto.WebResponse;
import testspring.belajarapispring.service.AuthService;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping(
            path = "api/auth/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    WebResponse<TokenResponse> login(@RequestBody LoginUserRequest request){
        TokenResponse tokenResponse = authService.login(request);
        return WebResponse.<TokenResponse>builder().data(tokenResponse).build();
    }
}
