package com.librarty.book.config.security;

import com.librarty.book.dto.application.TokenDTO;
import com.librarty.book.service.Oauth2UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomTokenEnhancer extends JwtAccessTokenConverter {

    private static final Logger LOGGER = LogManager.getLogger(CustomTokenEnhancer.class);
    private final Oauth2UserService oauth2UserService;

    @Autowired
    public CustomTokenEnhancer(Oauth2UserService oauth2UserService) {
        this.oauth2UserService = oauth2UserService;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        LOGGER.info("Start Function CustomTokenEnhancer enhance");
        final Map<String, Object> additionalInfo = new HashMap<>();

        User user = (User) oAuth2Authentication.getPrincipal();
        LOGGER.info("Function CustomTokenEnhancer enhance username : " + user.getUsername());

        TokenDTO userTokenData = oauth2UserService.getUserTokenData(user.getUsername());

        if (userTokenData != null && userTokenData.getId() > 0) {
            additionalInfo.put("user", userTokenData);
        }
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
        // set custom claims
        return super.enhance(oAuth2AccessToken, oAuth2Authentication);
    }

}
