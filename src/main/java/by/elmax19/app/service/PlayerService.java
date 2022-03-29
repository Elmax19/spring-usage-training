package by.elmax19.app.service;

import by.elmax19.app.model.dto.NewPlayerDto;
import by.elmax19.app.model.dto.PlayerDto;

public interface PlayerService {
    PlayerDto create(NewPlayerDto player);
}
