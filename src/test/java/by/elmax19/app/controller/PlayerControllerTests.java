package by.elmax19.app.controller;

import by.elmax19.app.ObjectToJsonConverter;
import by.elmax19.app.exception.NoEntityWithSuchId;
import by.elmax19.app.model.Position;
import by.elmax19.app.model.dto.PlayerDto;
import by.elmax19.app.service.PlayerService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlayerController.class)
public class PlayerControllerTests {
    private final List<PlayerDto> players = new ArrayList<>();
    private final ObjectToJsonConverter converter = new ObjectToJsonConverter();
    @MockBean
    private PlayerService playerService;
    @Autowired
    private MockMvc mockMvc;

    public PlayerControllerTests() {
        players.add(PlayerDto.builder()
                .id(new ObjectId().toString())
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
        players.add(PlayerDto.builder()
                .id(new ObjectId().toString())
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
        players.add(PlayerDto.builder()
                .id(new ObjectId().toString())
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
    }

    @Test
    @DisplayName("All players have been founded")
    void checkFindAllPlayers() throws Exception {
        when(playerService.findAll()).thenReturn(players);
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
        when(playerService.findByClub(clubName))
                .thenReturn(Arrays.asList(players.get(2), players.get(0)));
        String expectedJson = "[" + converter.convert(players.get(2)) + ',' +
                converter.convert(players.get(0)) + ']';
        mockMvc.perform(get("/{clubName}/players", clubName))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    @DisplayName("Player has been founded by id")
    void checkFindPlayerById() throws Exception {
        String searchedId = players.get(1).getId();
        when(playerService.findById(new ObjectId(searchedId))).thenReturn(players.get(1));
        String expectedJson = converter.convert(players.get(1));
        mockMvc.perform(get("/player/{id}", searchedId))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    @DisplayName("Player has not been founded by id")
    void checkNotFoundPlayerById() throws Exception {
        ObjectId searchedId = new ObjectId();
        when(playerService.findById(searchedId)).thenThrow(new NoEntityWithSuchId("There isn't player with id: " + searchedId));
        mockMvc.perform(get("/player/{id}", searchedId.toString()))
                .andExpect(status().is5xxServerError());
    }
}
