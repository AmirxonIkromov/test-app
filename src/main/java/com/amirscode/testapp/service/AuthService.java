package com.amirscode.testapp.service;

import com.amirscode.testapp.config.JwtService;
import com.amirscode.testapp.dao.UserRepository;
import com.amirscode.testapp.entity.User;
import com.amirscode.testapp.enums.Role;
import com.amirscode.testapp.model.LoginRequest;
import com.amirscode.testapp.model.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;


    public ResponseEntity<?> register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
                    .body("User in this email already registered");
        }
        User user = User.builder()
                .fullName(request.getFullName() == null ? request.getEmail() : request.getFullName())
                .PhoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .age(request.getAge())
                .role(Role.USER)
                .credentialsNonExpired(true)
                .accountNonLocked(true)
                .accountNonExpired(true)
                .emailCode(generateCode())
                .build();
        userRepository.save(user);
        sendEmail(user.getEmail(), user.getEmailCode());

        return ResponseEntity.ok("Verify your email " + user.getEmail());
    }


    public ResponseEntity<?> login(LoginRequest request) {

        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            }catch (Exception ex){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong email or password");
            }
            String jwtToken = jwtService.generateToken(user);
            return ResponseEntity.ok(jwtToken);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such user");
    }

    public ResponseEntity<?> verifyEmail(Integer emailCode, String email) {

        Optional<User> optionalUser = userRepository.findByEmailAndEmailCode(email, emailCode);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEnabled(true);
            user.setEmailCode(null);
            userRepository.save(user);
            return ResponseEntity.ok("Email verified");
        }
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Email incorrect");
    }

    private void sendEmail(String sendingEmail, Integer emailCode) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("Amirscode@gmail.com");
            message.setSentDate(new Date(System.currentTimeMillis()));
            message.setTo(sendingEmail);
            message.setSubject("Email verification code");
            message.setText("<a href='https://test-app.up.railway.app/auth/verifyEmail?emailCode=" + emailCode + "&email=" + sendingEmail + "'> Verify email</a>");
            javaMailSender.send(message);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    private Integer generateCode() {
        return Integer.valueOf(new DecimalFormat("0000").format(new Random().nextInt(9999)));
    }

}
