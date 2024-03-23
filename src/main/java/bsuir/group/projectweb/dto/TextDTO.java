package bsuir.group.projectweb.dto;


import bsuir.group.projectweb.model.Author;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;

public class TextDTO {
    /**
     * This id of object.
     *
     * @param id is an id of text
     */
    private Long id;
    /**
     * This information.
     *
     * @param information is an information of text
     */
    private String information;
    /**
     * This number of phone.
     *
     * @param numberOfPhone is a confidence data
     */
    private String numberOfPhone;
    /**
     * This email.
     *
     * @param email is a confidence data
     */
    private String email;
    /**
     * This many to many with authors.
     *
     * @param authors is a set of author for link
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<Author> authors;

    /**
     * This method get id.
     *
     * @return id of text
     */
    public Long getId() {
        return id;
    }

    /**
     * This method set idText.
     *
     * @param idText is an idText of text
     */
    public void setId(final Long idText) {
        this.id = idText;
    }

    /**
     * This method get information.
     *
     * @return information of text
     */
    public String getInformation() {
        return information;
    }

    /**
     * This method set informationText.
     *
     * @param informationText is an informationText of text
     */
    public void setInformation(final String informationText) {
        this.information = informationText;
    }

    /**
     * This method get numberOfPhone.
     *
     * @return numberOfPhone of text
     */
    public String getNumberOfPhone() {
        return numberOfPhone;
    }

    /**
     * This method set numberOfPhoneText.
     *
     * @param numberOfPhoneText is an numberOfPhoneText of text
     */
    public void setNumberOfPhone(final String numberOfPhoneText) {
        this.numberOfPhone = numberOfPhoneText;
    }

    /**
     * This method get email.
     *
     * @return email of text
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method set emailText.
     *
     * @param emailText is an emailText of text
     */
    public void setEmail(final String emailText) {
        this.email = emailText;
    }

    /**
     * This method get authors.
     *
     * @return authors of text
     */
    public Set<Author> getAuthors() {
        return authors;
    }

    /**
     * This method set authorsText.
     *
     * @param authorsText is an authorsText of text
     */
    public void setAuthors(final Set<Author> authorsText) {
        this.authors = authorsText;
    }
}
