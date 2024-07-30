package com.logate.academy.networking.springoauth2client.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ResourceServerService
{
    private final OAuth2AuthorizedClientService authorizedClientService;

    public ResourceServerService(OAuth2AuthorizedClientService authorizedClientService)
    {
        this.authorizedClientService = authorizedClientService;
    }

    public String getProtectedResource()
    {

        String token = authorizedClientService.loadAuthorizedClient("custom-client", "user")
                .getAccessToken().getTokenValue();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8081/private/hello", HttpMethod.GET, entity, String.class);

        return response.getBody();
    }
}
