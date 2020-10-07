package com.handoferis.security;

import net.minidev.json.JSONObject;
import org.springframework.http.*;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

public class AudienceValidator implements OAuth2TokenValidator<Jwt> {

    private final String audience;

    AudienceValidator(String audience) { this.audience = audience;}

    //@Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
        OAuth2Error error = new OAuth2Error("invalid token", "request audience is missing", null);

        if(token.getAudience().contains(audience)){
//            var test = token;
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.set("Authorization", "Bearer " + token);
//
//            HttpEntity<String> entity = new HttpEntity<String>(headers);
//
//            RestTemplate restTemplate = new RestTemplate();
//            ResponseEntity<String> result = restTemplate
//                    .exchange("https://dev-3c9hn7ky.us.auth0.com/api/v2/users", HttpMethod.GET, entity, String.class);
//
//

            return OAuth2TokenValidatorResult.success();
        }

        return OAuth2TokenValidatorResult.failure(error);
    }

//    public String getManagementApiToken() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        JSONObject requestBody = new JSONObject();
//        requestBody.put("client_id", "KROXiHCitz82n3Dk2cUjjm5QhG3eCwFR");
//        requestBody.put("client_secret", "uLRG8kVZQOrWBgAqPt0TfaUqy0bAAZMbTTPCOhhz042TLPY6NocKzw7aHLfJ9I59");
//        requestBody.put("audience", "https://dev-3c9hn7ky.us.auth0.com/api/v2/");
//        requestBody.put("grant_type", "client_credentials");
//
//        HttpEntity<String> request = new HttpEntity<String>(requestBody.toString(), headers);
//
//        RestTemplate restTemplate = new RestTemplate();
//        HashMap<String, String> result = restTemplate
//                .postForObject("https://dev-3c9hn7ky.us.auth0.com/oauth/token", request, HashMap.class);
//
//        return result.get("access_token");
//    }

}
