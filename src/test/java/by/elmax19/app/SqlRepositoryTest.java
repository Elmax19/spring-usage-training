package by.elmax19.app;

import by.elmax19.app.model.sql.SqlPlayer;
import by.elmax19.app.repository.SqlPlayerRepository;
import org.bson.types.ObjectId;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SqlRepositoryTest {
    @Autowired
    private SqlPlayerRepository playerRepository;

    @Test
    @DisplayName("save() method should insert a new player")
    void checkSaveMethod() {
        long countBeforeSaving = playerRepository.count();
        SqlPlayer newPlayer = createNewPlayer();

        playerRepository.save(newPlayer);

        assertEquals(countBeforeSaving + 1, playerRepository.count());
        Optional<SqlPlayer> actualPlayer = playerRepository.findById(newPlayer.getId());
        assertTrue(actualPlayer.isPresent());
        assertEquals(newPlayer, actualPlayer.get());

        playerRepository.delete(newPlayer);
    }

    @Test
    @DisplayName("findAll() method should return 3 players")
    void checkFindAllMethod() {
        long countBeforeSaving = playerRepository.count();
        List<SqlPlayer> newPlayers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            newPlayers.add(createNewPlayer());
        }
        playerRepository.saveAll(newPlayers);

        List<SqlPlayer> actualPlayers = playerRepository.findAll();

        assertEquals(countBeforeSaving + 3, actualPlayers.size());
        assertTrue(actualPlayers.containsAll(newPlayers));

        playerRepository.deleteAll(newPlayers);
    }

    @Test
    @DisplayName("delete() method should delete a created player")
    void checkDeleteMethod() {
        SqlPlayer newPlayer = createNewPlayer();
        playerRepository.save(newPlayer);
        long countBeforeDeleting = playerRepository.count();

        playerRepository.delete(newPlayer);

        assertEquals(countBeforeDeleting - 1, playerRepository.count());
        Optional<SqlPlayer> actualPlayer = playerRepository.findById(newPlayer.getId());
        assertTrue(actualPlayer.isEmpty());
    }

    private SqlPlayer createNewPlayer() {
        EasyRandom generator = new EasyRandom();
        SqlPlayer player = generator.nextObject(SqlPlayer.class);
        player.setId(new ObjectId().toString());
        return player;
    }
}
