package org.example;

import org.example.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilsTest {
    @Test
    public void testSelect() {
        Developer developer = new Developer(
                "A",
                1000,
                Grade.JUNIOR,
                3,
                List.of(Skill.COMMUNICATION),
                List.of(ProgrammingLanguages.C_SHARP)
        );

        List<Employee> employees = new ArrayList<>();
        employees.add(developer);
        employees.add(
                new Manager(
                        "B",
                        1000,
                        Grade.JUNIOR,
                        10,
                        List.of(Skill.COMMUNICATION),
                        10
                )
        );
        employees.add(
                new Tester(
                        "C",
                        1000,
                        Grade.JUNIOR,
                        5,
                        List.of(Skill.COMMUNICATION),
                        List.of(TestTools.POSTMAN)
                )
        );

        Employee employee = Utils.select(new MockScannerService("1"), employees);
        assertEquals(
                developer,
                employee
        );
    }

    @Test
    public void testMultiSelect() {
        Developer developer = new Developer("A", 1000, Grade.JUNIOR, 3, List.of(Skill.COMMUNICATION), List.of(ProgrammingLanguages.C_SHARP));
        Manager manager = new Manager("B", 1000, Grade.JUNIOR, 10, List.of(Skill.COMMUNICATION), 11);
        Tester tester = new Tester("C", 1000, Grade.JUNIOR, 5, List.of(Skill.COMMUNICATION), List.of(TestTools.DBEAVER));
        List<Employee> employees = new ArrayList<>();
        employees.add(developer);
        employees.add(manager);
        employees.add(tester);

        List<Employee> result = Utils.multiSelect(new MockScannerService("2", "1"), employees, 2);
        assertEquals(
                List.of(manager, developer),
                result
        );
    }

    @Test
    public void testMultiSelectWithoutCount() {
        Developer developer = new Developer("A", 1000, Grade.JUNIOR, 3, List.of(Skill.COMMUNICATION), List.of(ProgrammingLanguages.C_SHARP));
        Manager manager = new Manager("B", 1000, Grade.JUNIOR, 10, List.of(Skill.COMMUNICATION), 11);
        Tester tester = new Tester("C", 1000, Grade.JUNIOR, 5, List.of(Skill.COMMUNICATION), List.of(TestTools.DBEAVER));
        List<Employee> employees = new ArrayList<>();
        employees.add(developer);
        employees.add(manager);
        employees.add(tester);

        List<Employee> result = Utils.multiSelect(new MockScannerService("2", "1", ""), employees);
        assertEquals(
                List.of(manager, developer),
                result
        );
    }
}