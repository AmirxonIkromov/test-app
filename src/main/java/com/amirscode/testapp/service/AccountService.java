package com.amirscode.testapp.service;

import com.amirscode.testapp.dao.UserRepository;
import com.amirscode.testapp.entity.User;
import com.amirscode.testapp.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserRepository userRepository;


    public ResponseEntity<?> edit(Long id, UserDTO userDTO) {

        User user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if(user.equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            user.setAge(userDTO.getAge() != null ? userDTO.getAge() : user.getAge());
            user.setFullName(userDTO.getFullName() != null ? userDTO.getFullName() : user.getFullName());
            user.setEmail(userDTO.getEmail() != null ? userDTO.getEmail() : user.getEmail());
            user.setPhoneNumber(userDTO.getPhoneNumber() != null ? userDTO.getPhoneNumber() : user.getPhoneNumber());

            userRepository.save(user);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
