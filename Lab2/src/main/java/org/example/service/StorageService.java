package org.example.service;

import java.util.List;

public interface StorageService<T> {
    List<T> load();

    void save(List<T> toSave);
}
