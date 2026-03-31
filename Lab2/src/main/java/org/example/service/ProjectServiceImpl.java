package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProjectServiceImpl implements ProjectService {
    private final Logger logger = LogManager.getLogger(ProjectServiceImpl.class);
    private final ScannerService scannerService;
    private final SecurityService securityService;
    private final List<Project> projects;

    public ProjectServiceImpl(ScannerService scannerService, SecurityService securityService) {
        this.scannerService = scannerService;
        this.securityService = securityService;
        this.projects = new ArrayList<>();
    }

    public ProjectServiceImpl(ScannerService scannerService, SecurityService securityService, List<Project> projects) {
        this.scannerService = scannerService;
        this.securityService = securityService;
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
    public void update() {
        if (!securityService.checkPassword()) {
            System.out.println("Неверный пароль");
            return;
        }

        System.out.println("Выберите проект для редактирования");
        Project project = select();
        System.out.println(project.getName());
        System.out.println("""
                Выберите, что редактировать
                1) Название
                """);
        switch (scannerService.scanInt()) {
            case 1 -> updateName(project);
            default -> System.out.println("Невозможный пункт меню");
        }
    }

    private void updateName(Project project) {
        System.out.println("Введите название");
        project.setName(scannerService.scanNonEmptyString());
    }

    @Override
    public boolean remove(Project project) {
        return projects.remove(project);
    }

    @Override
    public void removeSelected() {
        if (!securityService.checkPassword()) {
            System.out.println("Неверный пароль");
            return;
        }

        if (projects.isEmpty()) {
            System.out.println("Пусто");
            return;
        }

        remove(select());
    }

    @Override
    public Project remove(int index) {
        return projects.remove(index);
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public void create() {
        projects.add(new Project(scannerService.scanNonEmptyString()));
    }
}
