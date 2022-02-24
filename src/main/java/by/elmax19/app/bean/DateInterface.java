package by.elmax19.app.bean;

import java.time.temporal.Temporal;

public interface DateInterface<T extends Temporal> {
    T getDate();
}
