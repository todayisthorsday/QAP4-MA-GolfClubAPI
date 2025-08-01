package com.golfclub.api.repository;

import com.golfclub.api.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberRepo extends jpaRepo<Member, Long> {
    List<Member> findByContainingIgnoreCase(String name);
    List<Member> findByPhoneNumber(String phoneNumber);
    List<Member> findByStartDate(LocalDate startDate);
    }
