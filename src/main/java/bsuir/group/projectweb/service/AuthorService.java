package bsuir.group.projectweb.service;

import bsuir.group.projectweb.model.Author;

import java.util.List;

public interface AuthorService {
    /**
     * This method save Person.
     * @param author is an entity for save
     * @return true is a save, else false
     */
    Boolean savePerson(Author author);
    /**
     * This method add Author in Text.
     * @param informationExist is an existing information
     * @param authorAdd        is an author for save
     * @return true is ok, false is error
     */
    Boolean addAuthorInText(String informationExist, Author authorAdd);
    /**This method find Author by Parameters.
     *@param lastName is a lastname for find
     *@param nameList is a list of name for find
     *@return restore list of author after finding
     */
    List<Author> findAuthorByParameters(String lastName, List<String> nameList);
    /**This method delete Author Connection.
     * @param idAuthor is an id entity for delete
     * @param idText is an id entity for delete
     * @return true is ok, false is error
     */
    boolean deleteAuthorConnection(Long idAuthor, Long idText);
}
