package org.example.service;

import org.example.service.storage.CompanyData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GlobalStorageService {
    private final String FILE_NAME = "company.dat";

    public void save(CompanyData data) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(FILE_NAME)))) {
            objectOutputStream.writeObject(data);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл");
        }
    }

    public CompanyData load() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(Paths.get(FILE_NAME)))) {
            return (CompanyData) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка чтения из файла");
            return new CompanyData(new ArrayList<>(), new ArrayList<>());
        }
    }
}
