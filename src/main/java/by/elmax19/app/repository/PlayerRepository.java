package by.elmax19.app.repository;

import by.elmax19.app.model.Player;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, String>, CustomFindingRepository {
    @Query("{'_id':{'oid':?0}}")
    Player findPlayerById(String id);

    Player findPlayerBySurnameAndName(String surname, String name);

    List<Player> findPlayersByCurrentClub(String currentClub);
}
