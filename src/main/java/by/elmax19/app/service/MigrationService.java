package by.elmax19.app.service;

/**
 * Data migration service interface from one database to another
 *
 * @param <T1> first database object type, that will be migrated
 * @param <T2> second database object type, that will be a result of migration
 * @author Elmax19
 * @version 1.0
 */
public interface MigrationService<T1, T2> {
    void migrateSqlDataToMongo();

    T2 mapSqlPlayerToMongo(T1 objectToMap);
}
