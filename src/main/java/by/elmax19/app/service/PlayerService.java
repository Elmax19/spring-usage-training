package by.elmax19.app.service;

import by.elmax19.app.exception.NoEntityWithSuchId;
import by.elmax19.app.mapper.PlayerMapper;
import by.elmax19.app.model.dto.PlayerDto;
import by.elmax19.app.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    public PlayerDto findById(String id) {
        return playerRepository.findById(new ObjectId(id))
                .map(playerMapper::convertToDto)
                .orElseThrow(() -> new NoEntityWithSuchId("There isn't player with id: " + id));
    }

    public List<PlayerDto> findAll() {
        return playerMapper.convertListToDto(playerRepository.findAll());
    }

    public List<PlayerDto> findByClub(String clubName) {
        return playerMapper.convertListToDto(playerRepository.findPlayersByCurrentClubOrderByNumber(clubName));
    }
}
