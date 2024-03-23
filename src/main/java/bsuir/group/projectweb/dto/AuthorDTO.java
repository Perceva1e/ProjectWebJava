package bsuir.group.projectweb.dto;

import bsuir.group.projectweb.model.Salary;

public class AuthorDTO {
    private Long id;
    private String firstName;
    private String lastName;

    private Salary price;

    public Salary getPrice() {
        return price;
    }

    public void setPrice(Salary price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
