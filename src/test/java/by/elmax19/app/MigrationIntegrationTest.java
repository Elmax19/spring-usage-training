package by.elmax19.app;

import by.elmax19.app.model.mongo.MongoPlayer;
import by.elmax19.app.model.sql.SqlPlayer;
import by.elmax19.app.repository.MongoPlayerRepository;
import by.elmax19.app.repository.SqlPlayerRepository;
import by.elmax19.app.service.MigrationService;
import org.bson.types.ObjectId;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MigrationIntegrationTest {
    private final List<SqlPlayer> newPlayers = new ArrayList<>();
    @Autowired
    private SqlPlayerRepository sqlPlayerRepository;
    @Autowired
    private MongoPlayerRepository mongoPlayerRepository;
    @Autowired
    private MigrationService<SqlPlayer, MongoPlayer> migrationService;

    @BeforeAll
    void addNewPlayer() {
        for (int i = 0; i < 5; i++) {
            newPlayers.add(createNewPlayer());
        }
        sqlPlayerRepository.saveAll(newPlayers);
    }

    private SqlPlayer createNewPlayer() {
        EasyRandom generator = new EasyRandom();
        SqlPlayer player = generator.nextObject(SqlPlayer.class);
        player.setId(new ObjectId().toString());
        return player;
    }

    @Test
    void checkPlayerMigration() {
        migrationService.migrateSqlDataToMongo();

        assertEquals(5, mongoPlayerRepository.count());
        List<MongoPlayer> actualPlayers = mongoPlayerRepository.findAll();
        assertTrue(actualPlayers.containsAll(
                newPlayers.stream()
                        .map(migrationService::mapSqlPlayerToMongo)
                        .toList()));
    }

    @AfterAll
    void deleteCreatedData() {
        sqlPlayerRepository.deleteAll(newPlayers);
        mongoPlayerRepository.deleteAll(
                newPlayers.stream()
                        .map(migrationService::mapSqlPlayerToMongo)
                        .toList());
    }
}
