package br.edu.ifsp.addthenewsoul.domain.entities.asset;

public class Local {

    private Integer id;
    private Integer number;
    private String section;

    public Local(Integer id, Integer number, String section) {
        this.id = id;
        this.section = section;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String fullLocation() {
        return new StringBuilder().append(section).append(" ").append(number).toString();
    }
}
