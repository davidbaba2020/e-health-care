package com.hda.dev.dto;

import com.hda.dev.models.User;
import lombok.Builder;
import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.ManyToOne;
import java.util.List;

@Data
@Builder
public class SymptomsDto {
    @ElementCollection
    private List<String> symptomNames;
    @ManyToOne
    private User patient;
}
