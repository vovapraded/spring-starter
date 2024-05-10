package by.javaguru.spring.database.entity;

import java.io.Serializable;

public interface BaseEntity <T extends Serializable> {
    void setId(T id);
    T getId();
}
