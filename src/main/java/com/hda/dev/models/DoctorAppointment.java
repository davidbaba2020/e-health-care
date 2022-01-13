package com.hda.dev.models;

import com.hda.dev.common.BaseClassAll;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorAppointment extends BaseClassAll {
    @OneToOne
    private User medicalPersonnel;
    @OneToOne
    private User patient;
    private LocalDateTime appointmentDate = LocalDateTime.now();
    private boolean appointmentApproval = false;
}
