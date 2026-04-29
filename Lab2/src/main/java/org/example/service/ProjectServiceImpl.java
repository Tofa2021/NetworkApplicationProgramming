package org.example.service;

import org.example.model.Employee;
import org.example.model.Project;
import org.example.model.log.TransferLog;
import org.example.service.log.TransferLogService;
import org.example.service.scanner.ScannerService;
import org.example.service.security.SecurityService;
import org.example.service.storage.StorageService;

import java.util.*;

public class ProjectServiceImpl implements ProjectService<Employee> {
    private final ScannerService scannerService;
    private final SecurityService securityService;
    private final StorageService<Project> storageService;
    private final TransferLogService<Employee> transferLogService;
    private final Map<Project, List<Employee>> projects;

    public ProjectServiceImpl(
            ScannerService scannerService,
            SecurityService securityService,
            StorageService<Project> storageService,
            TransferLogService<Employee> transferLogService
    ) {
        this.scannerService = scannerService;
        this.securityService = securityService;
        this.transferLogService = transferLogService;
        this.storageService = storageService;
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

        if (projects.isEmpty()) {
            System.out.println("Пусто");
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

    public void restoreProjectAssignments(List<Employee> allEmployees) {
        for (List<Employee> employees : this.projects.values()) {
            employees.clear();
        }

        for (Employee employee : allEmployees) {
            Project project = employee.getProject();
            if (project != null && this.projects.containsKey(project)) {
                this.projects.get(project).add(employee);
            }
        }
    }

    @Override
    public void setAll(List<Project> projects) {
        this.projects.clear();
        for (Project p : projects) {
            this.projects.put(p, new ArrayList<>());
        }
    }

    @Override
    public void create() {
        System.out.println("Введите название проекта");
        projects.put(new Project(scannerService.scanNonEmptyString()), new ArrayList<>());
    }

    @Override
    public List<Employee> getProjectAssignables(Project project) {
        return projects.get(project);
    }

    @Override
    public void printTransferLogs() {
        transferLogService.printLogs();
    }

    @Override
    public void printProjectAndEmployees() {
        for (Map.Entry<Project, List<Employee>> entry : projects.entrySet()) {
            String employeeString = String.join(" ", entry.getValue().stream().map(Employee::getName).toList());
            System.out.println(entry.getKey().getName() + " " + employeeString);
        }
    }

    @Override
    public void addToProject(Project project, Employee employee) {
        projects.get(project).add(employee);
        employee.setProject(project);
        transferLogService.add(new TransferLog<>(employee, null, project));
    }

    @Override
    public void removeFromProject(Employee employee) {
        Project project = employee.getProject();
        projects.get(project).remove(employee);
        employee.setProject(null);
        transferLogService.add(new TransferLog<>(employee, project, null));
    }

    @Override
    public void transfer(Project newProject, Employee employee) {
        Project oldProject = employee.getProject();
        if (oldProject == null) {
            return;
        }
        removeFromProject(employee);
        addToProject(newProject, employee);
        transferLogService.add(new TransferLog<>(employee, oldProject, newProject));
    }
}
