package by.elmax19.app.model.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
public class NewPlayerDto {
    @NotNull(message = "Full name cannot be null")
    @NotBlank
    private String fullName;

    @NotNull(message = "Age cannot be null")
    @Min(value = 16, message = "Age should not be less than 16")
    @Max(value = 60, message = "Age should not be greater than 60")
    private Integer age;

    @NotNull(message = "Height cannot be null")
    @Min(value = 1, message = "Height should not be less than 1 meter")
    @Max(value = 3, message = "Height should not be greater than 3 meters")
    private Double height;

    @NotNull(message = "Spike cannot be null")
    @Min(value = 2, message = "Spike should not be less than 2 meter")
    @Max(value = 4, message = "Spike should not be greater than 4 meters")
    private Integer spike;

    @NotNull(message = "Name cannot be null")
    @Min(value = 2, message = "Block should not be less than 2 meter")
    @Max(value = 4, message = "Block should not be greater than 4 meters")
    private Integer block;

    @NotNull(message = "Name cannot be null")
    private String position;

    @NotNull(message = "Name cannot be null")
    private String club;

    @NotNull(message = "Name cannot be null")
    @Min(value = 1, message = "Player number should not be less than 1")
    @Max(value = 12, message = "Player number should not be greater than 12")
    private Integer number;

    @NotNull(message = "Name cannot be null")
    @NotEmpty
    private List<String> nationalities;

    @NotNull(message = "Name cannot be null")
    private BigDecimal salary;
}
