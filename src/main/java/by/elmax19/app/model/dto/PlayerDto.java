package by.elmax19.app.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PlayerDto {
    private String fullName;
    private Integer age;
    private Double height;
    private Integer spike;
    private Integer block;
    private String position;
    private String club;
    private Integer number;
    private List<String> nationalities;
}