package org.example.model;

import org.example.model.enums.Grade;
import org.example.model.enums.Skill;
import org.example.model.interfaces.Describable;
import org.example.model.interfaces.Nameable;
import org.example.model.interfaces.Payable;
import org.example.model.interfaces.ProjectAssignable;

import java.io.Serializable;
import java.util.List;

public abstract class Employee implements Serializable, Nameable, Describable, ProjectAssignable, Payable {
    private String name;
    private int workExperience;
    private List<Skill> skills;
    private Grade grade;
    private double salary;

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

    public void setWorkExperience(int workExperience) {
        this.workExperience = workExperience;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
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

    public void setName(String name) {
        this.name = name;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
