package com.pinguin.issuetrackerapi.controller;

import com.pinguin.issuetrackerapi.dto.DeveloperDetailsDto;
import com.pinguin.issuetrackerapi.dto.RegisterDeveloperDto;
import com.pinguin.issuetrackerapi.entity.DeveloperDetails;
import com.pinguin.issuetrackerapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/api/v1")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/developer")
    private ResponseEntity<DeveloperDetailsDto> registerDeveloper(@RequestBody RegisterDeveloperDto registerDeveloperDto) {

        return new ResponseEntity<DeveloperDetailsDto>(userService
                .registerDeveloper(registerDeveloperDto), HttpStatus.OK);
    }

    @GetMapping("/developer")
    private ResponseEntity<List<DeveloperDetailsDto>> getAllDevelopers() {

        return new ResponseEntity<List<DeveloperDetailsDto>>(userService
                .getAllDeveloper(), HttpStatus.OK);
    }

    @DeleteMapping("/developer")
    private ResponseEntity<String> deleteDeveloper(@RequestParam int developerId) {
        userService.deleteDeveloper(developerId);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
