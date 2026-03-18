package org.example.service;

import org.example.Utils;
import org.example.model.ProjectTransferLog;

import java.util.ArrayList;
import java.util.List;

public class ProjectTransferLogger {
    private final List<ProjectTransferLog> projectTransferLogs = new ArrayList<>();

    public void addLog(ProjectTransferLog log) {
        projectTransferLogs.add(log);
    }

    public void printLogs() {
        Utils.printDescribableList(projectTransferLogs);
    }
}
