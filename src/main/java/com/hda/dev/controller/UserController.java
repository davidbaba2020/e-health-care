package com.hda.dev.controller;

import com.hda.dev.dto.DoctorRegistrationDto;
import com.hda.dev.dto.PatientRegistrationDto;
import com.hda.dev.dto.SymptomsDto;
import com.hda.dev.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/v1/health")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @RequestMapping(value = "/user-signup", method = RequestMethod.POST)
    public ResponseEntity<String> patientSignup(@Valid @RequestBody PatientRegistrationDto patientRegistrationDto) throws Exception {
        String message = userService.signupPatient(patientRegistrationDto);
        return new ResponseEntity<>(message, CREATED);
    }

    @RequestMapping(value = "/doctor-signup", method = RequestMethod.POST)
    public ResponseEntity<String> doctorSignup(@Valid @RequestBody DoctorRegistrationDto doctorRegistrationDto) throws Exception {
        String message = userService.signupDoctor(doctorRegistrationDto);
        return new ResponseEntity<>(message, CREATED);
    }

    @RequestMapping(value = "/patient-symptoms", method = RequestMethod.POST)
    public ResponseEntity<String> patientSymptoms(@Valid @RequestBody SymptomsDto symptomsDto) throws Exception {
        String message = userService.createPatientSymptoms(symptomsDto);
        return new ResponseEntity<>(message, CREATED);
    }
}
