package com.golfclub.api.service;

import com.golfclub.api.model.Member;
import com.golfclub.api.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private final MemberRepo memberRepo;

    public MemberService(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

    public Member saveMember(Member member) {
        return memberRepo.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepo.findAll();
    }

    public Optional<Member> getMemberById(Long id) {
        return memberRepo.findById(id);
    }

    public List<Member> searchByName(String name) {
        return memberRepo.findByNameContainingIgnoreCase(name);
    }

    public List<Member> searchByPhone(String phoneNumber) {
        return memberRepo.findByPhone(phoneNumber);
    }

    public List<Member> searchByStartDate(LocalDate startDate) {
        return memberRepo.findByStartDate(startDate);
    }
}
