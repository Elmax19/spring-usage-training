package by.elmax19.app.model.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
public class NewPlayerDto {
    @NotBlank(message = "Surname cannot be empty")
    private String surname;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Age cannot be null")
    @Min(value = 16, message = "Age should not be less than 16")
    @Max(value = 60, message = "Age should not be greater than 60")
    private Integer age;

    @NotNull(message = "Height cannot be null")
    @Positive(message = "Height should not be positive number")
    @Max(value = 3, message = "Height should not be greater than 3 meters")
    private Double height;

    @NotNull(message = "Spike cannot be null")
    @Min(value = 243, message = "Spike should not be less than 2.43 meters")
    @Max(value = 400, message = "Spike should not be greater than 4 meters")
    private Integer spike;

    @NotNull(message = "Block cannot be null")
    @Min(value = 243, message = "Block should not be less than 2.43 meters")
    @Max(value = 400, message = "Block should not be greater than 4 meters")
    private Integer block;

    @NotBlank(message = "Position cannot be empty")
    private String position;

    @NotBlank(message = "Club name cannot be empty")
    private String club;

    @NotNull(message = "Number cannot be null")
    @Min(value = 1, message = "Player number should not be less than 1")
    @Max(value = 12, message = "Player number should not be greater than 12")
    private Integer number;

    @NotEmpty(message = "Player should have at list 1 nationality")
    private List<String> nationalities;

    @NotNull(message = "Salary cannot be null")
    @Positive(message = "Salary value cannot be negative")
    private BigDecimal salary;
}
