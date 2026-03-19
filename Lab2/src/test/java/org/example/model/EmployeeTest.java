package org.example.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeTest {

    @Test
    void haveOneOfSkills_skillFound() {
        Developer developer = new Developer("A", 1000, Grade.JUNIOR, 3, List.of(Skill.COMMUNICATION, Skill.LEADERSHIP), List.of());

        List<Skill> skills = List.of(Skill.CRITICAL_THINKING, Skill.COMMUNICATION);

        assertTrue(developer.haveOneOfSkills(skills));
    }

    @Test
    void haveOneOfSkills_skillNotFound() {
        Developer developer = new Developer("A", 1000, Grade.JUNIOR, 3, List.of(Skill.COMMUNICATION), List.of());

        List<Skill> skills = List.of(Skill.LEADERSHIP);

        assertFalse(developer.haveOneOfSkills(skills));
    }
}