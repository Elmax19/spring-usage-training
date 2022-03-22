package by.elmax19.app;

import by.elmax19.app.model.Player;
import by.elmax19.app.model.Position;
import by.elmax19.app.repository.impl.PlayerRepo;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PlayerIntegrationTests {
    private static PlayerRepo playerRepo;
    @Autowired
    private PlayerRepo playerRepoBean;

    @PostConstruct
    private void init() {
        playerRepo = playerRepoBean;
    }

    public PlayerIntegrationTests() {
    }

    @Test
    @DisplayName("Player has been added")
    void checkPlayerCreation() {
        Player player = Player.builder()
                .surname("Rezende")
                .name("Bruno")
                .age(35)
                .height(1.90)
                .spike(323)
                .block(302)
                .position(Position.SETTER)
                .currentClub("Azimut Modena")
                .number(1)
                .nationalities(List.of("Brazilian"))
                .build();
        long countOfDocumentBeforeCreation = playerRepo.getDocumentsCount();
        create(player);
        assertEquals(countOfDocumentBeforeCreation + 1, playerRepo.getDocumentsCount());
    }

    @Test
    @DisplayName("Player has been deleted")
    void checkPlayerRemoval() {
        Player player = create(Player.builder()
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
        long countOfDocumentBeforeRemoval = playerRepo.getDocumentsCount();
        assertEquals(1, playerRepo.delete(player.getId()).getDeletedCount());
        assertEquals(countOfDocumentBeforeRemoval - 1, playerRepo.getDocumentsCount());
    }

    @Test
    @DisplayName("Player has been found by id")
    void checkPlayerFindingById() {
        Player player = create(Player.builder()
                .id(new ObjectId())
                .surname("Wilfredo")
                .name("Leon")
                .age(28)
                .height(2.02)
                .spike(380)
                .block(346)
                .position(Position.OUTSIDE_HITTER)
                .currentClub("Sir Safety Perugia")
                .number(9)
                .nationalities(List.of("Cuban", "Polish"))
                .build());
        Optional<Player> foundedPlayer = playerRepo.findById(player.getId());
        assertTrue(foundedPlayer.isPresent());
        assertEquals(player, foundedPlayer.get());
    }

    @Test
    @DisplayName("Player has been found by surname and name")
    void checkPlayerFindingBySurnameAndName() {
        Player player = create(Player.builder()
                .surname("Wilfredo")
                .name("Leon")
                .age(28)
                .height(2.02)
                .spike(380)
                .block(346)
                .position(Position.OUTSIDE_HITTER)
                .currentClub("Sir Safety Perugia")
                .number(9)
                .nationalities(List.of("Cuban", "Polish"))
                .build());
        Optional<Player> foundedPlayer = playerRepo.findBySurnameAndName(player.getSurname(), player.getName());
        assertTrue(foundedPlayer.isPresent());
        assertEquals(player, foundedPlayer.get());
    }

    @Test
    @DisplayName("Player has been found by currentClub")
    void checkPlayerFindingByCurrentClub() {
        Player player = create(Player.builder()
                .surname("N'Gapeth")
                .name("Earvin")
                .age(31)
                .height(1.94)
                .spike(358)
                .block(327)
                .position(Position.OUTSIDE_HITTER)
                .currentClub("Modena Volley")
                .number(9)
                .nationalities(List.of("French"))
                .build());
        List<Player> players = playerRepo.findByCurrentClub(player.getCurrentClub());
        assertEquals(1, players.size());
        assertEquals(player, players.get(0));
    }

    @Test
    @DisplayName("Player has been updated")
    void checkPlayerUpdating() {
        Player player = create(Player.builder()
                .surname("N'Gapeth")
                .name("Earvin")
                .age(31)
                .height(1.94)
                .spike(358)
                .block(327)
                .position(Position.OUTSIDE_HITTER)
                .currentClub("Modena Volley")
                .number(9)
                .nationalities(List.of("French"))
                .build());
        player.setAge(32);
        assertEquals(1, playerRepo.update(player).getModifiedCount());
        Optional<Player> foundedPlayer = playerRepo.findById(player.getId());
        assertTrue(foundedPlayer.isPresent());
        assertEquals(player, foundedPlayer.get());
    }

    private Player create(Player player) {
        playerRepo.create(player);
        Optional<Player> createdPlayer = playerRepo.findBySurnameAndName(player.getSurname(), player.getName());
        assertTrue(createdPlayer.isPresent());
        return createdPlayer.get();
    }

    @AfterAll
    public static void cleanUp() {
        playerRepo.deleteAll();
    }
}
