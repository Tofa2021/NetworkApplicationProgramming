package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Employee;
import org.example.model.Project;

import java.util.*;

public class ProjectServiceImpl implements ProjectService<Employee> {
    private final Logger logger = LogManager.getLogger(ProjectServiceImpl.class);
    private final ScannerService scannerService;
    private final SecurityService securityService;
    private final Map<Project, List<Employee>> projects;

    public ProjectServiceImpl(ScannerService scannerService, SecurityService securityService) {
        this.scannerService = scannerService;
        this.securityService = securityService;
        this.projects = new HashMap<>();
    }

    @Override
    public ScannerService getScannerService() {
        return scannerService;
    }

    @Override
    public List<Project> getElements() {
        return projects.keySet().stream().toList();
    }

    @Override
    public boolean add(Project project) {
        return Objects.requireNonNull(projects.put(project, new ArrayList<>())).isEmpty();
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
        return !projects.remove(project).isEmpty();
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
    public void create() {
        projects.put(new Project(scannerService.scanNonEmptyString()), new ArrayList<>());
    }

    @Override
    public List<Employee> getProjectAssignables(Project project) {
        return projects.get(project);
    }

    @Override
    public void addToProject(Project project, Employee participant) {
        projects.get(project).add(participant);
        participant.setProject(project);
        logger.info("{} added to project {}", participant.getName(), project.getName());
    }

    @Override
    public void removeFromProject(Employee participant) {
        Project project = participant.getProject();
        projects.get(project).remove(participant);
        participant.setProject(null);
        logger.info("{} removed from project {}", participant.getName(), project.getName());
    }

    @Override
    public void transfer(Project newProject, Employee participant) {
        Project oldProject = participant.getProject();
        if (oldProject == null) {
            logger.warn("{} cannot be transferred oldProject = null", participant.getName());
            return;
        }
        removeFromProject(participant);
        addToProject(newProject, participant);
        logger.info("{} transferred from project {} to project {}", participant.getName(), oldProject.getName(), newProject.getName());
    }
}
