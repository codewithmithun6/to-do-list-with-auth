package com.codewithmithun.to_do_list.auth;

import com.codewithmithun.to_do_list.entity.User;
import com.codewithmithun.to_do_list.repository.UserRepository;
import com.codewithmithun.to_do_list.secutrity.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody User user){
        User existingUser = userRepo.findByUsername(user.getUsername()).orElse(null);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(existingUser==null){
            userRepo.save(user);
            return "User registered successfully!!";
        }
        return "User already exist";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
