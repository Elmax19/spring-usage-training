package by.elmax19.app.repository.impl;

import by.elmax19.app.model.Player;
import by.elmax19.app.model.Position;
import by.elmax19.app.repository.CommonRepo;
import by.elmax19.app.service.DatabaseService;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

@Repository
public class PlayerRepo implements CommonRepo<Player> {
    @Autowired
    private DatabaseService databaseService;
    private MongoCollection<Document> collection;
    @Value("${mongodb.database.collections.players-collection.name}")
    private String playerCollectionName;

    @PostConstruct
    private void initMethod() {
        collection = databaseService.getCollection(playerCollectionName);
    }


    @Override
    public Optional<Player> findById(ObjectId playerId) {
        Document playerDocument = collection.find(eq("_id", playerId)).first();
        return playerDocument == null ? Optional.empty() : Optional.of(convertToEntity(playerDocument));
    }

    @Override
    public Player create(Player player) {
        final Document newPlayerDocument = convertToDocument(player);
        collection.insertOne(newPlayerDocument);
        return player;
    }

    @Override
    public UpdateResult update(Player player) {
        Bson filter = eq("_id", player.getId());
        Bson updateOperation = getUpdateOperation(player);
        return collection.updateOne(filter, updateOperation);
    }

    @Override
    public DeleteResult delete(ObjectId playerId) {
        Bson filter = eq("_id", playerId);
        return collection.deleteOne(filter);
    }

    @Override
    public Document convertToDocument(Player player) {
//        return new Document("_id", player.getId())
//                .append("surname", player.getSurname())
//                .append("name", player.getName())
//                .append("age", player.getAge())
//                .append("height", player.getHeight())
//                .append("spike", player.getSpike())
//                .append("block", player.getBlock())
//                .append("position", player.getPosition())
//                .append("currentClub", player.getCurrentClub())
//                .append("number", player.getNumber())
//                .append("nationalities", player.getNationalities());
        return Document.parse(player.toString());
    }

    @Override
    public Player convertToEntity(Document entityDocument) {
        return new Player(
                entityDocument.getObjectId("_id"),
                entityDocument.getString("surname"),
                entityDocument.getString("name"),
                entityDocument.getInteger("age"),
                entityDocument.getDouble("height"),
                entityDocument.getInteger("spike"),
                entityDocument.getInteger("block"),
                Position.valueOf(entityDocument.getString("position")),
                entityDocument.getString("currentClub"),
                entityDocument.getInteger("number"),
                entityDocument.getList("nationalities", String.class));
    }

    private Bson getUpdateOperation(Player player) {
        return combine(
                set("surname", player.getSurname()),
                set("name", player.getName()),
                set("age", player.getAge()),
                set("height", player.getHeight()),
                set("spike", player.getSpike()),
                set("block", player.getBlock()),
                set("position", player.getPosition()),
                set("currentClub", player.getCurrentClub()),
                set("number", player.getNumber()),
                set("nationalities", player.getNationalities()));
    }
}
