package com.golfclub.api.controller;

import com.golfclub.api.model.Member;
import com.golfclub.api.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return memberService.getMemberById(id).orElse(null);
    }

    @GetMapping("/search/by-name")
    public List<Member> searchByName(@RequestParam String name) {
        return memberService.searchByName(name);
    }

    @GetMapping("/search/by-phone")
    public List<Member> searchByPhone(@RequestParam String phone) {
        return memberService.searchByPhoneNumber(phone);
    }

    @GetMapping("/search/by-start-date")
    public List<Member> searchByStartDate(@RequestParam String date) {
        return memberService.searchByStartDate(LocalDate.parse(date));
    }
}
