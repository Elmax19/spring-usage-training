package by.elmax19.app.repository;

import by.elmax19.app.exception.PlayerNotFoundException;
import by.elmax19.app.mapper.PlayerMapper;
import by.elmax19.app.model.Player;
import by.elmax19.app.model.Position;
import by.elmax19.app.model.dto.PlayerDto;
import by.elmax19.app.service.impl.PlayerServiceImpl;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {
    private final List<Player> players = new ArrayList<>();
    private final List<PlayerDto> playerDtos = new ArrayList<>();
    @Mock
    private PlayerMapper playerMapper;
    @Mock
    private PlayerRepository playerRepository;
    @InjectMocks
    private PlayerServiceImpl playerService;

    public PlayerServiceTest() {
        initPlayersList();
        initPlayerDtosList();
    }

    @Test
    @DisplayName("Player has been founded by id")
    void checkFindById() {
        ObjectId id = players.get(1).getId();
        when(playerRepository.findById(id)).thenReturn(Optional.of(players.get(1)));
        when(playerMapper.convertToDto(players.get(1))).thenReturn(playerDtos.get(1));

        PlayerDto actual = playerService.findById(id.toString());

        assertEquals(playerDtos.get(1), actual);
    }

    @Test
    @DisplayName("Exception thrown when no Player with such id")
    void checkExceptionFindById() {
        ObjectId id = new ObjectId();
        when(playerRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PlayerNotFoundException.class, () -> playerService.findById(id.toString()));
    }

    @Test
    @DisplayName("All players have been founded")
    void checkFindAll() {
        Player player = Player.builder().build();
        when(playerRepository.findAll(Example.of(player))).thenReturn(players);
        when(playerMapper.convertListToDto(players)).thenReturn(playerDtos);

        List<PlayerDto> actual = playerService.findAll(Example.of(player));

        assertEquals(actual.size(), playerDtos.size());
        assertTrue(playerDtos.containsAll(actual));
    }

    @Test
    @DisplayName("All \"Modena Volley\" players have been founded")
    void checkFindByClub() {
        String clubName = "Modena Volley";
        Player player = Player.builder().currentClub(clubName).build();
        List<Player> searchedPlayers = Arrays.asList(players.get(2), players.get(0));
        when(playerRepository.findAll(Example.of(player)))
                .thenReturn(searchedPlayers);
        when(playerMapper.convertListToDto(searchedPlayers))
                .thenReturn(Arrays.asList(playerDtos.get(2), playerDtos.get(0)));

        List<PlayerDto> actual = playerService.findAll(Example.of(player));

        assertEquals(searchedPlayers.size(), actual.size());
        assertTrue(playerDtos.containsAll(actual));
    }

    private void initPlayersList() {
        players.add(Player.builder()
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
                .salary(BigDecimal.valueOf(1500))
                .build());
        players.add(Player.builder()
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
                .salary(BigDecimal.valueOf(950))
                .build());
        players.add(Player.builder()
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
                .salary(BigDecimal.valueOf(1200))
                .build());
    }

    private void initPlayerDtosList() {
        playerDtos.add(PlayerDto.builder()
                .id(players.get(0).getId().toString())
                .fullName("Nimir Abdel-Aziz")
                .age(30)
                .height(2.01)
                .spike(360)
                .block(340)
                .position("OPPOSITE_HITTER")
                .club("Modena Volley")
                .number(14)
                .build());
        playerDtos.add(PlayerDto.builder()
                .id(players.get(1).getId().toString())
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
                .id(players.get(2).getId().toString())
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
    }
}
