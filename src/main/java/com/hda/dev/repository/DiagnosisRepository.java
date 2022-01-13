package com.hda.dev.repository;

import com.hda.dev.models.Diagnosis;
import com.hda.dev.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
}
