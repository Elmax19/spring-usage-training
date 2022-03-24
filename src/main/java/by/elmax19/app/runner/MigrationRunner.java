package by.elmax19.app.runner;

import by.elmax19.app.MigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MigrationRunner implements CommandLineRunner {
    @Autowired
    private MigrationService migrationService;

    @Override
    public void run(String... args) {
        migrationService.migrateSqlDataToMongo();
    }
}
