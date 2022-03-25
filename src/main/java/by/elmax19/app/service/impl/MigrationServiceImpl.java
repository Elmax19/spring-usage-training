package by.elmax19.app.service.impl;

import by.elmax19.app.model.mongo.MongoPlayer;
import by.elmax19.app.model.sql.SqlPlayer;
import by.elmax19.app.repository.MongoPlayerRepository;
import by.elmax19.app.repository.SqlPlayerRepository;
import by.elmax19.app.service.MigrationService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MigrationServiceImpl implements MigrationService<SqlPlayer, MongoPlayer> {
    private final SqlPlayerRepository sqlPlayerRepository;
    private final MongoPlayerRepository mongoPlayerRepository;

    @Override
    public void migrateSqlDataToMongo() {
        List<MongoPlayer> players = sqlPlayerRepository.findAll().stream()
                .map(this::mapSqlPlayerToMongo)
                .toList();
        mongoPlayerRepository.saveAll(players);
    }

    @Override
    public MongoPlayer mapSqlPlayerToMongo(SqlPlayer sqlPlayer) {
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
