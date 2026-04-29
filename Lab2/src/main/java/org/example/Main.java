package org.example;

import org.example.model.Employee;
import org.example.model.Project;
import org.example.service.*;
import org.example.service.log.LinkedListTransferLogService;
import org.example.service.log.TransferLogService;
import org.example.service.scanner.ScannerService;
import org.example.service.scanner.SystemInService;
import org.example.service.security.SecurityService;
import org.example.service.security.SecurityServiceImpl;
import org.example.service.storage.FileStorageService;
import org.example.service.storage.StorageService;

public class Main {
    public static void main(String[] args) {
        ScannerService scannerService = SystemInService.INSTANCE;

        StorageService<Employee> employeeStorageService = new FileStorageService<>();
        StorageService<Project> projectStorageService = new FileStorageService<>();
        GlobalStorageService globalStorageService = new GlobalStorageService();

        EmployeeFactory employeeFactory = new EmployeeFactory(scannerService);
        SecurityService securityService = new SecurityServiceImpl(scannerService);
        TransferLogService<Employee> transferLogService = new LinkedListTransferLogService();

        EmployeeService employeeService = new EmployeeServiceImpl(scannerService, employeeStorageService, securityService, employeeFactory);
        ProjectService<Employee> projectService = new ProjectServiceImpl(scannerService, securityService, projectStorageService, transferLogService);

        Solution solution = new Solution(scannerService, employeeService, projectService, globalStorageService);

        solution.solve();
    }
}
