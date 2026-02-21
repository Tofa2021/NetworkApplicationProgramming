package org.example;

import org.example.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilsTest {
    @Test
    public void testSelect() {
        Developer developer = new Developer("A", 3, List.of(Skill.PLACEHOLDER1));

        List<Employee> employees = new ArrayList<>();
        employees.add(developer);
        employees.add(new Manager("B", 10, List.of(Skill.PLACEHOLDER1)));
        employees.add(new Tester("C", 5, List.of(Skill.PLACEHOLDER1)));

        Employee employee = Utils.select(new MockScannerService("1"), employees);
        assertEquals(
                developer,
                employee
        );
    }

    @Test
    public void testMultiSelect() {
        Developer developer = new Developer("A", 3, List.of(Skill.PLACEHOLDER1));
        Manager manager = new Manager("B", 10, List.of(Skill.PLACEHOLDER1));
        Tester tester = new Tester("C", 5, List.of(Skill.PLACEHOLDER1));
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
}