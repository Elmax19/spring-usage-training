package by.elmax19.app;

import by.elmax19.app.model.Player;
import by.elmax19.app.model.Position;
import by.elmax19.app.repository.impl.PlayerRepo;
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
    private final Player firstPlayer;
    private Player secondPlayer;
    private Player thirdPlayer;
    private Player fourthPlayer;

    @PostConstruct
    private void init() {
        playerRepo = playerRepoBean;
    }

    public PlayerIntegrationTests() {
        this.firstPlayer = Player.builder()
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
        this.secondPlayer = Player.builder()
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
                .build();
        this.thirdPlayer = Player.builder()
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
                .build();
        this.fourthPlayer = Player.builder()
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
                .build();
    }

    @Test
    @DisplayName("First player has been added")
    void checkPlayerCreation() {
        long countOfDocumentBeforeCreation = playerRepo.getDocumentsCount();
        Optional<Player> newPlayer = playerRepo.create(firstPlayer);
        assertTrue(newPlayer.isPresent());
        assertEquals(countOfDocumentBeforeCreation + 1, playerRepo.getDocumentsCount());
    }

    @Test
    @DisplayName("Second player has been deleted")
    void checkPlayerRemoval() {
        secondPlayer = create(secondPlayer);
        long countOfDocumentBeforeRemoval = playerRepo.getDocumentsCount();
        assertEquals(1, playerRepo.delete(secondPlayer.getId()).getDeletedCount());
        assertEquals(countOfDocumentBeforeRemoval - 1, playerRepo.getDocumentsCount());
    }

    @Test
    @DisplayName("Third player has been found by id")
    void checkPlayerFindingById() {
        thirdPlayer = create(thirdPlayer);
        Optional<Player> foundedPlayer = playerRepo.findById(thirdPlayer.getId());
        assertTrue(foundedPlayer.isPresent());
        assertEquals(thirdPlayer, foundedPlayer.get());
    }

    @Test
    @DisplayName("Third player has been found by surname and name")
    void checkPlayerFindingBySurnameAndName() {
        thirdPlayer = create(thirdPlayer);
        Optional<Player> foundedPlayer = playerRepo.findBySurnameAndName(thirdPlayer.getSurname(), thirdPlayer.getName());
        assertTrue(foundedPlayer.isPresent());
        assertEquals(thirdPlayer, foundedPlayer.get());
    }

    @Test
    @DisplayName("Forth player has been found by currentClub")
    void checkPlayerFindingByCurrentClub() {
        fourthPlayer = create(fourthPlayer);
        List<Player> players = playerRepo.findByCurrentClub(fourthPlayer.getCurrentClub());
        assertEquals(1, players.size());
        assertEquals(fourthPlayer, players.get(0));
    }

    @Test
    @DisplayName("Fourth player has been updated")
    void checkPlayerUpdating() {
        fourthPlayer = create(fourthPlayer);
        fourthPlayer.setAge(32);
        assertEquals(1, playerRepo.update(fourthPlayer).getModifiedCount());
        Optional<Player> foundedPlayer = playerRepo.findById(fourthPlayer.getId());
        assertTrue(foundedPlayer.isPresent());
        assertEquals(fourthPlayer, foundedPlayer.get());
    }

    private Player create(Player player) {
        Optional<Player> createdPlayer = playerRepo.create(player);
        assertTrue(createdPlayer.isPresent());
        return createdPlayer.get();
    }

    @AfterAll
    public static void cleanUp() {
        playerRepo.deleteAll();
    }
}
