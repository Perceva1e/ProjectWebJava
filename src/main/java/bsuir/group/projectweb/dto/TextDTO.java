package bsuir.group.projectweb.dto;


import bsuir.group.projectweb.model.Author;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Set;

public class TextDTO {
    private Long id;
    private String information;
    private String numberOfPhone;
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<Author> authors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getNumberOfPhone() {
        return numberOfPhone;
    }

    public void setNumberOfPhone(String numberOfPhone) {
        this.numberOfPhone = numberOfPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}
