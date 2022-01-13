package com.hda.dev.dataBaseMigration;

import com.hda.dev.enums.Gender;
import com.hda.dev.enums.UserType;
import com.hda.dev.models.User;
import com.hda.dev.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class MyDataMigrationClass {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public MyDataMigrationClass(UserRepository userRepository,
                                PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private  void  userCreator(){


        User admin = User.builder()
                .firstName("David")
                .lastName("Baba")
                .email("david@gmail.com")
                .password("David2022")
                .dateOfBirth(LocalDate.of(1990, 03, 02))
                .userType(UserType.ADMIN)
                .gender(Gender.MALE)
                .isAccountVerified(true)
                .build();

        User doctor1 = User.builder()
                .firstName("Ikechukwu")
                .lastName("Omola")
                .email("ike@gmail.com")
                .password(passwordEncoder.encode("Ike2022"))
                .dateOfBirth(LocalDate.of(1980, 03, 02))
                .userType(UserType.DOCTOR)
                .gender(Gender.MALE)
                .description("Specialist in Cancer")
                .qualification("Consultant Physcitry")
                .title("Doctor")
                .isAccountVerified(true)
                .build();

        User patient1 = User.builder()
                .firstName("Mainila")
                .lastName("Bulus")
                .email("mainilas@gmail.com")
                .password("Mainila2022")
                .dateOfBirth(LocalDate.of(1990, 03, 02))
                .userType(UserType.PATIENT)
                .gender(Gender.FEMALE)
                .isAccountVerified(true)
                .build();

        List<User> users = Arrays.asList(admin, doctor1,patient1);
        userRepository.saveAll(users);

    }
}
