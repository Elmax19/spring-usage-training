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
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PlayerIntegrationTests {
    private static PlayerRepo playerRepo;
    @Autowired
    private PlayerRepo playerRepoBean;
    private final Player firstPlayer;
    private final Player secondPlayer;
    private final Player thirdPlayer;
    private final Player fourthPlayer;

    @PostConstruct
    private void init() {
        playerRepo = playerRepoBean;
    }

    public PlayerIntegrationTests() {
        this.firstPlayer = new Player(
                "Rezende",
                "Bruno",
                35,
                1.90,
                323,
                302,
                Position.SETTER,
                "Azimut Modena",
                1,
                List.of("Brazilian"));
        this.secondPlayer = new Player(
                "Mikhaylov",
                "Maxim",
                33,
                2.02,
                360,
                340,
                Position.OPPOSITE_HITTER,
                "Zenit Kazan",
                18,
                List.of("Russian"));
        this.thirdPlayer = new Player(
                "Wilfredo",
                "Leon",
                28,
                2.02,
                380,
                346,
                Position.OUTSIDE_HITTER,
                "Sir Safety Perugia",
                9,
                List.of("Cuban", "Polish"));
        this.fourthPlayer = new Player(
                "N'Gapeth",
                "Earvin",
                31,
                1.94,
                358,
                327,
                Position.OUTSIDE_HITTER,
                "Modena Volley",
                9,
                List.of("French"));
    }

    @Test
    @DisplayName("First player has been added")
    void checkPlayerCreation() throws IOException {
        long countOfDocumentBeforeCreation = playerRepo.getDocumentsCount();
        assertEquals(firstPlayer, playerRepo.create(firstPlayer));
        assertEquals(countOfDocumentBeforeCreation + 1, playerRepo.getDocumentsCount());
    }

    @Test
    @DisplayName("Second player has been deleted")
    void checkPlayerRemoval() {
        assertNotNull(playerRepo.create(secondPlayer));
        long countOfDocumentBeforeRemoval = playerRepo.getDocumentsCount();
        assertEquals(1, playerRepo.delete(secondPlayer.getId()).getDeletedCount());
        assertEquals(countOfDocumentBeforeRemoval - 1, playerRepo.getDocumentsCount());
    }

    @Test
    @DisplayName("Third player has been found")
    void checkPlayerFinding() {
        assertNotNull(playerRepo.create(thirdPlayer));
        Optional<Player> foundedPlayer = playerRepo.findById(thirdPlayer.getId());
        assertTrue(foundedPlayer.isPresent());
        assertEquals(thirdPlayer, foundedPlayer.get());
    }

    @Test
    @DisplayName("Fourth player has been updated")
    void checkPlayerUpdating() {
        assertNotNull(playerRepo.create(fourthPlayer));
        fourthPlayer.setAge(32);
        assertEquals(1, playerRepo.update(fourthPlayer).getModifiedCount());
        Optional<Player> foundedPlayer = playerRepo.findById(fourthPlayer.getId());
        assertTrue(foundedPlayer.isPresent());
        assertEquals(fourthPlayer, foundedPlayer.get());
    }

    @AfterAll
    public static void cleanUp() {
        playerRepo.deleteAll();
    }
}
