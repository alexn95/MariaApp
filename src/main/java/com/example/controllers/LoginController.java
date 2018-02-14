package com.example.controllers;

import com.okta.spring.config.OktaClientProperties;
import com.okta.spring.config.OktaOAuth2Properties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private static final String STATE = "state";
    private static final String SCOPES = "scopes";
    private static final String OKTA_BASE_URL = "oktaBaseUrl";
    private static final String OKTA_CLIENT_ID = "oktaClientId";
    private static final String REDIRECT_URI = "redirectUri";
    private static final String ISSUER_URI = "issuerUri";

    private final OktaOAuth2Properties oktaOAuth2Properties;

    private final OktaClientProperties oktaClientProperties;

    public LoginController(OktaOAuth2Properties oktaOAuth2Properties, OktaClientProperties oktaClientProperties) {
        this.oktaOAuth2Properties = oktaOAuth2Properties;
        this.oktaClientProperties = oktaClientProperties;
    }

    @GetMapping(value = "/login")
    public ModelAndView login(@RequestParam("state") String state) {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject(STATE, state);
        mav.addObject(SCOPES, oktaOAuth2Properties.getScopes());
        mav.addObject(OKTA_BASE_URL, oktaClientProperties.getOrgUrl());
        mav.addObject(OKTA_CLIENT_ID, oktaOAuth2Properties.getClientId());
        mav.addObject(REDIRECT_URI, oktaOAuth2Properties.getRedirectUri());
        mav.addObject(ISSUER_URI, oktaOAuth2Properties.getIssuer());
        return mav;
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }
}
