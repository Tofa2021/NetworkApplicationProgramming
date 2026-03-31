package org.example.model;

import org.example.model.enums.Grade;
import org.example.model.enums.Skill;

import java.util.List;
import java.util.function.Predicate;

public class Manager extends Employee {
    private int teamSize;

    public Manager(String name, double salary, Grade grade, int workExperience, List<Skill> skills, int teamSize) {
        super(name, salary, grade, workExperience, skills);
        this.teamSize = teamSize;
    }

    public static Predicate<Employee> getIsInstancePredicate() {
        return (employee) -> employee instanceof Manager;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " Размер команды: " + teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }
}
