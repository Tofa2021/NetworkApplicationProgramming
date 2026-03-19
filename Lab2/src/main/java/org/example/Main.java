package org.example;

import org.example.model.Project;
import org.example.service.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ScannerService scannerService = SystemInService.INSTANCE;

        Solution.solve(
                scannerService,
                new EmployeeServiceImpl(scannerService),
                new ProjectServiceImpl(
                        scannerService,
                        new ProjectTransferLogger(),
                        new ArrayList<>(List.of(
                                new Project("AbacusDelivery"),
                                new Project("DiceHome")
                        ))
                ),
                new EmployeeFactory(scannerService)
        );
    }
}
