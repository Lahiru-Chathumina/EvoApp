package edu.icet.controller.system;

import edu.icet.service.oauth.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {
    private final SignUpService signUpService;

    @GetMapping("/google")
    public ResponseEntity<Boolean> google(OAuth2AuthenticationToken token, @RequestParam String userType) {
        return   ResponseEntity.ok(signUpService.AuthenticateUser(token,userType)) ;
    }

    @GetMapping("/facebook")
    public ResponseEntity<Boolean> facebook(OAuth2AuthenticationToken token, @RequestParam String userType) {
        return   ResponseEntity.ok(signUpService.AuthenticateUser(token,userType)) ;
    }
}