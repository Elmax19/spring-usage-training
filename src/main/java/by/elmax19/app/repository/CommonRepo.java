package by.elmax19.app.repository;

import by.elmax19.app.model.BaseEntity;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface CommonRepo<T extends BaseEntity> {
    Optional<T> findById(ObjectId id);

    T create(T entity);

    UpdateResult update(T entity);

    DeleteResult delete(ObjectId id);

    Document convertToDocument(T entity);

    T convertToEntity(Document document);
}
