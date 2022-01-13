package com.hda.dev.models;

import com.hda.dev.common.BaseClassAll;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

//@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Diagnosis extends BaseClassAll {
    private String diagnosisDetails;
    private String diseases;
    @OneToOne
    private Symptoms symptoms;
    @ManyToOne
    private User patient;
}
