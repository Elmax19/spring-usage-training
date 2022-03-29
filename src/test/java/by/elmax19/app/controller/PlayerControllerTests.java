package by.elmax19.app.controller;

import by.elmax19.app.model.Player;
import by.elmax19.app.model.Position;
import by.elmax19.app.model.dto.PlayerDto;
import by.elmax19.app.repository.PlayerRepository;
import by.elmax19.app.util.ObjectToJsonConverter;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlayerControllerTests {
    private Player firstPlayer;
    private Player secondPlayer;
    private Player thirdPlayer;
    private PlayerDto firstPlayerDto;
    private PlayerDto secondPlayerDto;
    private PlayerDto thirdPlayerDto;
    @Autowired
    private ObjectToJsonConverter converter;
    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public void init(@Autowired PlayerRepository playerRepository) {
        initPlayersData();
        saveTestsData(playerRepository);
    }

    private void initPlayersData() {
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
        String expected = "[" + converter.convert(firstPlayerDto) + ',' +
                converter.convert(secondPlayerDto) + ',' +
                converter.convert(thirdPlayerDto) + ']';

        MvcResult mvcResult = mockMvc.perform(get("/players"))
                .andExpect(status().isOk())
                .andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Players have been founded by club name")
    void checkFindPlayersByClub() throws Exception {
        String clubName = "Modena Volley";
        String expected = "[" + converter.convert(firstPlayerDto) + ',' +
                converter.convert(thirdPlayerDto) + ']';

        MvcResult mvcResult = mockMvc.perform(get("/players")
                        .param("club", clubName))
                .andExpect(status().isOk())
                .andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Player has been founded by id")
    void checkFindPlayerById() throws Exception {
        String searchedId = firstPlayerDto.getId().toString();
        mockMvc.perform(get("/player/{playerId}", searchedId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(searchedId)));
    }

    @Test
    @DisplayName("Player has not been founded by id")
    void checkNotFoundPlayerById() throws Exception {
        ObjectId searchedId = new ObjectId();
        mockMvc.perform(get("/player/{playerId}", searchedId.toString()))
                .andExpect(status().isNotFound());
    }

    private void saveTestsData(PlayerRepository playerRepository) {
        playerRepository.save(firstPlayer);
        playerRepository.save(secondPlayer);
        playerRepository.save(thirdPlayer);
    }
}
