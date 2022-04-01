package by.elmax19.app.mapper;

import by.elmax19.app.model.Player;
import by.elmax19.app.model.dto.NewPlayerDto;
import by.elmax19.app.model.dto.PlayerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    @Mapping(target = "id", expression = "java(player.getId().toString())")
    @Mapping(target = "fullName", expression = "java(player.getName() + ' ' + player.getSurname())")
    @Mapping(target = "club", source = "player.currentClub")
    PlayerDto convertToDto(Player player);

    List<PlayerDto> convertListToDto(List<Player> players);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "currentClub", source = "newPlayer.club")
    Player convertToEntity(NewPlayerDto newPlayer);
}
