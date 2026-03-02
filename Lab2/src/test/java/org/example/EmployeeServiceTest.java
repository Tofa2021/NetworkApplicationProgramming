package org.example;

import org.example.model.*;
import org.example.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeServiceTest {

    @Test
    void getBySkillsHappyPath() {
        List<Employee> employees = List.of(
                new Developer("A", 3, List.of(Skill.PLACEHOLDER1)),
                new Manager("B", 10, List.of(Skill.PLACEHOLDER1)),
                new Tester("C", 5, List.of(Skill.PLACEHOLDER1))
        );

        List<Skill> skills = List.of(Skill.PLACEHOLDER1);

        assertEquals(
                employees,
                new EmployeeServiceImpl(employees).getBySkills(skills)
        );
    }

    @Test
    void getBySkillsSadPath() {
        List<Employee> employees = List.of(
                new Developer("A", 3, List.of(Skill.PLACEHOLDER1)),
                new Manager("B", 10, List.of(Skill.PLACEHOLDER2)),
                new Tester("C", 5, List.of(Skill.PLACEHOLDER3))
        );

        List<Skill> skills = List.of(Skill.PLACEHOLDER1);

        assertEquals(
                employees.get(0),
                new EmployeeServiceImpl(employees).getBySkills(skills).get(0)
        );
    }
}