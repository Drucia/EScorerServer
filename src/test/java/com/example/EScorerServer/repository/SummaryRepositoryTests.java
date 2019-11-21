package com.example.EScorerServer.repository;

import com.example.EScorerServer.model.Match;
import com.example.EScorerServer.model.Summary;
import com.example.EScorerServer.model.Team;
import com.example.EScorerServer.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SummaryRepositoryTests {
    @Autowired private SummaryRepository summaryRepository;
    @Autowired private MatchRepository matchRepository;
    @Autowired private TeamRepository teamRepository;
    @Autowired private UserRepository userRepository;

    private User user;
    private Team host;
    private Team guest;

    @Before
    public void initUser()
    {
        user = new User("testowy", "Pan", "Test");
        host = new Team("host team", "host");
        guest = new Team("guest team", "guest");
    }

    @Test
    public void injectedComponentsAreNotNull()
    {
        assertThat(summaryRepository).isNotNull();
        assertThat(matchRepository).isNotNull();
        assertThat(userRepository).isNotNull();
    }

    @Test
    public void saveUserTeamsAndMatchAndThenAddSummaryOfMatch()
    {
        userRepository.save(user);
        host.setUserId(user.getId());
        guest.setUserId(user.getId());
        host = teamRepository.save(host);
        guest = teamRepository.save(guest);
        Match match = new Match();
        match.setHost_team(host);
        match.setGuest_team(guest);
        match.setDate("21/11/2019 10:30");
        match.setUserId(user.getId());
        Match savedMatch = matchRepository.save(match);
        Summary summary = new Summary();
        summary.setMatchId(savedMatch.getId());
        summary.setWinner(host);
        summary.setSetsHome(3);
        summary.setSetsGuest(2);
        summaryRepository.save(summary);
        assertThat(summaryRepository.getSummaryByMatch(savedMatch.getId()).isPresent()).isTrue();
    }

    @Test
    public void saveSummaryOfUserMatch()
    {
        userRepository.save(user);
        host.setUserId(user.getId());
        guest.setUserId(user.getId());
        host = teamRepository.save(host);
        guest = teamRepository.save(guest);
        Match match = new Match();
        match.setHost_team(host);
        match.setGuest_team(guest);
        match.setDate("21/11/2019 10:30");
        match.setUserId(user.getId());
        Match savedMatch = matchRepository.save(match);
        Summary summary = new Summary();
        summary.setMatchId(savedMatch.getId());
        summary.setWinner(host);
        summary.setSetsHome(3);
        summary.setSetsGuest(2);
        summaryRepository.save(summary);
        assertThat(summaryRepository.getAllSummariesOfUser(user.getId()).isPresent()).isTrue();
    }

    @Test
    public void saveSeveralSummariesOfUserMatches()
    {
        userRepository.save(user);
        host.setUserId(user.getId());
        guest.setUserId(user.getId());
        host = teamRepository.save(host);
        guest = teamRepository.save(guest);
        Match match = new Match();
        match.setHost_team(host);
        match.setGuest_team(guest);
        match.setDate("21/11/2019 10:30");
        match.setUserId(user.getId());
        Match savedMatch = matchRepository.save(match);
        Summary summary = new Summary();
        summary.setMatchId(savedMatch.getId());
        summary.setWinner(host);
        summary.setSetsHome(3);
        summary.setSetsGuest(2);
        summaryRepository.save(summary);
        match = new Match();
        match.setHost_team(guest);
        match.setGuest_team(host);
        match.setDate("21/11/2019 12:30");
        match.setUserId(user.getId());
        savedMatch = matchRepository.save(match);
        summary = new Summary();
        summary.setMatchId(savedMatch.getId());
        summary.setWinner(guest);
        summary.setSetsHome(2);
        summary.setSetsGuest(3);
        summaryRepository.save(summary);
        assertThat(summaryRepository.getAllSummariesOfUser(user.getId()).get().size()).isEqualTo(2);
    }

    @Test
    public void saveSummaryOfMatchAndCheckIfIsTheSameWithUserSummaries()
    {
        userRepository.save(user);
        host.setUserId(user.getId());
        guest.setUserId(user.getId());
        host = teamRepository.save(host);
        guest = teamRepository.save(guest);
        Match match = new Match();
        match.setHost_team(host);
        match.setGuest_team(guest);
        match.setDate("21/11/2019 10:30");
        match.setUserId(user.getId());
        Match savedMatch = matchRepository.save(match);
        Summary summary = new Summary();
        summary.setMatchId(savedMatch.getId());
        summary.setWinner(host);
        summary.setSetsHome(3);
        summary.setSetsGuest(2);
        summaryRepository.save(summary);
        assertThat(summaryRepository.getAllSummariesOfUser(user.getId()).get().get(0))
                .isEqualTo(summaryRepository.getSummaryByMatch(savedMatch.getId()).get());
    }
}