package by.elmax19.app.repo;

import java.util.List;
import java.util.Optional;

/**
 * Custom repository for additional methods to find required data
 * @param <T1> type of finding object model
 * @param <T2> type of searching Criteria model
 * @author Elmax19
 * @version 1.0
 */
public interface CustomFindingRepository<T1, T2> {
    long count();

    Optional<T1> findFirstByCriteria(T2 criteria);

    List<T1> findAllByCriteria(T2 criteria);
}
