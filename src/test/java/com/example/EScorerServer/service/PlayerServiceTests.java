package com.example.EScorerServer.service;

import com.example.EScorerServer.model.Player;
import com.example.EScorerServer.model.Team;
import com.example.EScorerServer.model.User;
import com.example.EScorerServer.repository.PlayerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTests {
    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private TeamService teamService;

    @InjectMocks
    private PlayerServiceImpl playerService;

    private List<Player> playerList;
    private List<Player> playerListWithUpdatePlayer;
    private List<Player> playerFilteredList;
    private Team team;

    @BeforeEach
    void initPlayerList()
    {
        MockitoAnnotations.initMocks(this);
        User user = new User("user", "Super", "User");
        team = new Team(8, "dream team", "dream", user.getId());
        playerList = new ArrayList<>(Arrays.asList(
                new Player("Jan", "Nowak", team),
                new Player("Ola", "Gil", team),
                new Player("Krzysiek", "Wrot", team),
                new Player("Wojtek", "Kris", team),
                new Player("Kasia", "Gil", team),
                new Player("Władzia", "Kot", team)
        ));
        playerListWithUpdatePlayer = new ArrayList<>(Arrays.asList(
                new Player("Kasia", "Nowak", team),
                new Player("Ola", "Gil", team),
                new Player("Krzysiek", "Wrot", team),
                new Player("Wojtek", "Kris", team),
                new Player("Kasia", "Gil", team),
                new Player("Władzia", "Kot", team)
        ));
        playerFilteredList = new ArrayList<>(Arrays.asList(
                new Player("Jan", "Nowak", team),
                new Player("Ola", "Gil", team),
                new Player("Krzysiek", "Wrot", team)
        ));
    }

    @Test
    void injectedComponentsAreNotNull()
    {
        assertNotNull(playerRepository);
        assertNotNull(playerService);
        assertNotNull(teamService);
    }

    @Test
    void addNewTeamWithPlayers()
    {
        playerList = playerList.stream().peek(player -> player.setTeam(team)).collect(Collectors.toList());
        when(playerRepository.saveAll(playerList)).thenReturn(playerList);
        List<Player> savedPlayers = playerService.saveOrUpdatePlayersOfTeam(team.getId(), playerList);
        assertEquals(savedPlayers.size(), playerList.size());
        assertTrue(savedPlayers.contains(playerList.get(playerList.size()-1)));
    }

    @Test
    void updatePlayersInTeam()
    {
        when(playerRepository.getAllPlayersOfTeam(team.getId())).thenReturn(Optional.of(playerList));
        when(playerRepository.saveAll(playerFilteredList)).thenReturn(playerFilteredList);
        List<Player> savedPlayers = playerService.saveOrUpdatePlayersOfTeam(team.getId(), playerFilteredList);
        assertEquals(savedPlayers.size(), playerFilteredList.size());
        assertTrue(savedPlayers.contains(playerFilteredList.get(playerFilteredList.size()-1)));
    }

    @Test
    void updatePlayersInTeamToEmpty()
    {
        when(playerRepository.getAllPlayersOfTeam(team.getId())).thenReturn(Optional.of(playerList));
        when(playerRepository.saveAll(any(List.class))).thenReturn(new ArrayList());
        List<Player> savedPlayers = playerService.saveOrUpdatePlayersOfTeam(team.getId(), new ArrayList<>());
        assertEquals(savedPlayers.size(), 0);
    }

    @Test
    void updatePlayerInTeam()
    {
        when(playerRepository.getAllPlayersOfTeam(team.getId())).thenReturn(Optional.of(playerList));
        when(playerRepository.saveAll(playerListWithUpdatePlayer)).thenReturn(playerListWithUpdatePlayer);
        List<Player> savedPlayers = playerService.saveOrUpdatePlayersOfTeam(team.getId(), playerListWithUpdatePlayer);
        assertEquals(savedPlayers.size(), playerListWithUpdatePlayer.size());
        assertTrue(savedPlayers.contains(playerListWithUpdatePlayer.get(0)));
    }
}
