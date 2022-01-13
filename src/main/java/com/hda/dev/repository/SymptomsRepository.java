package com.hda.dev.repository;

import com.hda.dev.dto.SymptomsDto;
import com.hda.dev.models.Symptoms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymptomsRepository extends JpaRepository<Symptoms, Long> {
}
