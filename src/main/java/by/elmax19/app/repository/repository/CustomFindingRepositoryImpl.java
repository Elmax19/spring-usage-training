package by.elmax19.app.repository.repository;

import by.elmax19.app.model.Player;
import by.elmax19.app.repository.CustomFindingRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomFindingRepositoryImpl implements CustomFindingRepository {
    private final MongoTemplate mongoTemplate;

    @Override
    public Player customFindById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Player.class);
    }
}
