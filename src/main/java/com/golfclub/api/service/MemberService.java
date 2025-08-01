package com.golfclub.api.service;

import com.golfclub.api.model.Member;
import com.golfclub.api.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public List<Member> searchByMembershipType(String membershipType) {
        return memberRepo.findByMembershipTypeContainingIgnoreCase(membershipType);
    }

    public Member updateMember(Long id, Member updatedMember) {
        return memberRepo.findById(id).map(existingMember -> {
            existingMember.setName(updatedMember.getName());
            existingMember.setAddress(updatedMember.getAddress());
            existingMember.setEmail(updatedMember.getEmail());
            existingMember.setPhone(updatedMember.getPhone());
            existingMember.setMembershipType(updatedMember.getMembershipType());
            existingMember.setStartDate(updatedMember.getStartDate());
            existingMember.setDurationInMonths(updatedMember.getDurationInMonths());
            return memberRepo.save(existingMember);
        }).orElseThrow(() -> new RuntimeException("Member not found"));
    }


    public void deleteMember(Long id) {
        if (!memberRepo.existsById(id)) {
            throw new RuntimeException("Member not found");
        }
        memberRepo.deleteById(id);
    }

}
