package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Project;

import java.util.Collections;
import java.util.List;

public class ProjectServiceImpl implements ProjectService {
    private final Logger logger = LogManager.getLogger(ProjectServiceImpl.class);
    private final ScannerService scannerService;
    private final List<Project> projects;

    public ProjectServiceImpl(ScannerService scannerService, List<Project> projects) {
        this.scannerService = scannerService;
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
    public Logger getLogger() {
        return logger;
    }
}
