package by.elmax19.app.model.mongo;

import by.elmax19.app.model.Position;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "players")
@Builder
@Getter
@EqualsAndHashCode
public class MongoPlayer {
    @MongoId
    private ObjectId id;
    private String surname;
    private String name;
    private Integer age;
    private Double height;
    private Integer spike;
    private Integer block;
    private Position position;
    private String currentClub;
    private Integer number;
}