package by.elmax19.app;

import by.elmax19.app.model.mongo.MongoPlayer;
import by.elmax19.app.model.sql.SqlPlayer;
import by.elmax19.app.repository.MongoPlayerRepository;
import by.elmax19.app.repository.SqlPlayerRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record MigrationService(SqlPlayerRepository sqlPlayerRepository, MongoPlayerRepository mongoPlayerRepository) {
    public void migrateSqlDataToMongo() {
        List<MongoPlayer> players = sqlPlayerRepository.findAll().stream()
                .map(this::parseSqlPlayerToMongo)
                .toList();
        mongoPlayerRepository.saveAll(players);
    }

    public MongoPlayer parseSqlPlayerToMongo(SqlPlayer sqlPlayer) {
        return MongoPlayer.builder()
                .id(new ObjectId(sqlPlayer.getId()))
                .surname(sqlPlayer.getSurname())
                .name(sqlPlayer.getName())
                .age(sqlPlayer.getAge())
                .height(sqlPlayer.getHeight())
                .spike(sqlPlayer.getSpike())
                .block(sqlPlayer.getBlock())
                .position(sqlPlayer.getPosition())
                .currentClub(sqlPlayer.getCurrentClub())
                .number(sqlPlayer.getNumber())
                .build();
    }
}
