package com.zephsie.securityNew.util;

import com.zephsie.securityNew.dto.PersonDTO;
import com.zephsie.securityNew.models.Person;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonDTOConverter {
    private final ModelMapper modelMapper;

    @Autowired
    public PersonDTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Person convertToEntity(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }
}
