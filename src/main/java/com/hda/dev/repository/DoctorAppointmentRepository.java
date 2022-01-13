package com.hda.dev.repository;

import com.hda.dev.models.DoctorAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorAppointmentRepository extends JpaRepository<DoctorAppointment,Long> {

}
