package edu.icet.service.oauth;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public interface SignUpService {
    Boolean AuthenticateUser(OAuth2AuthenticationToken token,String userType);
}
