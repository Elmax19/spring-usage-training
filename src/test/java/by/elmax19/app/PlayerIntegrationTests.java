package by.elmax19.app;

import by.elmax19.app.model.Player;
import by.elmax19.app.model.Position;
import by.elmax19.app.repository.impl.PlayerRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PlayerIntegrationTests {
    @Autowired
    private PlayerRepo playerRepo;
    private final Player firstPlayer;
    private final Player secondPlayer;
    private final Player thirdPlayer;

    public PlayerIntegrationTests() {
        this.firstPlayer = new Player(
                "Rezende",
                "Bruno",
                35,
                1.90,
                323,
                302,
                Position.SETTER,
                "Azimut Modena",
                1,
                List.of("Brazilian"));
        this.secondPlayer = new Player(
                "Mikhaylov",
                "Maxim",
                33,
                2.02,
                360,
                340,
                Position.OPPOSITE_HITTER,
                "Zenit Kazan",
                18,
                List.of("Russian"));
        this.thirdPlayer = new Player(
                "Wilfredo",
                "Leon",
                28,
                2.02,
                380,
                346,
                Position.OUTSIDE_HITTER,
                "Sir Safety Perugia",
                9,
                List.of("Cuban", "Polish"));
    }

    @Test
    @DisplayName("First player has been added")
    void checkPlayerCreation() {
        playerRepo.create(firstPlayer);
    }
}
