package by.elmax19.app.repository;

import by.elmax19.app.model.Player;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataMongoTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlayerRepoTests {
    @Autowired
    private PlayerRepository playerRepo;

    @Test
    @DisplayName("Player has been added")
    void checkPlayerCreation() {
        Player player = createNewPlayer();
        long countOfDocumentBeforeCreation = playerRepo.count();
        playerRepo.save(player);
        assertEquals(countOfDocumentBeforeCreation + 1, playerRepo.count());
    }

    @Test
    @DisplayName("Player has been deleted")
    void checkPlayerRemoval() {
        Player player = createNewPlayer();
        playerRepo.save(player);
        long countOfDocumentBeforeRemoval = playerRepo.count();
        playerRepo.delete(player);
        assertEquals(countOfDocumentBeforeRemoval - 1, playerRepo.count());
    }

    @Test
    @DisplayName("Player has been found by id")
    void checkPlayerFindingById() {
        Player player = createNewPlayer();
        playerRepo.save(player);
        Optional<Player> foundedPlayer = playerRepo.findById(player.getId());
        assertTrue(foundedPlayer.isPresent());
        assertEquals(player, foundedPlayer.get());
    }

    @Test
    @DisplayName("Player has been found by player Criteria")
    void checkPlayerFindingByCriteria() {
        Player player = createNewPlayer();
        player.setSurname("Wilfredo");
        player.setName("Leon");
        playerRepo.save(player);
        Player foundedPlayer = playerRepo.findPlayerBySurnameAndName(player.getSurname(), player.getName());
        assertEquals(player, foundedPlayer);
    }

    @Test
    @DisplayName("Players have been found by current club Criteria")
    void checkPlayersFindingAllByCriteria() {
        Player firstPlayer = createNewPlayer();
        Player secondPlayer = createNewPlayer();
        firstPlayer.setCurrentClub("Club name");
        secondPlayer.setCurrentClub("Club name");
        playerRepo.save(firstPlayer);
        playerRepo.save(secondPlayer);
        List<Player> players = playerRepo.findPlayersByCurrentClubOrderByNumber(firstPlayer.getCurrentClub());
        assertEquals(2, players.size());
        assertTrue(players.contains(firstPlayer) && players.contains(secondPlayer));
    }

    @Test
    @DisplayName("Player has been updated")
    void checkPlayerUpdating() {
        Player player = createNewPlayer();
        playerRepo.save(player);
        player.setAge(32);
        playerRepo.save(player);
        Optional<Player> foundedPlayer = playerRepo.findById(player.getId());
        assertTrue(foundedPlayer.isPresent());
        assertEquals(player, foundedPlayer.get());
    }

    private Player createNewPlayer() {
        EasyRandom generator = new EasyRandom();
        return generator.nextObject(Player.class);
    }

    @AfterAll
    public void cleanUp() {
        playerRepo.deleteAll();
    }
}
