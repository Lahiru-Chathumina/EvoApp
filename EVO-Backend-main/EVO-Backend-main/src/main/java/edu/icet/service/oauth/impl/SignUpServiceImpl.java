package edu.icet.service.oauth.impl;

import edu.icet.dto.customer.User;
import edu.icet.service.oauth.SignUpService;
import edu.icet.util.UserType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SignUpServiceImpl implements SignUpService {
    @Override
    public Boolean AuthenticateUser(OAuth2AuthenticationToken token,String userType) {
        Map<String, Object> attributes = token.getPrincipal().getAttributes();

        User user = new User();
        user.setFirstName(attributes.get("name").toString());
        user.setLastName(null);
        String encodedPassword = new BCryptPasswordEncoder().encode(attributes.get("sub").toString());
        user.setPassword(encodedPassword);
        user.setEmail(attributes.get("email").toString());

        if (userType.equals("ADMIN")){
            user.setUserType(UserType.ADMIN);
        }else if(userType.equals("CUSTOMER")){
            user.setUserType(UserType.CUSTOMER);
        }else if(userType.equals("SUPPLIER")){
            user.setUserType(UserType.SUPPLIER);
        }else{
            return false;
        }
        return true;
    }
}
