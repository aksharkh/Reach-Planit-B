package com.example.Planit_Backend.repository.helperRepositories;


import com.example.Planit_Backend.entity.BirthdayType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BirthdayTypeRepository extends JpaRepository<BirthdayType, Long> {

}
