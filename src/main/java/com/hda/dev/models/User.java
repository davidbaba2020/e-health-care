package com.hda.dev.models;

import com.hda.dev.common.BaseClassAll;
import com.hda.dev.enums.Gender;
import com.hda.dev.enums.UserType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Builder
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseClassAll {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private LocalDate dateOfBirth;
    private boolean isAccountVerified;


    //Doctor Fileds
    private String title;
    private String qualification;
    private String description;
    private String specialty;
}
