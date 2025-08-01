package com.golfclub.api.controller;

import com.golfclub.api.model.Member;
import com.golfclub.api.model.Tournament;
import com.golfclub.api.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeParseException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @PostMapping
    public Tournament addTournament(@RequestBody Tournament tournament) {
        return tournamentService.saveTournament(tournament);
    }

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @GetMapping("/{id}")
    public Tournament getTournamentById(@PathVariable Long id) {
        return tournamentService.getTournamentById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
    }

    @PostMapping("/{tournamentId}/add-member/{memberId}")
    public Tournament addMemberToTournament(@PathVariable Long tournamentId, @PathVariable Long memberId) {
        return tournamentService.addMemberToTournament(tournamentId, memberId);
    }

    @GetMapping("/search/by-location")
    public List<Tournament> searchByLocation(@RequestParam String location) {
        return tournamentService.searchByLocation(location);
    }

    @GetMapping("/search/by-start-date")
    public List<Tournament> searchByStartDate(@RequestParam String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            return tournamentService.searchByStartDate(parsedDate);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format. Use yyyy-MM-dd.");
        }
    }

    @GetMapping("/{tournamentId}/members")
    public List<Member> getMembersInTournament(@PathVariable Long tournamentId) {
        return tournamentService.getMembersInTournament(tournamentId);
    }
}
