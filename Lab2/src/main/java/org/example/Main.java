package org.example;

import org.example.model.Project;
import org.example.service.EmployeeServiceImpl;
import org.example.service.ProjectServiceImpl;
import org.example.service.SystemInService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution.solve(
                SystemInService.INSTANCE,
                new EmployeeServiceImpl(),
                new ProjectServiceImpl(
                        new ArrayList<>(List.of(
                                new Project("AbacusDelivery"),
                                new Project("DiceHome")
                        ))
                )
        );
    }
}
