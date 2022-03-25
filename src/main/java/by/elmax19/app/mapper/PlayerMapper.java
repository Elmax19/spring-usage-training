package by.elmax19.app.mapper;

import by.elmax19.app.model.Player;
import by.elmax19.app.model.dto.PlayerDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface PlayerMapper {
    PlayerDto convertToDto(Player player);

    List<PlayerDto> convertListToDto(List<Player> players);
}
