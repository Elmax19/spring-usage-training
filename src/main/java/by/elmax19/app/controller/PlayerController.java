package by.elmax19.app.controller;

import by.elmax19.app.exception.PlayerExistsException;
import by.elmax19.app.exception.PlayerNotFoundException;
import by.elmax19.app.model.dto.FindAllPlayerDto;
import by.elmax19.app.model.dto.NewPlayerDto;
import by.elmax19.app.model.dto.PlayerDto;
import by.elmax19.app.model.dto.ValidationErrorDto;
import by.elmax19.app.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping("/players")
    public List<PlayerDto> findAllPlayers(@RequestParam Optional<String> club) {
        FindAllPlayerDto player = new FindAllPlayerDto();
        club.ifPresent(player::setClub);
        return playerService.findAll(player);
    }

    @GetMapping("/player/{playerId}")
    public PlayerDto findPlayerById(@PathVariable String playerId) {
        return playerService.findById(playerId);
    }

    @PostMapping("/players")
    public PlayerDto createPlayer(@Valid @RequestBody NewPlayerDto newPlayer) {
        return playerService.create(newPlayer);
    }

    @ExceptionHandler({PlayerNotFoundException.class})
    @ResponseStatus(NOT_FOUND)
    private String playerNotFoundException(RuntimeException e) {
        return e.getLocalizedMessage();
    }

    @ExceptionHandler({PlayerExistsException.class})
    @ResponseStatus(BAD_REQUEST)
    private String playerExistsException(RuntimeException e) {
        return e.getLocalizedMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public List<ValidationErrorDto> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return fieldErrors.stream().map(fieldError ->
                ValidationErrorDto.builder()
                        .field(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                .build()).toList();
    }
}
