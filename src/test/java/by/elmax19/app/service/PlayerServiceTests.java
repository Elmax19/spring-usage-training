package by.elmax19.app.service;

import by.elmax19.app.mapper.PlayerMapper;
import by.elmax19.app.model.dto.NewPlayerDto;
import by.elmax19.app.repository.PlayerRepository;
import by.elmax19.app.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTests {
    @Mock
    private PlayerMapper playerMapper;
    @Mock
    private PlayerRepository playerRepository;
    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    @DisplayName("Player has been created")
    void checkPlayerCreation() {
        NewPlayerDto newPlayerDto = NewPlayerDto.builder()
                
                .build();
    }
}
