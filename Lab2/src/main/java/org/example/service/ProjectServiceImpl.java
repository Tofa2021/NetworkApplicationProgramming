package org.example.service;

import org.example.model.Project;

import java.util.Collections;
import java.util.List;

public class ProjectServiceImpl implements ProjectService {
    private final ScannerService scannerService;
    private final ProjectTransferLogger projectTransferLogger;
    private final List<Project> projects;

    public ProjectServiceImpl(ScannerService scannerService, ProjectTransferLogger projectTransferLogger, List<Project> projects) {
        this.scannerService = scannerService;
        this.projectTransferLogger = projectTransferLogger;
        this.projects = projects;
    }

    @Override
    public ScannerService getScannerService() {
        return scannerService;
    }

    @Override
    public List<Project> getElements() {
        return Collections.unmodifiableList(projects);
    }

    @Override
    public boolean add(Project project) {
        return projects.add(project);
    }

    @Override
    public boolean remove(Project project) {
        return projects.remove(project);
    }

    @Override
    public Project remove(int index) {
        return projects.remove(index);
    }

    @Override
    public ProjectTransferLogger getProjectLogger() {
        return projectTransferLogger;
    }
}
