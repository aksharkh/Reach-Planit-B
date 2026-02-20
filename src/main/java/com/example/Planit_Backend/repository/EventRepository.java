package com.example.Planit_Backend.repository;

import com.example.Planit_Backend.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByUserIdAndEventDateAfterOrderByEventDateAsc(Long userId, LocalDate now);


    Long countByUserIdAndEventDate(Long userId, LocalDate eventDate);

}
