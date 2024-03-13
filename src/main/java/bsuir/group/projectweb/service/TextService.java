package bsuir.group.projectweb.service;

import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Salary;
import bsuir.group.projectweb.model.Text;

import java.util.List;


public interface TextService {

    /**
     * This method demonstrates javadoc format.
     * @return restore a list of entity Text
     */
    List<Text> findAllText();

    /**
     * This method demonstrates javadoc format.
     * @param information is an input parameter
     * @return restore an entity text
     */
    Text saveText(Text information);

    /**
     * This method demonstrates javadoc format.
     * @param author is an entity for save
     * @return restore an entity Author
     */
    Author savePerson(Author author);

    /**
     * This method demonstrates javadoc format.
     * @param informationExist is an existing information
     * @param authorAdd        is an author for save
     * @return true is ok, false is error
     */
    Boolean addAuthorInText(String informationExist, Author authorAdd);

    /**
     * This method demonstrates javadoc format.
     * @param id    is an id of salary
     * @param price is price for change
     * @return true is ok, false is error
     */
    Boolean changeSalaryById(Long id, Integer price);

    /**
     * This method demonstrates javadoc format.
     * @param informationExist is an existing information
     * @param information is information for change
     * @return true is ok, false is error
     */
    Boolean changeByText(String informationExist, String information);

    /**
     * This method demonstrates javadoc format.
     * @param information is information for find
     * @return restore true is ok, else false
     */
    boolean findByText(String information);

    /**
     * This method demonstrates javadoc format.
     * @param id is an id of text for delete
     * @return true is ok, false is error
     */
    boolean deleteText(Long id);

    /**
     * This method demonstrates javadoc format.
     * @param id is an id of text for delete
     * @return true is ok, false is error
     */
    boolean deleteSalary(Long id);
    /** This method demonstrates javadoc format.
     * @param information is a text for hard code
     * @return restore text after hard code
     */
    Text findNumberPhoneAndEmail(Text information);
    /**This method demonstrates javadoc format.
     *@param lastName is a lastname for find
     *@param nameList is a list of name for find
     *@return restore list of author after finding
     */
    List<Author> findAuthorByParameters(String lastName, List<String> nameList);
    /**This method demonstrates javadoc format.
     *@param salary is an entity for save
     *@return restore salary after save
     */
    Salary saveSalary(Salary salary);
    /**This method demonstrates javadoc format.
     *@param id is an id of entity for get
     *@return restore text
     */
    Text getText(Long id);
    /**This method demonstrates javadoc format.
     * @param information is an entity existing
     * @return true is ok, false is error
     */
    boolean existByText(String information);
    /**This method demonstrates javadoc format.
     * @param idAuthor is an id entity for delete
     * @param idText is an id entity for delete
     * @return true is ok, false is error
     */
    boolean deleteAuthorConnection(Long idAuthor, Long idText);
}
