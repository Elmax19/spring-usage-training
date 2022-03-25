package by.elmax19.app.repository;

import by.elmax19.app.model.mongo.MongoPlayer;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MongoPlayerRepository extends CrudRepository<MongoPlayer, ObjectId> {
    List<MongoPlayer> findAll();
}