package by.elmax19.app.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode
@Builder
@Getter
@Document(collection = "players")
public class Player {
    @MongoId
    private ObjectId id;
    @Setter
    private String surname;
    @Setter
    private String name;
    @Setter
    private Integer age;
    private Double height;
    private Integer spike;
    private Integer block;
    private Position position;
    @Setter
    private String currentClub;
    private Integer number;
    private List<String> nationalities;
    private BigDecimal salary;
}
