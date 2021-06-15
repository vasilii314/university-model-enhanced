package com.example.task.service;

import com.example.task.entity.Human;
import com.example.task.repository.HumanRepository;
import com.example.task.specification.HumanSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HumanServiceImpl implements HumanService {

    private HumanRepository humanRepository;

    @Autowired
    public HumanServiceImpl(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    @Override
    public List<Human> findAll() {
        List<Human> people = new ArrayList<>();
        humanRepository.findAll().forEach(people::add);
        return people;
    }

    @Override
    public List<Human> findAllWithConstraints(HumanSpecification specification) {
        return humanRepository.findAll(specification);
    }

    @Override
    public Optional<Human> findById(int id) {
        return humanRepository.findById(id);
    }

    @Override
    public void save(Human human) {
        humanRepository.save(human);
    }

    @Override
    public void deleteById(int id) {
        humanRepository.deleteById(id);
    }
}
