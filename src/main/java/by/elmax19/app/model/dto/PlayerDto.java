package by.elmax19.app.model.dto;

import by.elmax19.app.model.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlayerDto {
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
}
