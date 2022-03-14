package by.elmax19.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@AllArgsConstructor
public class Player {
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
    private List<String> nationalities;

    public Player(String surname, String name, Integer age, Double height, Integer spike, Integer block, Position position, String currentClub, Integer number, List<String> nationality) {
        this.id = new ObjectId();
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
        for (String nationality : nationalities) {
            nationalitiesList.append('\"').append(nationality).append('\"').append(',');
        }
        nationalitiesList.setLength(nationalitiesList.length() - 1);
        return "{" +
                "\"_id\": ObjectId(\"" + id + "\")," +
                "\"surname\": \"" + surname + "\"," +
                "\"name\": \"" + name + "\"," +
                "\"age\": " + age + ',' +
                "\"height\": " + height + ',' +
                "\"spike\": " + spike + ',' +
                "\"block\": " + block + ',' +
                "\"position\": \"" + position.name() + "\"," +
                "\"currentClub\": \"" + currentClub + "\"," +
                "\"number\": " + number + ',' +
                "\"nationalities\": [" + nationalitiesList + ']' +
                '}';
    }
}
