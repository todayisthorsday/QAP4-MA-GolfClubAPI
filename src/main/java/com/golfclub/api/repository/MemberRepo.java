package com.golfclub.api.repository;

import com.golfclub.api.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {
    List<Member> findByNameContainingIgnoreCase(String name);
    List<Member> findByPhone(String phoneNumber);
    List<Member> findByStartDate(LocalDate startDate);
    }
