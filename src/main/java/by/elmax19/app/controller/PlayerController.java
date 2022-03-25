package by.elmax19.app.controller;

import by.elmax19.app.model.dto.PlayerDto;
import by.elmax19.app.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping("/players")
    List<PlayerDto> findAllPlayers() {
        return playerService.findAll();
    }

    @GetMapping("/{clubName}/players")
    List<PlayerDto> findAllPlayersBuClub(@PathVariable String clubName) {
        return playerService.findByClub(clubName);
    }

    @GetMapping("/player/{id}")
    PlayerDto findPlayerById(@PathVariable ObjectId id) {
        return playerService.findById(id);
    }
}
