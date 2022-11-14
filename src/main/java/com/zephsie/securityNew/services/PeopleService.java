package com.zephsie.securityNew.services;

import com.zephsie.securityNew.models.Person;
import com.zephsie.securityNew.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Person> findByUsername(String username) {
        return peopleRepository.findByUsername(username);
    }
}