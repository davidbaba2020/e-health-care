package com.hda.dev.controller;

import com.hda.dev.configuration.securityConfigs.JwtTokenUtil;

import com.hda.dev.requests.JwtRequest;
import com.hda.dev.responses.JwtResponse;
import com.hda.dev.service.UserService;
import com.hda.dev.service.serviceImpl.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class JwtAuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;
    private final UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
//        Authentication authenticate;
//        try {
//            System.out.println(1);
////            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
////                    (jwtRequest.getEmail(), jwtRequest.getPassword()));
//            System.out.println(2);
//        } catch (DisabledException e) {
//            throw new Exception("USER_DISABLED", e);
//        } catch (BadCredentialsException e) {
//            throw new Exception("INVALID_CREDENTIALS", e);
//        }
//        SecurityContextHolder.getContext().setAuthentication(authenticate);
        System.out.println(3);
        boolean status = userService.login(jwtRequest.getEmail(), jwtRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());

        final String jwtToken = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(jwtToken));
    }

}
