package by.elmax19.app;

import by.elmax19.app.model.Position;
import by.elmax19.app.model.mongo.MongoPlayer;
import by.elmax19.app.model.sql.SqlPlayer;
import by.elmax19.app.repository.MongoPlayerRepository;
import by.elmax19.app.repository.SqlPlayerRepository;
import by.elmax19.app.service.MigrationService;
import by.elmax19.app.service.impl.MigrationServiceImpl;
import org.bson.types.ObjectId;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MigrationServiceTest {
    private final List<SqlPlayer> newPlayers = new ArrayList<>();
    @Mock
    private SqlPlayerRepository sqlPlayerRepository;
    @Mock
    private MongoPlayerRepository mongoPlayerRepository;
    @InjectMocks
    private MigrationServiceImpl migrationService;
    @Captor
    private ArgumentCaptor<List<MongoPlayer>> captor;
    private final SqlPlayer sqlPlayer = SqlPlayer.builder()
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
    private final MongoPlayer mongoPlayer = MongoPlayer.builder()
            .id(new ObjectId(sqlPlayer.getId()))
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

    public MigrationServiceTest() {
        for (int i = 0; i < 5; i++) {
            newPlayers.add(createNewPlayer());
        }
    }

    @Test
    @DisplayName("Player has been parsed from SQL to Mongo correct")
    void checkParsingSqlPlayerToMongo() {
        assertEquals(mongoPlayer, migrationService.mapSqlPlayerToMongo(sqlPlayer));
    }

    @Test
    @DisplayName("Players have been migrated")
    void checkPlayersMigration() {
        when(sqlPlayerRepository.findAll()).thenReturn(newPlayers);

        migrationService.migrateSqlDataToMongo();

        verify(mongoPlayerRepository).saveAll(captor.capture());
        assertEquals(newPlayers.size(), captor.getValue().size());
    }

    private SqlPlayer createNewPlayer() {
        EasyRandom generator = new EasyRandom();
        SqlPlayer player = generator.nextObject(SqlPlayer.class);
        player.setId(new ObjectId().toString());
        return player;
    }
}
