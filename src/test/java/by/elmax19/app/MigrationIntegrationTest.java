package by.elmax19.app;

import by.elmax19.app.model.Position;
import by.elmax19.app.model.mongo.MongoPlayer;
import by.elmax19.app.model.sql.SqlPlayer;
import by.elmax19.app.repository.MongoPlayerRepository;
import by.elmax19.app.repository.SqlPlayerRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MigrationIntegrationTest {
    @Autowired
    private SqlPlayerRepository sqlPlayerRepository;
    @Autowired
    private MongoPlayerRepository mongoPlayerRepository;
    @Autowired
    private MigrationService migrationService;
    private SqlPlayer newPlayer;

    @BeforeAll
    void addNewPlayer() {
        newPlayer = createSqlPlayer();
        sqlPlayerRepository.save(newPlayer);
    }

    private SqlPlayer createSqlPlayer() {
        return SqlPlayer.builder()
                .id(new ObjectId().toString())
                .surname("Abdel-Aziz")
                .name("Nimir")
                .age(30)
                .height(2.01)
                .spike(360)
                .block(340)
                .position(Position.OPPOSITE_HITTER)
                .currentClub("Modena Volley")
                .number(14)
                .build();
    }

    @Test
    void checkPlayerMigration() {
        MongoPlayer newMongoPlayer = migrationService.parseSqlPlayerToMongo(newPlayer);
        long countBeforeMigration = mongoPlayerRepository.count();

        migrationService.migrateSqlDataToMongo();

        assertEquals(countBeforeMigration + 1, mongoPlayerRepository.count());
        Optional<MongoPlayer> actualMongoPlayer = mongoPlayerRepository.findById(newMongoPlayer.getId());
        assertTrue(actualMongoPlayer.isPresent());
        assertEquals(newMongoPlayer, actualMongoPlayer.get());
    }

    @AfterAll
    void deleteCreatedData() {
        sqlPlayerRepository.delete(newPlayer);
        mongoPlayerRepository.deleteAll();
    }
}
