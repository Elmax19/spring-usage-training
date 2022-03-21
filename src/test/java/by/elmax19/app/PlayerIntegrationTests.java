package by.elmax19.app;

import by.elmax19.app.model.Player;
import by.elmax19.app.model.Position;
import by.elmax19.app.repository.PlayerRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataMongoTest
public class PlayerIntegrationTests {
    private static PlayerRepository playerRepo;
    @Autowired
    private PlayerRepository playerRepoBean;
    private final Player firstPlayer;
    private Player secondPlayer;
    private Player thirdPlayer;
    private Player fourthPlayer;
    private Player fifthPlayer;
    private Player sixthPlayer;

    @PostConstruct
    private void init() {
        playerRepo = playerRepoBean;
    }

    public PlayerIntegrationTests() {
        firstPlayer = Player.builder()
                .id(new ObjectId())
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
                .currentClub("Sir Safety Perugia")
                .number(9)
                .nationalities(List.of("Cuban", "Polish"))
                .build();
        fourthPlayer = Player.builder()
                .id(new ObjectId())
                .surname("Robertlandy")
                .name("Simon")
                .age(34)
                .height(2.08)
                .spike(387)
                .block(350)
                .position(Position.MIDDLE_BLOCKER)
                .currentClub("Cucine Lube Civitanova")
                .number(13)
                .nationalities(List.of("Cuban"))
                .build();
        fifthPlayer = Player.builder()
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
                .nationalities(List.of("Dutch"))
                .build();
        sixthPlayer = Player.builder()
                .id(new ObjectId())
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
        long countOfDocumentBeforeCreation = playerRepo.count();
        playerRepo.save(firstPlayer);
        assertEquals(countOfDocumentBeforeCreation + 1, playerRepo.count());
    }

    @Test
    @DisplayName("Second player has been deleted")
    void checkPlayerRemoval() {
        secondPlayer = playerRepo.save(secondPlayer);
        long countOfDocumentBeforeRemoval = playerRepo.count();
        playerRepo.delete(secondPlayer);
        assertEquals(countOfDocumentBeforeRemoval - 1, playerRepo.count());
    }

    @Test
    @DisplayName("Third player has been found by id")
    void checkPlayerFindingById() {
        thirdPlayer = playerRepo.save(thirdPlayer);
        playerRepo.findAll().forEach(player -> System.out.println(player.toString()));
        Optional<Player> foundedPlayer = playerRepo.findById(thirdPlayer.getId());
        assertTrue(foundedPlayer.isPresent());
        assertEquals(thirdPlayer, foundedPlayer.get());
    }

    @Test
    @DisplayName("Second player has been found by player Criteria")
    void checkPlayerFindingByCriteria() {
        secondPlayer = playerRepo.save(secondPlayer);
        Player foundedPlayer = playerRepo.findPlayerBySurnameAndName(secondPlayer.getSurname(), secondPlayer.getName());
        assertEquals(secondPlayer, foundedPlayer);
    }

    @Test
    @DisplayName("Fifth and sixth players have been found by current club Criteria")
    void checkPlayersFindingAllByCriteria() {
        fifthPlayer = playerRepo.save(fifthPlayer);
        sixthPlayer = playerRepo.save(sixthPlayer);
        List<Player> players = playerRepo.findPlayersByCurrentClub(fifthPlayer.getCurrentClub());
        assertEquals(2, players.size());
        assertTrue(players.contains(fifthPlayer) && players.contains(sixthPlayer));
    }

    @Test
    @DisplayName("Fourth player has been updated")
    void checkPlayerUpdating() {
        fourthPlayer = playerRepo.save(fourthPlayer);
        fourthPlayer.setAge(32);
        playerRepo.save(fourthPlayer);
        Optional<Player> foundedPlayer = playerRepo.findById(fourthPlayer.getId());
        assertTrue(foundedPlayer.isPresent());
        assertEquals(fourthPlayer, foundedPlayer.get());
    }

    @AfterAll
    public static void cleanUp() {
        playerRepo.deleteAll();
    }
}
