package org.example.service.log;

import org.example.model.Employee;
import org.example.model.log.TransferLog;

import java.util.LinkedList;
import java.util.List;

public class LinkedListTransferLogService implements TransferLogService<Employee> {
    private final List<TransferLog<Employee>> logs = new LinkedList<>();

    @Override
    public void add(TransferLog<Employee> log) {
        logs.add(log);
    }

    @Override
    public void printLogs() {
        if (logs.isEmpty()) {
            System.out.println("Пусто");
            return;
        }

        for (TransferLog<Employee> log : logs) {
            System.out.println(log.getDescription());
        }
    }
}
