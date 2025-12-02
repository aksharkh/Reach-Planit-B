package com.example.Planit_Backend.repository;

import com.example.Planit_Backend.entity.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyMembersRepository extends JpaRepository<FamilyMember , Long> {


}
