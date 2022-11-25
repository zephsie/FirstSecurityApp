package com.zephsie.securityNew.util;

import com.zephsie.securityNew.models.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class RandomPeopleGenerator {
    public List<Person> generate(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> new Person(i, "Username" + i, "Password" + i, "ROLE_USER", i))
                .collect(Collectors.toList());
    }
}