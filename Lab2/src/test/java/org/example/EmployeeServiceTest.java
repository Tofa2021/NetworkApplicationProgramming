package org.example;

import org.example.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeServiceTest {

    @Test
    void getBySkills() {
        List<Employee> employees = List.of(
                new Developer("A", 3, List.of(Skill.PLACEHOLDER1)),
                new Manager("B", 10, List.of(Skill.PLACEHOLDER1)),
                new Tester("C", 5, List.of(Skill.PLACEHOLDER1))
        );

        EmployeeService.add(employees);

        List<Skill> skills = List.of(Skill.PLACEHOLDER1);

        assertEquals(
                employees,
                EmployeeService.getBySkills(skills)
        );
    }
}