package by.elmax19.app.controller;

import by.elmax19.app.model.Player;
import by.elmax19.app.model.Position;
import by.elmax19.app.model.dto.PlayerDto;
import by.elmax19.app.repository.PlayerRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlayerControllerTests {
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("All players have been founded")
    void checkFindAllPlayers() throws Exception {
        List<PlayerDto> expectedPlayers = initThreePlayers();

        MvcResult mvcResult = mockMvc.perform(get("/players"))
                .andExpect(status().isOk())
                .andReturn();
        List<PlayerDto> actualPlayers = new ObjectMapper().readValue(
                mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
                });

        assertEquals(3, actualPlayers.size());
        comparePlayers(expectedPlayers.get(0), actualPlayers.get(0));
        comparePlayers(expectedPlayers.get(1), actualPlayers.get(1));
        comparePlayers(expectedPlayers.get(2), actualPlayers.get(2));
    }

    @Test
    @DisplayName("Players have been founded by club name")
    void checkFindPlayersByClub() throws Exception {
        String clubName = "Modena Volley";
        List<PlayerDto> expectedPlayers = initThreePlayers();

        MvcResult mvcResult = mockMvc.perform(get("/players")
                        .param("club", clubName))
                .andExpect(status().isOk())
                .andReturn();
        List<PlayerDto> actualPlayers = new ObjectMapper().readValue(
                mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
                });

        assertEquals(2, actualPlayers.size());
        comparePlayers(expectedPlayers.get(0), actualPlayers.get(0));
        comparePlayers(expectedPlayers.get(2), actualPlayers.get(1));
    }

    @Test
    @DisplayName("Player has been founded by id")
    void checkFindPlayerById() throws Exception {
        Player player = Player.builder()
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
        playerRepository.save(player);
        PlayerDto playerDto = PlayerDto.builder()
                .id(player.getId().toString())
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

        mockMvc.perform(get("/player/{playerId}", playerDto.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(playerDto.getId())))
                .andExpect(jsonPath("$.fullName", is(player.getName() + ' ' + player.getSurname())))
                .andExpect(jsonPath("$.age", is(player.getAge())))
                .andExpect(jsonPath("$.height", is(player.getHeight())))
                .andExpect(jsonPath("$.spike", is(player.getSpike())))
                .andExpect(jsonPath("$.block", is(player.getBlock())))
                .andExpect(jsonPath("$.position", is(player.getPosition().name())))
                .andExpect(jsonPath("$.club", is(player.getCurrentClub())))
                .andExpect(jsonPath("$.number", is(player.getNumber())))
                .andExpect(jsonPath("$.nationalities", is(player.getNationalities())));
    }

    @Test
    @DisplayName("Player has not been founded by id")
    void checkNotFoundPlayerById() throws Exception {
        ObjectId searchedId = new ObjectId();
        mockMvc.perform(get("/player/{playerId}", searchedId.toString()))
                .andExpect(status().isNotFound());
    }

    @AfterEach
    void clearDatabaseData(@Autowired PlayerRepository playerRepository) {
        playerRepository.deleteAll();
    }

    private void comparePlayers(PlayerDto firstPlayer, PlayerDto secondPlayer) {
        assertEquals(firstPlayer.getId(), secondPlayer.getId());
        assertEquals(firstPlayer.getFullName(), secondPlayer.getFullName());
        assertEquals(firstPlayer.getAge(), secondPlayer.getAge());
        assertEquals(firstPlayer.getHeight(), secondPlayer.getHeight());
        assertEquals(firstPlayer.getSpike(), secondPlayer.getSpike());
        assertEquals(firstPlayer.getBlock(), secondPlayer.getBlock());
        assertEquals(firstPlayer.getPosition(), secondPlayer.getPosition());
        assertEquals(firstPlayer.getClub(), secondPlayer.getClub());
        assertEquals(firstPlayer.getNumber(), secondPlayer.getNumber());
        assertEquals(firstPlayer.getNationalities(), secondPlayer.getNationalities());
    }

    private List<PlayerDto> initThreePlayers() {
        Player firstPlayer = Player.builder()
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
        Player secondPlayer = Player.builder()
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
        Player thirdPlayer = Player.builder()
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
        playerRepository.saveAll(Arrays.asList(firstPlayer, secondPlayer, thirdPlayer));

        List<PlayerDto> playerDtos = new ArrayList<>();
        playerDtos.add(PlayerDto.builder()
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
                .build());
        playerDtos.add(PlayerDto.builder()
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
                .build());
        playerDtos.add(PlayerDto.builder()
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
                .build());
        return playerDtos;
    }
}
