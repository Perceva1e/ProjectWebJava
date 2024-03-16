package bsuir.group.projectweb.service;

import bsuir.group.projectweb.model.Text;

import java.util.List;


public interface TextService {

    /**
     * This method find All Text.
     *
     * @return restore a list of entity Text
     */
    List<Text> findAllText();

    /**
     * This method save Text.
     *
     * @param information is an input parameter
     * @return restore an entity text
     */
    Text saveText(Text information);

    /**
     * This method change By Text.
     *
     * @param informationExist is an existing information
     * @param information      is information for change
     * @return true is ok, false is error
     */
    Boolean changeByText(String informationExist, String information);

    /**
     * This method find by Text.
     *
     * @param information is information for find
     * @return restore true is ok, else false
     */
    boolean findByText(String information);

    /**
     * This method deleteText.
     *
     * @param id is an id of text for delete
     * @return true is ok, false is error
     */
    boolean deleteText(Long id);

    /**
     * This method find Number Phone and Email.
     *
     * @param information is a text for hard code
     * @return restore text after hard code
     */
    Text findNumberPhoneAndEmail(Text information);
}
