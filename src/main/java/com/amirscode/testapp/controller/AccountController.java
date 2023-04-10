package com.amirscode.testapp.controller;

import com.amirscode.testapp.model.UserDTO;
import com.amirscode.testapp.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class AccountController {

    private final AccountService accountService;


    @PatchMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return accountService.edit(id, userDTO);
    }
}
