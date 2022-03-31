package by.elmax19.app.service.impl;

import by.elmax19.app.exception.PlayerExistsException;
import by.elmax19.app.exception.PlayerNotFoundException;
import by.elmax19.app.mapper.PlayerMapper;
import by.elmax19.app.model.Player;
import by.elmax19.app.model.dto.FindAllPlayerDto;
import by.elmax19.app.model.dto.NewPlayerDto;
import by.elmax19.app.model.dto.PlayerDto;
import by.elmax19.app.repository.PlayerRepository;
import by.elmax19.app.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<PlayerDto> findAll(FindAllPlayerDto playerDto) {
        Player player = Player.builder()
                .currentClub(playerDto.getClub())
                .build();
        return playerMapper.convertListToDto(playerRepository.findAll(Example.of(player)));
    }

    public PlayerDto create(NewPlayerDto playerDto) {
        Player player = playerMapper.convertToEntity(playerDto);
        Optional<Player> playerInDatabase = playerRepository.findOne(Example.of(player));
        if (playerInDatabase.isPresent()) {
            throw new PlayerExistsException("Such player is already exists.");
        }
        return playerMapper.convertToDto(playerRepository.save(player));
    }
}
