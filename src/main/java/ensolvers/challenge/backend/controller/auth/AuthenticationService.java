/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensolvers.challenge.backend.controller.auth;

import ensolvers.challenge.backend.config.JwtUtils;
import ensolvers.challenge.backend.model.User;
import ensolvers.challenge.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author rauls
 */
@Service
@RequiredArgsConstructor
class AuthenticationService {
    
    
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    public AuthenticationResponse register(RegisterRequest request){
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .name(request.getName())
                .build();
        userRepository.save(user);
        var jwtToken = jwtUtils.generateToken(user);
        
        return AuthenticationResponse.builder().token(jwtToken).username(user.getUsername()).build();
    }
    
    public AuthenticationResponse login(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername());
        var jwtToken = jwtUtils.generateToken(user.get());
        String username = request.getUsername();
        return AuthenticationResponse.builder().token(jwtToken).username(username).build();
    }
}
