package org.example;

import org.example.model.*;
import org.example.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeServiceTest {

    @Test
    void getBySkills() {
        Developer developer = new Developer("A", 3, List.of(Skill.COMMUNICATION), List.of());
        Manager manager = new Manager("B", 10, List.of(Skill.COMMUNICATION, Skill.LEADERSHIP), 2);

        List<Employee> employees = List.of(
                developer,
                manager,
                new Tester("C", 5, List.of(Skill.TEAMWORK, Skill.CRITICAL_THINKING), List.of()),
                new Tester("D", 5, List.of(), List.of())
        );

        List<Skill> skills = List.of(Skill.COMMUNICATION);

        assertEquals(
                List.of(developer, manager),
                new EmployeeServiceImpl(employees).getBySkills(skills)
        );
    }
}