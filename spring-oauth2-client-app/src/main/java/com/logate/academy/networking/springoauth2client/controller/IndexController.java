package com.logate.academy.networking.springoauth2client.controller;

import com.logate.academy.networking.springoauth2client.service.ResourceServerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController
{

    private final ResourceServerService resourceServerService;

    public IndexController(ResourceServerService resourceServerService)
    {
        this.resourceServerService = resourceServerService;
    }

    @GetMapping("/")
    public String home()
    {
        return "home";
    }

    @GetMapping("/secured")
    public String securedPage(Model model, @AuthenticationPrincipal OidcUser principal)
    {
        model.addAttribute("user", principal);
        return "secured";
    }

    @GetMapping("/remote")
    @ResponseBody
    public ResponseEntity<String> getRemoteInfo()
    {
        return ResponseEntity.ok(resourceServerService.getProtectedResource());
    }
}
