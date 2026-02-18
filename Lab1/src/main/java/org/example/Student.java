package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

public class Student {
    private final Logger logger = LogManager.getLogger(Student.class);
    private final String surname;
    private final MyArrayList<LocalDate> absences = new MyArrayList<>();

    public Student(String surname) {
        this.surname = surname;
    }

    public void addAbsence(LocalDate date) {
        absences.add(date);
        logger.info("Add absence to {} on date {}", surname, date);
    }

    public List<LocalDate> getAbsences() {
        return absences;
    }

    public String getName() {
        return surname;
    }

    public String getAbsenceString() {
        List<String> localDateStrings = absences.stream().map(Utils::convertDateToString).toList();
        return String.join(" ", localDateStrings);

    }
}
