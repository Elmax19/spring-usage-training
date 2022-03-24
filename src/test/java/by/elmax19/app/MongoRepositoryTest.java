package by.elmax19.app;

import by.elmax19.app.model.mongo.MongoPlayer;
import by.elmax19.app.repository.MongoPlayerRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataMongoTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MongoRepositoryTest {
    @Autowired
    MongoPlayerRepository playerRepository;

    @Test
    @DisplayName("Player has been added")
    void checkPlayerCreation() {
        MongoPlayer player = createNewPlayer();
        long countOfDocumentBeforeCreation = playerRepository.count();
        playerRepository.save(player);
        assertEquals(countOfDocumentBeforeCreation + 1, playerRepository.count());
    }

    @Test
    @DisplayName("Player has been deleted")
    void checkPlayerRemoval() {
        MongoPlayer player = createNewPlayer();
        playerRepository.save(player);
        long countOfDocumentBeforeRemoval = playerRepository.count();
        playerRepository.delete(player);
        assertEquals(countOfDocumentBeforeRemoval - 1, playerRepository.count());
    }

    @Test
    @DisplayName("Player has been found by id")
    void checkPlayerFindingById() {
        MongoPlayer player = createNewPlayer();
        playerRepository.save(player);
        Optional<MongoPlayer> foundedPlayer = playerRepository.findById(player.getId());
        assertTrue(foundedPlayer.isPresent());
        assertEquals(player, foundedPlayer.get());
    }

    @Test
    @DisplayName("saveAll() method works faster then forEach(save())")
    void checkTimeOfSavingEntities() {
        List<MongoPlayer> players = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            players.add(createNewPlayer());
        }
        long time = System.currentTimeMillis();
        players.forEach(playerRepository::save);
        long timeOfSaveMethod = System.currentTimeMillis() - time;
        time = System.currentTimeMillis();
        playerRepository.saveAll(players);
        long timeOfSaveAllMethod = System.currentTimeMillis() - time;
        System.out.println("timeOfSaveMethod = " + timeOfSaveMethod);
        System.out.println("timeOfSaveAllMethod = " + timeOfSaveAllMethod);
        assertTrue(timeOfSaveAllMethod < timeOfSaveMethod);
    }

    @AfterAll
    public void cleanUp() {
        playerRepository.deleteAll();
    }

    private MongoPlayer createNewPlayer() {
        EasyRandom generator = new EasyRandom();
        return generator.nextObject(MongoPlayer.class);
    }
}
