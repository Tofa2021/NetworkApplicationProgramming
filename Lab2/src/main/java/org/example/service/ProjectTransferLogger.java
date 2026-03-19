package org.example.service;

import org.example.Utils;
import org.example.model.Employee;
import org.example.model.ProjectTransferLog;

import java.util.ArrayList;
import java.util.List;

public class ProjectTransferLogger {
    private final List<ProjectTransferLog<Employee>> projectTransferLogs = new ArrayList<>();

    public void addLog(ProjectTransferLog<Employee> log) {
        projectTransferLogs.add(log);
    }

    public void printLogs() {
        Utils.printDescribableList(projectTransferLogs);
    }
}
