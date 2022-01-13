package com.hda.dev.models;

import com.hda.dev.common.BaseClassAll;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


//@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Symptoms extends BaseClassAll {
    @ElementCollection
    private List<String> symptomNames;
    @ManyToOne
    private User patient;
}
