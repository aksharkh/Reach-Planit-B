package com.example.Planit_Backend.repository;

import com.example.Planit_Backend.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByUserIdAndEventDateAfterOrderByEventDateAsc(Long userId, LocalDateTime now);


    @Query("SELECT COUNT(e) FROM Event e WHERE e.user.id = :userId AND e.eventDate BETWEEN :startOfDay AND :endOfDay")
    long countEventsForDay(Long userId, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
