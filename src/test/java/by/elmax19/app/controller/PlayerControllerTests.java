package by.elmax19.app.controller;

import by.elmax19.app.model.Player;
import by.elmax19.app.model.Position;
import by.elmax19.app.model.dto.PlayerDto;
import by.elmax19.app.repository.PlayerRepository;
import by.elmax19.app.service.PlayerService;
import by.elmax19.app.util.ObjectToJsonConverter;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlayerControllerTests {
    private final List<Player> newPlayers = new ArrayList<>();
    @Autowired
    private ObjectToJsonConverter converter;
    private List<PlayerDto> players = new ArrayList<>();

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    void init(@Autowired PlayerService playerService,
              @Autowired PlayerRepository playerRepository) {
        saveTestsData(playerRepository);
        players = playerService.findAll();
    }

    private void saveTestsData(PlayerRepository playerRepository) {
        newPlayers.add(Player.builder()
                .id(new ObjectId())
                .surname("Abdel-Aziz")
                .name("Nimir")
                .age(30)
                .height(2.01)
                .spike(360)
                .block(340)
                .position(Position.OPPOSITE_HITTER)
                .currentClub("Modena Volley")
                .number(14)
                .build());
        newPlayers.add(Player.builder()
                .id(new ObjectId())
                .surname("Mikhaylov")
                .name("Maxim")
                .age(33)
                .height(2.02)
                .spike(360)
                .block(340)
                .position(Position.OPPOSITE_HITTER)
                .currentClub("Zenit Kazan")
                .number(18)
                .nationalities(List.of("Russian"))
                .build());
        newPlayers.add(Player.builder()
                .id(new ObjectId())
                .surname("Wilfredo")
                .name("Leon")
                .age(28)
                .height(2.02)
                .spike(380)
                .block(346)
                .position(Position.OUTSIDE_HITTER)
                .currentClub("Modena Volley")
                .number(9)
                .nationalities(List.of("Cuban", "Polish"))
                .build());
        playerRepository.saveAll(newPlayers);
    }

    @Test
    @DisplayName("All players have been founded")
    void checkFindAllPlayers() throws Exception {
        StringBuilder expectedJson = new StringBuilder("[");
        for (PlayerDto player : players) {
            expectedJson.append(converter.convert(player)).append(",");
        }
        expectedJson.deleteCharAt(expectedJson.length() - 1);
        expectedJson.append(']');
        mockMvc.perform(get("/players"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson.toString()));
    }

    @Test
    @DisplayName("Players have been founded by club name")
    void checkFindPlayersByClub() throws Exception {
        String clubName = "Modena Volley";
        String expectedJson = "[" + converter.convert(players.get(2)) + ',' +
                converter.convert(players.get(0)) + ']';
        mockMvc.perform(get("/players")
                        .param("club", clubName))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    @DisplayName("Player has been founded by id")
    void checkFindPlayerById() throws Exception {
        String searchedId = players.get(1).getId();
        String expectedJson = converter.convert(players.get(1));
        mockMvc.perform(get("/player/{playerId}", searchedId))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    @DisplayName("Player has not been founded by id")
    void checkNotFoundPlayerById() throws Exception {
        ObjectId searchedId = new ObjectId();
        mockMvc.perform(get("/player/{playerId}", searchedId.toString()))
                .andExpect(status().isNotFound());
    }

    @AfterAll
    void destroy(@Autowired PlayerRepository playerRepository) {
        playerRepository.deleteAll(newPlayers);
    }
}
