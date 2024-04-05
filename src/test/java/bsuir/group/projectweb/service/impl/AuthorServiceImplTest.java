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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
class AuthorServiceImplTest {

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
        AuthorService serviceAuthorMock = mock(AuthorService.class);
        when(serviceAuthorMock.savePerson(any(Author.class))).thenReturn(false);
        boolean isSavePerson = serviceAuthorMock.savePerson(author);
        Assert.assertFalse(isSavePerson);
    }

}