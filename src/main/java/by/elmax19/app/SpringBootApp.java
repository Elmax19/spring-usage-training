package by.elmax19.app;

import by.elmax19.app.model.Player;
import by.elmax19.app.model.Position;
import by.elmax19.app.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringBootApp {
//    @Autowired
//    private PlayerRepository playerRepo;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }

//    @Bean
//    public Player newPlayer() {
//        Player player = Player.builder()
//                .id("62382e6bd057fd713e9b50e9")
//                .surname("Rezende")
//                .name("Bruno")
//                .age(35)
//                .height(1.90)
//                .spike(323)
//                .block(302)
//                .position(Position.SETTER)
//                .currentClub("Azimut Modena")
//                .number(1)
//                .nationalities(List.of("Brazilian"))
//                .build();
//        playerRepo.save(player);
//        System.out.println(playerRepo.findById(player.getId()));
//        return player;
//    }

}
