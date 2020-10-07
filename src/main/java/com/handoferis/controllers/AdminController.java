package com.handoferis.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.spring.security.api.authentication.AuthenticationJsonWebToken;
import com.handoferis.DAL.UploadJamDAL;
import com.handoferis.pojos.UploadJam;
import com.handoferis.services.AdminService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.*;

@RestController
@RequestMapping(value = "api/admin", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class AdminController {


    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<Task> getTasks(AuthenticationJsonWebToken authentication) {
        //logger.debug("getTasks called.");
        DecodedJWT jwt = JWT.decode(authentication.getToken());
        Map<String, Claim> claims = jwt.getClaims();
        for (Object key: claims.keySet()) {
            //logger.debug("key: {}, value: {}", key.toString(),  claims.get(key).asString());
        }

        return null;
    }

    @PostMapping("/uploadsong")
    public ResponseEntity uploadSong(@RequestParam("file") MultipartFile file, @RequestParam("title") String title,
                                     @RequestParam("notes") String notes, @RequestParam("date") Date date,
                                     @RequestParam("user") String user) throws IOException {

        URI url = adminService.upload(file, title, notes, date, user);

        if(url != null)
        {
            return ResponseEntity.ok(url);
        }
        else
        {
            return (ResponseEntity) ResponseEntity.noContent();
        }
    }

    @GetMapping("/retrievesongs")
    public ResponseEntity retrieveSongs() throws IOException {

        var data = adminService.getSongs();

        if(data != null)
        {
            return ResponseEntity.ok(data);
        }
        else
        {
            return (ResponseEntity) ResponseEntity.noContent();
        }
    }

    @GetMapping("/getjams")
    public List<UploadJam> getAllJams(){
        var result = adminService.getAllSongs();
        return result;
    }

    @GetMapping("/getjambyid/{id}")
    public Optional getJamById(@PathVariable UUID id){
        var result = adminService.getJamById(id);
        return result;
    }
}
