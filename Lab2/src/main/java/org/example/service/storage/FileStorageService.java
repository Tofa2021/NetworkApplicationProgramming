package org.example.service.storage;

import org.example.model.Employee;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileStorageService implements StorageService<Employee> {
    private final String FILE_NAME = "employees.dat";

    @Override
    public void save(List<Employee> employees) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(FILE_NAME)))) {
            objectOutputStream.writeObject(employees);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл");
        }
    }

    @Override
    public List<Employee> load() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(Paths.get(FILE_NAME)))) {
            return (List<Employee>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка чтения из файла");
            return new ArrayList<>();
        }
    }
}
