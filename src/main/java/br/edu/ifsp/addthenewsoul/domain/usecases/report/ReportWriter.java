package br.edu.ifsp.addthenewsoul.domain.usecases.report;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;

import java.io.IOException;

public interface ReportWriter<T> {
    void write(String filename, T t) throws IOException;
}
