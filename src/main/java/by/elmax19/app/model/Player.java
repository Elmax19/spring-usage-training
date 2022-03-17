package by.elmax19.app.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.List;

@EqualsAndHashCode
@Builder
@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Player {
    private ObjectId id;
    private String surname;
    private String name;
    @Setter
    private Integer age;
    private Double height;
    private Integer spike;
    private Integer block;
    private Position position;
    private String currentClub;
    private Integer number;
    private List<String> nationalities;
}
