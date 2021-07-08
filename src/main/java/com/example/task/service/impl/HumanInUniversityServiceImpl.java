package com.example.task.service.impl;

import com.example.task.entity.HumanInUniversity;
import com.example.task.repository.HumanInUniversityRepository;
import com.example.task.service.HumanInUniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HumanInUniversityServiceImpl implements HumanInUniversityService {

    private final HumanInUniversityRepository humanInUniversityRepository;

    @Autowired
    public HumanInUniversityServiceImpl(HumanInUniversityRepository humanInUniversityRepository) {
        this.humanInUniversityRepository = humanInUniversityRepository;
    }

    @Override
    public List<HumanInUniversity> findAll() {
        List<HumanInUniversity> people = new ArrayList<>();
        humanInUniversityRepository.findAll().forEach(people::add);
        return people;
    }

    @Override
    public Optional<HumanInUniversity> findById(int id) {
        return humanInUniversityRepository.findById(id);
    }

    @Override
    public void save(HumanInUniversity human) {
        humanInUniversityRepository.save(human);
    }

    @Override
    public void deleteById(int id) {
        humanInUniversityRepository.deleteById(id);
    }
}