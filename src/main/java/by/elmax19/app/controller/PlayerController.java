package by.elmax19.app.controller;

import by.elmax19.app.exception.PlayerNotFoundException;
import by.elmax19.app.model.dto.FindAllPlayerDto;
import by.elmax19.app.model.dto.NewPlayerDto;
import by.elmax19.app.model.dto.PlayerDto;
import by.elmax19.app.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

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

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void playerNotFoundHandler(PlayerNotFoundException ex) {
    }

    @PostMapping("/players")
    public PlayerDto createPlayer(@RequestBody NewPlayerDto newPlayer) {
        return playerService.create(newPlayer);
    }
}
