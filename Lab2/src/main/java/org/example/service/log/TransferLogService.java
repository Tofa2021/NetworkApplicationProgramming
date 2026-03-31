package org.example.service.log;

import org.example.model.interfaces.Nameable;
import org.example.model.interfaces.ProjectAssignable;
import org.example.model.log.TransferLog;

public interface TransferLogService<T extends ProjectAssignable & Nameable> {
    void add(TransferLog<T> log);

    void printLogs();
}
