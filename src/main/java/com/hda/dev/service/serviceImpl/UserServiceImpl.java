package com.hda.dev.service.serviceImpl;

import com.hda.dev.dto.DiagnosisDto;
import com.hda.dev.dto.DoctorRegistrationDto;
import com.hda.dev.dto.PatientRegistrationDto;
import com.hda.dev.dto.SymptomsDto;
import com.hda.dev.exceptions.CustomAppException;
import com.hda.dev.exceptions.UserWithEmailNotFound;
import com.hda.dev.models.Diagnosis;
import com.hda.dev.models.DoctorAppointment;
import com.hda.dev.models.Symptoms;
import com.hda.dev.models.User;
import com.hda.dev.repository.DiagnosisRepository;
import com.hda.dev.repository.DoctorAppointmentRepository;
import com.hda.dev.repository.SymptomsRepository;
import com.hda.dev.repository.UserRepository;
import com.hda.dev.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final SymptomsRepository symptomsRepository;
    @Autowired
    private final DoctorAppointmentRepository doctorAppointmentRepository;
    @Autowired
    private final DiagnosisRepository diagnosisRepository;
    private final ModelMapper mapper;


    @Override
    public String signupPatient(PatientRegistrationDto patientRegistrationDto) throws Exception {
        Optional<User> optionalUser = userRepository.findUserByEmail(patientRegistrationDto.getEmail());
        if (optionalUser.isEmpty()) {
            User user = mapper.map(patientRegistrationDto, User.class);
            user.setAccountVerified(true);
            userRepository.save(user);
        } else throw new CustomAppException("User with email "+patientRegistrationDto.getEmail()+ " already exist");

            return "Registration of new User was successful.";
    }

    @Override
    public String signupDoctor(DoctorRegistrationDto doctorRegistrationDto) throws Exception {
        Optional<User> optionalDoctor = userRepository.findUserByEmail(doctorRegistrationDto.getEmail());
        if (optionalDoctor.isEmpty()) {
            User user = mapper.map(doctorRegistrationDto, User.class);
            user.setAccountVerified(true);
            System.out.println(user.getDateOfBirth());
            userRepository.save(user);
        } else throw new CustomAppException("User with email "+doctorRegistrationDto.getEmail()+ " already exist");

        return "Registration of new Doctor was successful.";    }




    @Override
    public boolean login(String email, String password) {
        User user = userRepository.findUserByEmailAndPassword(email, password).orElseThrow(()->
                new CustomAppException("Email/password not correct"));
        return true;
    }

    @Override
    public DoctorAppointment bookAppointment(Long personnelId, Long patientId) {
        DoctorAppointment doctorAppointment = new DoctorAppointment();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String email = userDetails.getUsername();
        User userLoggedIn = userRepository.findUserByEmail(email).orElseThrow(() ->
                new UserWithEmailNotFound("User with " + email + " was not found"));
        boolean status = false;
        String userType = String.valueOf(userLoggedIn.getUserType());
        if (userType.equalsIgnoreCase("ADMIN")) {
            Optional<User> fetchedPatient = userRepository.findById(personnelId);
            Optional<User> fetchedMedicalPersonel = userRepository.findById(personnelId);
            if (fetchedPatient.isPresent() && fetchedMedicalPersonel.isPresent()){
                User patient  = fetchedPatient.orElseThrow();
                User personnel = fetchedMedicalPersonel.orElseThrow();

                doctorAppointment.setAppointmentApproval(true);
                doctorAppointment.setPatient(patient);
                doctorAppointment.setMedicalPersonnel(personnel);
               return doctorAppointmentRepository.save(doctorAppointment);
            }

        }else{
            throw new CustomAppException("User not authorized to access this feature");
        }
        return null;
    }


    @Override
    public String createPatientSymptomsAndDiagnosis(SymptomsDto symptomsDto, DiagnosisDto diagnosisDto) throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String email = userDetails.getUsername();
        User userLoggedIn = userRepository.findUserByEmail(email).orElseThrow(() ->
                new UserWithEmailNotFound("User with " + email + " was not found"));
        boolean status = false;
        String userType = String.valueOf(userLoggedIn.getUserType());
            if (userType.equalsIgnoreCase("ADMIN")) {
                Symptoms newSymptoms = mapper.map(symptomsDto, Symptoms.class);
                newSymptoms.setPatient(userLoggedIn);

                Diagnosis diagnosis = new Diagnosis();
                diagnosis.setDiagnosisDetails(diagnosisDto.getDiagnosisDetails());
                diagnosis.setDiseases(diagnosisDto.getDiseases());
                diagnosis.setPatient(userLoggedIn);
                diagnosis.setSymptoms(newSymptoms);
                diagnosisRepository.save(diagnosis);

                symptomsRepository.save(newSymptoms);
            }else{
            throw new CustomAppException("User not authorized to access this feature");
        }
        return "Symptoms saved successfully";
    }
}
