package com.example.task.service;

import java.util.List;
import java.util.Optional;

public interface Service<T> {
    List<T> findAll();
    Optional<T> findById(int id);
    void save(T t);
    void deleteById(int id);
}
