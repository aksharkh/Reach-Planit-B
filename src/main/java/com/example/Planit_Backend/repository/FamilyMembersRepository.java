package com.example.Planit_Backend.repository;

import com.example.Planit_Backend.entity.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyMembersRepository extends JpaRepository<FamilyMember , Long> {

    List<FamilyMember> findByUserId(Long userId);
}
