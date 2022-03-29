package by.elmax19.app.service.impl;

import by.elmax19.app.exception.PlayerNotFoundException;
import by.elmax19.app.mapper.PlayerMapper;
import by.elmax19.app.model.Player;
import by.elmax19.app.model.dto.PlayerDto;
import by.elmax19.app.repository.PlayerRepository;
import by.elmax19.app.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    @Override
    public PlayerDto findById(String id) {
        return playerRepository.findById(new ObjectId(id))
                .map(playerMapper::convertToDto)
                .orElseThrow(() -> new PlayerNotFoundException("There isn't player with id: " + id));
    }

    @Override
    public List<PlayerDto> findAll(Example<Player> player) {
        return playerMapper.convertListToDto(playerRepository.findAll(player));
    }
}
