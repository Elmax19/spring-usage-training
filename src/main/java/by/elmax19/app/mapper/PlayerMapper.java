package by.elmax19.app.mapper;

import by.elmax19.app.model.Player;
import by.elmax19.app.model.dto.NewPlayerDto;
import by.elmax19.app.model.dto.PlayerDto;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", imports = ObjectId.class)
public interface PlayerMapper {
    @Mapping(target = "id", expression = "java(player.getId().toString())")
    @Mapping(target = "fullName", expression = "java(player.getName() + ' ' + player.getSurname())")
    @Mapping(target = "club", source = "player.currentClub")
    PlayerDto convertToDto(Player player);

    List<PlayerDto> convertListToDto(List<Player> players);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "currentClub", source = "newPlayer.club")
    Player convertToEntity(NewPlayerDto newPlayer);

    @Mapping(target = "id", expression = "java(new ObjectId(player.getId()))")
    @Mapping(target = "surname", expression = "java(player.getFullName().split(\" \")[1])")
    @Mapping(target = "name", expression = "java(player.getFullName().split(\" \")[0])")
    @Mapping(target = "currentClub", source = "player.club")
    @Mapping(target = "salary", ignore = true)
    Player convertToEntity(PlayerDto player);
}
