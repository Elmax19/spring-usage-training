package by.elmax19.app.repository;

import by.elmax19.app.model.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, String> {
    Player findPlayerBySurnameAndName(String surname, String name);

    List<Player> findPlayersByCurrentClub(String currentClub);
}
