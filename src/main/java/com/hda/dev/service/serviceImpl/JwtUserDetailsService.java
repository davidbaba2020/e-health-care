package com.hda.dev.service.serviceImpl;

import com.hda.dev.dto.MyUserDetails;
import com.hda.dev.models.User;
import com.hda.dev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailsService implements  UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<User> userModel = userRepository.findUserByEmail(userEmail);
        User user = userModel.orElseThrow(() ->
              new UsernameNotFoundException("No user found with email : " + userEmail));

            return new MyUserDetails(user);
    }
}
