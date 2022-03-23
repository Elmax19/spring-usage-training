package by.elmax19.app.repository.impl;

import by.elmax19.app.exception.ParsingJsonException;
import by.elmax19.app.model.Player;
import by.elmax19.app.model.Position;
import by.elmax19.app.repository.CommonRepo;
import by.elmax19.app.util.ObjectToJsonConverter;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

@Slf4j
@Repository
public class PlayerRepo implements CommonRepo<Player, ObjectId> {
    @Autowired
    private MongoCollection<Document> playersCollection;
    @Autowired
    private ObjectToJsonConverter converter;

    @Override
    public Optional<Player> findById(ObjectId playerId) {
        Document playerDocument = playersCollection.find(eq("_id", playerId)).first();
        return Optional.ofNullable(playerDocument)
                .map(this::convertToEntity);
    }

    public Optional<Player> findBySurnameAndName(String surname, String name) {
        Document playerDocument = playersCollection.
                find(and(eq("surname", surname),
                        eq("name", name)))
                .first();
        return Optional.ofNullable(playerDocument)
                .map(this::convertToEntity);
    }

    public List<Player> findByCurrentClub(String currentClub) {
        Spliterator<Document> players = playersCollection
                .find(eq("currentClub", currentClub)).spliterator();
        return StreamSupport.stream(players, false)
                .map(this::convertToEntity)
                .toList();
    }

    @Override
    public void create(Player player) throws ParsingJsonException {
        final Optional<Document> newPlayerDocument = convertToDocument(player);
        newPlayerDocument.ifPresent(document -> playersCollection.insertOne(document));
    }

    @Override
    public UpdateResult update(Player player) {
        Bson filter = eq("_id", player.getId());
        Bson updateOperation = getUpdateOperation(player);
        return playersCollection.updateOne(filter, updateOperation);
    }

    @Override
    public DeleteResult delete(ObjectId playerId) {
        Bson filter = eq("_id", playerId);
        return playersCollection.deleteOne(filter);
    }

    @Override
    public void deleteAll() {
        playersCollection.deleteMany(new BasicDBObject());
    }

    @Override
    public long getDocumentsCount() {
        return playersCollection.countDocuments();
    }

    private Optional<Document> convertToDocument(Player player) {
        try {
            String playerJsonData = converter.convert(player);
            return Optional.of(Document.parse(playerJsonData));
        } catch (IOException e) {
            log.error("Error while trying to parse Player into JSON: " + e.getLocalizedMessage());
            throw new ParsingJsonException("Can't parse Player: " + player.toString());
        }
    }

    private Player convertToEntity(Document entityDocument) {
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
                set("position", player.getPosition().name()),
                set("currentClub", player.getCurrentClub()),
                set("number", player.getNumber()),
                set("nationalities", player.getNationalities()));
    }
}
