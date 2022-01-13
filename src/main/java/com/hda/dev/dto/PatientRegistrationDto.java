package com.hda.dev.dto;

import com.hda.dev.enums.UserType;
import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
public class PatientRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    @Size(min = 5,message = "The password character is less than 8")
    private String password;
    private LocalDate dateOfBirth;
    private UserType userType;
    private String gender;
}
