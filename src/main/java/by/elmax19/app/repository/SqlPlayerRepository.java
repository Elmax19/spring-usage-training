package by.elmax19.app.repository;

import by.elmax19.app.model.sql.SqlPlayer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SqlPlayerRepository extends CrudRepository<SqlPlayer, String> {
    List<SqlPlayer> findAll();
}
