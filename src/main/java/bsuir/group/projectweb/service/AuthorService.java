package bsuir.group.projectweb.service;

import bsuir.group.projectweb.model.Author;

import java.util.List;

public interface AuthorService {
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
    /**This method demonstrates javadoc format.
     *@param lastName is a lastname for find
     *@param nameList is a list of name for find
     *@return restore list of author after finding
     */
    List<Author> findAuthorByParameters(String lastName, List<String> nameList);
    /**This method demonstrates javadoc format.
     * @param idAuthor is an id entity for delete
     * @param idText is an id entity for delete
     * @return true is ok, false is error
     */
    boolean deleteAuthorConnection(Long idAuthor, Long idText);
}
