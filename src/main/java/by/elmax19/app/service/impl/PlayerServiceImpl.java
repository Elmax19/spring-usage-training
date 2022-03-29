package by.elmax19.app.service.impl;

import by.elmax19.app.mapper.PlayerMapper;
import by.elmax19.app.model.Player;
import by.elmax19.app.model.dto.NewPlayerDto;
import by.elmax19.app.model.dto.PlayerDto;
import by.elmax19.app.repository.PlayerRepository;
import by.elmax19.app.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    @Override
    public PlayerDto create(NewPlayerDto playerDto) {
        Player player = playerMapper.convertToEntity(playerDto);
        return playerMapper.convertToDto(playerRepository.save(player));
    }
}
