package by.elmax19.app.runner;

import by.elmax19.app.model.mongo.MongoPlayer;
import by.elmax19.app.model.sql.SqlPlayer;
import by.elmax19.app.service.MigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MigrationRunner implements CommandLineRunner {
    @Autowired
    private MigrationService<SqlPlayer, MongoPlayer> migrationService;

    @Override
    public void run(String... args) {
        migrationService.migrateSqlDataToMongo();
    }
}
