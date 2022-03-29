package by.elmax19.app.controller;

import by.elmax19.app.model.Player;
import by.elmax19.app.model.Position;
import by.elmax19.app.model.dto.PlayerDto;
import by.elmax19.app.repository.PlayerRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PlayerControllerTests {
    private static Player firstPlayer;
    private static Player secondPlayer;
    private static Player thirdPlayer;
    private static PlayerDto firstPlayerDto;
    private static PlayerDto secondPlayerDto;
    private static PlayerDto thirdPlayerDto;
    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void init(@Autowired PlayerRepository playerRepository) {
        initPlayersData();
        saveTestsData(playerRepository);
    }

    private static void initPlayersData() {
        firstPlayer = Player.builder()
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
                .nationalities(List.of("French"))
                .salary(BigDecimal.valueOf(1500))
                .build();
        secondPlayer = Player.builder()
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
                .salary(BigDecimal.valueOf(1200))
                .build();
        thirdPlayer = Player.builder()
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
                .salary(BigDecimal.valueOf(950))
                .build();
        firstPlayerDto = PlayerDto.builder()
                .id(firstPlayer.getId().toString())
                .fullName("Nimir Abdel-Aziz")
                .age(30)
                .height(2.01)
                .spike(360)
                .block(340)
                .position("OPPOSITE_HITTER")
                .club("Modena Volley")
                .number(14)
                .nationalities(List.of("French"))
                .build();
        secondPlayerDto = PlayerDto.builder()
                .id(secondPlayer.getId().toString())
                .fullName("Maxim Mikhaylov")
                .age(33)
                .height(2.02)
                .spike(360)
                .block(340)
                .position("OPPOSITE_HITTER")
                .club("Zenit Kazan")
                .number(18)
                .nationalities(List.of("Russian"))
                .build();
        thirdPlayerDto = PlayerDto.builder()
                .id(thirdPlayer.getId().toString())
                .fullName("Leon Wilfredo")
                .age(28)
                .height(2.02)
                .spike(380)
                .block(346)
                .position("OUTSIDE_HITTER")
                .club("Modena Volley")
                .number(9)
                .nationalities(List.of("Cuban", "Polish"))
                .build();
    }

    @Test
    @DisplayName("All players have been founded")
    void checkFindAllPlayers() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/players"))
                .andExpect(status().isOk())
                .andReturn();
        List<PlayerDto> actualPlayers = new ObjectMapper().readValue(
                mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
                });

        assertEquals(3, actualPlayers.size());
        assertEquals(firstPlayerDto, actualPlayers.get(0));
        assertEquals(secondPlayerDto, actualPlayers.get(1));
        assertEquals(thirdPlayerDto, actualPlayers.get(2));
    }

    @Test
    @DisplayName("Players have been founded by club name")
    void checkFindPlayersByClub() throws Exception {
        String clubName = "Modena Volley";

        MvcResult mvcResult = mockMvc.perform(get("/players")
                        .param("club", clubName))
                .andExpect(status().isOk())
                .andReturn();
        List<PlayerDto> actualPlayers = new ObjectMapper().readValue(
                mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
                });

        assertEquals(2, actualPlayers.size());
        assertEquals(firstPlayerDto, actualPlayers.get(0));
        assertEquals(thirdPlayerDto, actualPlayers.get(1));
    }

    @Test
    @DisplayName("Player has been founded by id")
    void checkFindPlayerById() throws Exception {
        String searchedId = firstPlayerDto.getId();
        mockMvc.perform(get("/player/{playerId}", searchedId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(searchedId)))
                .andExpect(jsonPath("$.fullName", is(firstPlayer.getName() + ' ' + firstPlayer.getSurname())))
                .andExpect(jsonPath("$.age", is(firstPlayer.getAge())))
                .andExpect(jsonPath("$.height", is(firstPlayer.getHeight())))
                .andExpect(jsonPath("$.spike", is(firstPlayer.getSpike())))
                .andExpect(jsonPath("$.block", is(firstPlayer.getBlock())))
                .andExpect(jsonPath("$.position", is(firstPlayer.getPosition().name())))
                .andExpect(jsonPath("$.club", is(firstPlayer.getCurrentClub())))
                .andExpect(jsonPath("$.number", is(firstPlayer.getNumber())))
                .andExpect(jsonPath("$.nationalities", is(firstPlayer.getNationalities())));
    }

    @Test
    @DisplayName("Player has not been founded by id")
    void checkNotFoundPlayerById() throws Exception {
        ObjectId searchedId = new ObjectId();
        mockMvc.perform(get("/player/{playerId}", searchedId.toString()))
                .andExpect(status().isNotFound());
    }

    private static void saveTestsData(PlayerRepository playerRepository) {
        playerRepository.save(firstPlayer);
        playerRepository.save(secondPlayer);
        playerRepository.save(thirdPlayer);
    }
}
