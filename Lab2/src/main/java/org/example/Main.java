package org.example;

import org.example.model.Employee;
import org.example.service.*;

public class Main {
    public static void main(String[] args) {
        ScannerService scannerService = SystemInService.INSTANCE;

        StorageService<Employee> employeeStorageService = new FileStorageService();

        EmployeeFactory employeeFactory = new EmployeeFactory(scannerService);
        SecurityService securityService = new SecurityServiceImpl(scannerService);

        EmployeeService employeeService = new EmployeeServiceImpl(scannerService, employeeStorageService, securityService, employeeFactory);
        ProjectService projectService = new ProjectServiceImpl(scannerService, securityService);

        Solution solution = new Solution(scannerService, employeeService, projectService);

        solution.solve();
    }
}
