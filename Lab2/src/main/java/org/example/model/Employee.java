package org.example.model;

import java.io.Serializable;
import java.util.List;

public abstract class Employee implements Serializable, Nameable, Describable, ProjectAssignable, Payable {
    private final String name;
    private final int workExperience;
    private final List<Skill> skills;
    private final double salary;
    private final Grade grade;
    private Project project;

    public Employee(String name, double salary, Grade grade, int workExperience, List<Skill> skills) {
        this.name = name;
        this.salary = salary;
        this.grade = grade;
        this.workExperience = workExperience;
        this.skills = skills;
        this.project = null;
    }

    public boolean haveOneOfSkills(List<Skill> skills) {
        for (Skill skill : this.skills) {
            return skills.contains(skill);
        }
        return false;
    }

    @Override
    public String getDescription() {
        String positionString = "Должность: " + this.getClass().getSimpleName();
        String skillsString = skills.isEmpty() ? "" : " Умения: " + String.join(" ", skills.stream().map(Skill::getName).toList());
        String wordExperienceString = "Стаж: " + workExperience;
        String salaryString = "Зарплата: " + salary;
        String gradeString = "Грейд: " + grade.getName();

        return String.join(" ", List.of(positionString, skillsString, wordExperienceString, salaryString, gradeString));
    }

    public int getWorkExperience() {
        return workExperience;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public Project getProject() {
        return project;
    }

    @Override
    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String getName() {
        return name;
    }

    public Grade getGrade() {
        return grade;
    }
}
