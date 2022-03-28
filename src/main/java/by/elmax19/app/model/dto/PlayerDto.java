package by.elmax19.app.model.dto;

import by.elmax19.app.model.Position;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PlayerDto {
    private String id;
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
