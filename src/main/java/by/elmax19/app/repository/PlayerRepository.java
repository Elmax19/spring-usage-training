package by.elmax19.app.repository;

import by.elmax19.app.model.Player;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<Player, ObjectId> {
    Player findPlayerBySurnameAndName(String surname, String name);
}
