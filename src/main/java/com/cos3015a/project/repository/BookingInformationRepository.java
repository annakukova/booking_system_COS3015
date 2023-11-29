package com.cos3015a.project.repository;

import com.cos3015a.project.dto.BookedDateDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingInformationRepository extends JpaRepository<BookedDateDTO, Integer> {

}
