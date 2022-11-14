package com.zephsie.securityNew.services;

import com.zephsie.securityNew.models.Person;
import com.zephsie.securityNew.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public RegisterService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public void register(Person person) {
        peopleRepository.save(person);
    }
}