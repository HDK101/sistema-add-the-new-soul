package br.edu.ifsp.addthenewsoul.domain.entities.asset;

public class Local {
    private String section;
    private int number;
    private int id;

    public Local(int id, String section, int number) {
        this.section = section;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String fullLocation() {
        return new StringBuilder().append(section).append("").append(number).toString();
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
