package br.edu.ifsp.addthenewsoul.application.writers;

import com.opencsv.bean.CsvBindByName;

public class CSVBean {
    @CsvBindByName()
    public String n1;
    @CsvBindByName
    public String n2;
    @CsvBindByName
    public String n3;

    public CSVBean(String n1, String n2, String n3) {
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
    }
}