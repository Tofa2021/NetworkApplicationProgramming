package org.example;

import org.example.model.Employee;
import org.example.service.EmployeeService;
import org.example.service.EmployeeServiceImpl;
import org.example.service.ProjectService;
import org.example.service.ProjectServiceImpl;
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

        StorageService<Employee> employeeStorageService = new FileStorageService();

        EmployeeFactory employeeFactory = new EmployeeFactory(scannerService);
        SecurityService securityService = new SecurityServiceImpl(scannerService);
        TransferLogService<Employee> transferLogService = new LinkedListTransferLogService();

        EmployeeService employeeService = new EmployeeServiceImpl(scannerService, employeeStorageService, securityService, employeeFactory);
        ProjectService<Employee> projectService = new ProjectServiceImpl(scannerService, securityService, transferLogService);

        Solution solution = new Solution(scannerService, employeeService, projectService);

        solution.solve();
    }
}
