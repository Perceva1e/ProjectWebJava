package bsuir.group.projectweb.service.impl;

import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Salary;
import bsuir.group.projectweb.model.Text;
import bsuir.group.projectweb.repository.AuthorRepositoryDAO;
import bsuir.group.projectweb.repository.TextRepositoryDAO;
import bsuir.group.projectweb.service.AuthorService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;


@RunWith(SpringRunner.class)
@SpringBootTest
class AuthorServiceImplTest {

    @Autowired
    private AuthorService serviceAuthor;

    @MockBean
    private AuthorRepositoryDAO repositoryAuthor;
    @MockBean
    private TextRepositoryDAO repositoryText;


    @Test
    void savePerson() {
        Author author = new Author();
        author.setFirstName("denis");
        author.setLastName("shagun");
        author.setId(1L);
        boolean isSavePerson = serviceAuthor.savePerson(author);
        Assert.assertFalse(isSavePerson);
    }

    @Test
    void deleteAuthorConnection() {
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
        Assert.assertTrue(isDeleteAuthorConnection);
        Mockito.doReturn(false)
                .when(repositoryAuthor)
                .existsById(author.getId());
        isDeleteAuthorConnection = serviceAuthor.deleteAuthorConnection(author.getId(),textForCheck.getId());
        Assert.assertFalse(isDeleteAuthorConnection);
    }

    @Test
    void addAuthorInText() {
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
        Assert.assertTrue(isAddAuthorInText);
        Mockito.doReturn(false)
                .when(repositoryText)
                .existsByInformation(textForCheck.getInformation());
        isAddAuthorInText = serviceAuthor.addAuthorInText(textForCheck.getInformation(), author);
        Assert.assertFalse(isAddAuthorInText);
    }
}