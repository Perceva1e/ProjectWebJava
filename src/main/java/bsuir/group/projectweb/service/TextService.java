package bsuir.group.projectweb.service;

import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Salary;
import bsuir.group.projectweb.model.Text;
import java.util.List;


public interface TextService {

    List<Text> findAllText() ;
    Text saveText(Text information) ;
    Author savePerson(Author author);
    Boolean addAuthorInText(String informationExist, Author authorAdd);
    Boolean changeSalaryById(Long id, Integer price);
    Boolean changeByText(final String informationExist, final String information);
    Text findByText(String information) ;
    boolean deleteText(Long id);
    boolean deleteAuthor(Long id);
    boolean deleteSalary(Long id);
    Text findNumberPhoneAndEmail(Text information);
    List<Author> findAuthorByParameters(String lastName, List<String> nameList);
    Salary saveSalary(Salary salary);
    Text getText(Long id);
    boolean existByText(final String information);
}
