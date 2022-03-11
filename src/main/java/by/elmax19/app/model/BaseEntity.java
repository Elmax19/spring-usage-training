package by.elmax19.app.model;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public abstract class BaseEntity {
    private ObjectId id;

    public BaseEntity() {
        id = new ObjectId();
    }

    public BaseEntity(ObjectId id) {
        this.id = id;
    }
}
