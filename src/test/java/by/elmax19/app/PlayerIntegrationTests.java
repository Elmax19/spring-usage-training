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

    @PostConstruct
    private void init() {
        playerRepo = playerRepoBean;
    }

    @Test
    @DisplayName("First player has been added")
    void checkPlayerCreation() {
        Player player = Player.builder()
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
        long countOfDocumentBeforeCreation = playerRepo.count();
        playerRepo.save(player);
        assertEquals(countOfDocumentBeforeCreation + 1, playerRepo.count());
    }

    @Test
    @DisplayName("Second player has been deleted")
    void checkPlayerRemoval() {
        Player player = playerRepo.save(Player.builder()
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
        long countOfDocumentBeforeRemoval = playerRepo.count();
        playerRepo.delete(player);
        assertEquals(countOfDocumentBeforeRemoval - 1, playerRepo.count());
    }

    @Test
    @DisplayName("Third player has been found by id")
    void checkPlayerFindingById() {
        Player player = playerRepo.save(Player.builder()
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
    @DisplayName("Second player has been found by player Criteria")
    void checkPlayerFindingByCriteria() {
        Player player = playerRepo.save(Player.builder()
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
        Player foundedPlayer = playerRepo.findPlayerBySurnameAndName(player.getSurname(), player.getName());
        assertEquals(player, foundedPlayer);
    }

    @Test
    @DisplayName("Fifth and sixth players have been found by current club Criteria")
    void checkPlayersFindingAllByCriteria() {
        Player firstPlayer = playerRepo.save(Player.builder()
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
                .build());
        Player secondPlayer = playerRepo.save(Player.builder()
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
                .build());
        List<Player> players = playerRepo.findPlayersByCurrentClub(firstPlayer.getCurrentClub());
        assertEquals(2, players.size());
        assertTrue(players.contains(firstPlayer) && players.contains(secondPlayer));
    }

    @Test
    @DisplayName("Fourth player has been updated")
    void checkPlayerUpdating() {
        Player player = playerRepo.save(Player.builder()
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
                .build());
        player.setAge(32);
        playerRepo.save(player);
        Optional<Player> foundedPlayer = playerRepo.findById(player.getId());
        assertTrue(foundedPlayer.isPresent());
        assertEquals(player, foundedPlayer.get());
    }

    @AfterAll
    public static void cleanUp() {
        playerRepo.deleteAll();
    }
}
