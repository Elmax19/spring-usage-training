package by.elmax19.app.service;

import by.elmax19.app.model.Player;
import by.elmax19.app.model.dto.PlayerDto;
import org.springframework.data.domain.Example;

import java.util.List;

public interface PlayerService extends CommonService<PlayerDto, String> {
    List<PlayerDto> findAll(Example<Player> of);
}
