package bsuir.group.projectweb.service.impl;

import bsuir.group.projectweb.cache.TextDataCache;
import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Salary;
import bsuir.group.projectweb.model.Text;
import bsuir.group.projectweb.repository.AuthorRepositoryDAO;
import bsuir.group.projectweb.repository.SalaryRepositoryDAO;
import bsuir.group.projectweb.repository.TextRepositoryDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.HashSet;
import java.util.Set;


@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @Mock
    private TextDataCache cache = new TextDataCache();
    @Mock
    private AuthorRepositoryDAO repositoryAuthor;
    @Mock
    private TextRepositoryDAO repositoryText;
    @Mock
    private SalaryRepositoryDAO repositorySalary;
    @InjectMocks
    private AuthorServiceImpl serviceAuthor = new AuthorServiceImpl(cache,repositoryText,repositoryAuthor,repositorySalary);

    @Test
    void savePerson() {
        Text text = new Text();
        cache.putText("text", text);
        serviceAuthor.setRepositoryAuthor(repositoryAuthor);
        Author author = new Author();
        author.setFirstName("denis");
        author.setLastName("shagun");
        author.setId(1L);
        boolean isSavePerson = serviceAuthor.savePerson(author);
        Assertions.assertFalse(isSavePerson);
    }

    @Test
    void deleteAuthorConnection() {
        Text text = new Text();
        cache.putText("text", text);
        serviceAuthor.setRepositoryAuthor(repositoryAuthor);
        serviceAuthor.setRepositoryText(repositoryText);
        Text textForCheck = new Text();
        textForCheck.setInformation("hi");
        textForCheck.setId(1L);
        Salary salary = new Salary();
        salary.setId(1L);
        salary.setPrice(1200);
        Author author = new Author();
        author.setId(1L);
        author.setFirstName("denis");
        author.setLastName("shagun");
        author.setSalaries(salary);
        Set<Author> authors = new HashSet<>();
        authors.add(author);
        textForCheck.setAuthors(authors);
        Mockito.doReturn(true)
                .when(repositoryAuthor)
                .existsById(author.getId());
        Mockito.doReturn(textForCheck)
                .when(repositoryText)
                .findTextById(textForCheck.getId());
        boolean isDeleteAuthorConnection = serviceAuthor.deleteAuthorConnection(author.getId(),textForCheck.getId());
        Assertions.assertTrue(isDeleteAuthorConnection);
        Mockito.doReturn(false)
                .when(repositoryAuthor)
                .existsById(author.getId());
        isDeleteAuthorConnection = serviceAuthor.deleteAuthorConnection(author.getId(),textForCheck.getId());
        Assertions.assertFalse(isDeleteAuthorConnection);
    }

    @Test
    void addAuthorInText() {
        Text text = new Text();
        cache.putText("text", text);
        serviceAuthor.setRepositoryAuthor(repositoryAuthor);
        serviceAuthor.setRepositoryText(repositoryText);
        Text textForCheck = new Text();
        textForCheck.setInformation("hi");
        Salary salary = new Salary();
        salary.setId(1L);
        salary.setPrice(1200);
        Author author = new Author();
        author.setId(1L);
        author.setFirstName("denis");
        author.setLastName("shagun");
        author.setSalaries(salary);
        textForCheck.setAuthors(null);
        Mockito.doReturn(true)
                .when(repositoryText)
                .existsByInformation(textForCheck.getInformation());
        Mockito.doReturn(textForCheck)
                .when(repositoryText)
                .findByInformation(textForCheck.getInformation());
        boolean isAddAuthorInText = serviceAuthor.addAuthorInText(textForCheck.getInformation(), author);
        Assertions.assertTrue(isAddAuthorInText);
        Mockito.doReturn(false)
                .when(repositoryText)
                .existsByInformation(textForCheck.getInformation());
        isAddAuthorInText = serviceAuthor.addAuthorInText(textForCheck.getInformation(), author);
        Assertions.assertFalse(isAddAuthorInText);
    }
}