package org.example.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeTest {

    @Test
    void haveAnySkill() {
        Developer developer = new Developer("A", 3, List.of(Skill.PLACEHOLDER1));

        List<Skill> skills = List.of(Skill.PLACEHOLDER1, Skill.PLACEHOLDER2);

        assertTrue(developer.haveAnySkill(skills));
    }
}