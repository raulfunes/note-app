/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensolvers.challenge.backend.service;

import ensolvers.challenge.backend.model.User;
import ensolvers.challenge.backend.model.user.UserListDTO;
import ensolvers.challenge.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rauls
 */
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public UserListDTO getUsuarioByUsername(String username){
        User userReturned = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + username));
        return new UserListDTO(userReturned);
    }
}
