package com.hda.dev.service;

import com.hda.dev.dto.DiagnosisDto;
import com.hda.dev.dto.DoctorRegistrationDto;
import com.hda.dev.dto.PatientRegistrationDto;
import com.hda.dev.dto.SymptomsDto;
import com.hda.dev.models.DoctorAppointment;
import com.hda.dev.models.User;

public interface UserService {
    String signupPatient(PatientRegistrationDto registrationDto) throws Exception;
    String signupDoctor(DoctorRegistrationDto doctorRegistrationDto ) throws Exception;
//    String createPatientSymptoms(SymptomsDto symptomsDto) throws Exception;
    String createPatientSymptomsAndDiagnosis(SymptomsDto symptomsDto, DiagnosisDto diagnosisDto) throws Exception;
    boolean login(String email, String password);
    DoctorAppointment bookAppointment(Long personnelId, Long patientId);
}
