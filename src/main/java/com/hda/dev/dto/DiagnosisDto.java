package com.hda.dev.dto;

import com.hda.dev.models.Symptoms;
import com.hda.dev.models.User;
import lombok.Builder;
import lombok.Data;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@Builder
public class DiagnosisDto {
    private String diagnosisDetails;
    private String diseases;
    @OneToOne
    private Symptoms symptoms;
    @ManyToOne
    private User patient;
}
