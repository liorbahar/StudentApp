package com.example.studentsapp.model;
import java.util.LinkedList;
import java.util.List;

public class Model {
    private static final Model _instance = new Model();

    public static Model instance() {
        return _instance;
    }

    private Model() {
    }

    List<Student> data = new LinkedList<>();

    public List<Student> getAllStudents() {
        return this.data;
    }

    public void addStudent(Student st) {
        this.data.add(st);
    }

    public void editStudent(Student student) {
        int studentIndex = this.data.indexOf(student);
        this.data.set(studentIndex, student);
    }

    public void removeStudent(Student st) {
        this.data.remove(st);
    }
}