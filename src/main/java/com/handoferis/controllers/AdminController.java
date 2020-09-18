package com.handoferis.controllers;

import com.handoferis.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping(value = "api/admin", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/uploadsong")
    public ResponseEntity uploadSong(@RequestBody MultipartFile file) throws IOException {
        URI url = adminService.upload(file);

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
}
