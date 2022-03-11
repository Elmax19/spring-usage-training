package by.elmax19.app.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Locale;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class Player extends BaseEntity {
    private String surname;
    private String name;
    private Integer age;
    private Double height;
    private Integer spike;
    private Integer block;
    private Position position;
    private String currentClub;
    private Integer number;
    private List<String> nationalities;

    public Player(String surname, String name, Integer age, Double height, Integer spike, Integer block, Position position, String currentClub, Integer number, List<String> nationality) {
        this.surname = surname;
        this.name = name;
        this.age = age;
        this.height = height;
        this.spike = spike;
        this.block = block;
        this.position = position;
        this.currentClub = currentClub;
        this.number = number;
        this.nationalities = nationality;
    }

    public Player(ObjectId id, String surname, String name, Integer age, Double height, Integer spike, Integer block, Position position, String currentClub, Integer number, List<String> nationality) {
        super(id);
        this.surname = surname;
        this.name = name;
        this.age = age;
        this.height = height;
        this.spike = spike;
        this.block = block;
        this.position = position;
        this.currentClub = currentClub;
        this.number = number;
        this.nationalities = nationality;
    }

    @Override
    public String toString() {
        StringBuilder nationalitiesList = new StringBuilder();
        for(String nationality : nationalities){
            nationalitiesList.append('\"').append(nationality).append('\"').append(',');
        }
        nationalitiesList.setLength(nationalitiesList.length() - 1);
        return "{" +
                "\"surname\": \"" + surname + "\"," +
                "\"name\": \"" + name + "\"," +
                "\"age\": " + age + ',' +
                "\"height\": " + height + ',' +
                "\"spike\": " + spike + ',' +
                "\"block\": " + block + ',' +
                "\"position\": \"" + position.name().toLowerCase(Locale.ROOT) + "\"," +
                "\"currentClub\": \"" + currentClub + "\"," +
                "\"number\": " + number + ',' +
                "\"nationalities\": [" + nationalitiesList + ']' +
                '}';
    }
}
