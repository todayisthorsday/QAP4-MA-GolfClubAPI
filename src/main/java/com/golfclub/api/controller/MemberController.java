package com.golfclub.api.controller;

import com.golfclub.api.model.Member;
import com.golfclub.api.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeParseException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public Member addMember(@RequestBody Member member) {
        return memberService.saveMember(member);
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
    }

    @GetMapping("/search/by-name")
    public List<Member> searchByName(@RequestParam String name) {
        return memberService.searchByName(name);
    }

    @GetMapping("/search/by-phone")
    public List<Member> searchByPhone(@RequestParam String phone) {
        return memberService.searchByPhone(phone);
    }

    @GetMapping("/search/by-start-date")
    public List<Member> searchByStartDate(@RequestParam String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            return memberService.searchByStartDate(parsedDate);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format. Use yyyy-MM-dd.");
        }
    }

    @GetMapping("/search/by-membership")
    public List<Member> searchByMembership(@RequestParam String membershipType) {
        return memberService.searchByMembershipType(membershipType);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member member) {
        Member updated = memberService.updateMember(id, member);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }



}
