package by.elmax19.app.repo.impl;

import by.elmax19.app.model.Player;
import by.elmax19.app.model.PlayerCriteria;
import by.elmax19.app.repo.CustomFindingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomFindingPlayerRepository implements CustomFindingRepository<Player, PlayerCriteria> {
    private final MongoTemplate mongoTemplate;

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Optional<Player> findFirstByCriteria(PlayerCriteria criteria) {
        return Optional.empty();
    }

    @Override
    public List<Player> findAllByCriteria(PlayerCriteria criteria) {
        return null;
    }
}
