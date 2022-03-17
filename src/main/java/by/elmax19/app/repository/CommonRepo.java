package by.elmax19.app.repository;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface CommonRepo<T, K> {
    Optional<T> findById(K id);

    Optional<T> create(T entity);

    UpdateResult update(T entity);

    DeleteResult delete(ObjectId id);

    Document convertToDocument(T entity);

    T convertToEntity(Document document);

    long getDocumentsCount();
}
