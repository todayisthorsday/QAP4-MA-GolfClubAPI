package com.golfclub.api.service;

import com.golfclub.api.model.Member;
import com.golfclub.api.model.Tournament;
import com.golfclub.api.repository.MemberRepo;
import com.golfclub.api.repository.TournamentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepo tournamentRepo;

    @Autowired
    private MemberRepo memberRepo;

    public Tournament saveTournament(Tournament tournament) {
        return tournamentRepo.save(tournament);
    }

    public List<Tournament> getAllTournaments() {
        return tournamentRepo.findAll();
    }

    public Optional<Tournament> getTournamentById(Long id) {
        return tournamentRepo.findById(id);
    }

    public List<Tournament> searchByStartDate(LocalDate startDate) {
        return tournamentRepo.findByStartDate(startDate);
    }

    public List<Tournament> searchByLocation(String location) {
        return tournamentRepo.findByLocationContainingIgnoreCase(location);
    }

    public Tournament addMemberToTournament(Long tournamentId, Long memberId) {
        Tournament tournament = tournamentRepo.findById(tournamentId).orElseThrow();
        Member member = memberRepo.findById(memberId).orElseThrow();
        tournament.getMembers().add(member);
        return tournamentRepo.save(tournament);
    }

    public List<Member> getMembersInTournament(Long tournamentId) {
        return tournamentRepo.findById(tournamentId)
                .map(Tournament::getMembers)
                .map(List::copyOf)
                .orElseThrow();
    }
}
