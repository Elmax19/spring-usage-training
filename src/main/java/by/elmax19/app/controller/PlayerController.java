package by.elmax19.app.controller;

import by.elmax19.app.exception.PlayerNotFoundException;
import by.elmax19.app.model.Player;
import by.elmax19.app.model.dto.PlayerDto;
import by.elmax19.app.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping("/players")
    public List<PlayerDto> findAllPlayersBuClub(@RequestParam Optional<String> club) {
        Player player = Player.builder().build();
        club.ifPresent(player::setCurrentClub);
        return playerService.findAll(Example.of(player));
    }

    @GetMapping("/player/{playerId}")
    public PlayerDto findPlayerById(@PathVariable String playerId) {
        return playerService.findById(playerId);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void playerNotFoundHandler(PlayerNotFoundException ex) {
    }
}
