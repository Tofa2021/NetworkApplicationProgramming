package org.example.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeTest {

    @Test
    void haveOneOfSkillsHappyPath() {
        Developer developer = new Developer("A", 3, List.of(Skill.PLACEHOLDER1));

        List<Skill> skills = List.of(Skill.PLACEHOLDER1, Skill.PLACEHOLDER2);

        assertTrue(developer.haveOneOfSkills(skills));
    }

    @Test
    void haveOneOfSkillsSadPath() {
        Developer developer = new Developer("A", 3, List.of(Skill.PLACEHOLDER1));

        List<Skill> skills = List.of(Skill.PLACEHOLDER2);

        assertFalse(developer.haveOneOfSkills(skills));
    }
}