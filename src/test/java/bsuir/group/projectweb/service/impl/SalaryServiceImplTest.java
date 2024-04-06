package bsuir.group.projectweb.service.impl;

import bsuir.group.projectweb.cache.TextDataCache;
import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Salary;
import bsuir.group.projectweb.model.Text;
import bsuir.group.projectweb.repository.AuthorRepositoryDAO;
import bsuir.group.projectweb.repository.SalaryRepositoryDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class SalaryServiceImplTest {
    @Mock
    private TextDataCache cache = new TextDataCache();
    @Mock
    private SalaryRepositoryDAO repositorySalary;

    @Mock
    private AuthorRepositoryDAO repositoryAuthor;
    @InjectMocks
    private SalaryServiceImpl serviceSalary = new SalaryServiceImpl(cache,repositoryAuthor,repositorySalary);

    @Test
    void saveSalary() {
        Text text = new Text();
        cache.putText("text", text);
        Salary salary = new Salary();
        serviceSalary.setRepositorySalary(repositorySalary);
        boolean isSaveSalary = serviceSalary.saveSalary(salary);
        Assertions.assertTrue(isSaveSalary);
        salary.setId(1L);
        isSaveSalary = serviceSalary.saveSalary(salary);
        Assertions.assertFalse(isSaveSalary);
    }
    @Test
    void deleteSalary() {
        Text text = new Text();
        cache.putText("text", text);
        serviceSalary.setRepositorySalary(repositorySalary);
        serviceSalary.setRepositoryAuthor(repositoryAuthor);
        Salary salary = new Salary();
        salary.setId(1L);
        Mockito.doReturn(true)
                .when(repositorySalary)
                .existsById(salary.getId());
        Mockito.doReturn(salary)
                .when(repositorySalary)
                .findSalariesById(salary.getId());
        Mockito.doReturn(null)
                .when(repositoryAuthor)
                .findAuthorBySalaries(salary);
        boolean isDeleteSalary = serviceSalary.deleteSalaryInAuthor(salary.getId());
        Assertions.assertFalse(isDeleteSalary);
        Mockito.doReturn(false)
                .when(repositorySalary)
                .existsById(salary.getId());
        isDeleteSalary = serviceSalary.deleteSalaryInAuthor(salary.getId());
        Assertions.assertFalse(isDeleteSalary);
        Mockito.doReturn(true)
                .when(repositorySalary)
                .existsById(salary.getId());
        Mockito.doReturn(salary)
                .when(repositorySalary)
                .findSalariesById(salary.getId());
        Mockito.doReturn(new Author())
                .when(repositoryAuthor)
                .findAuthorBySalaries(salary);
        isDeleteSalary = serviceSalary.deleteSalaryInAuthor(salary.getId());
        Assertions.assertTrue(isDeleteSalary);
    }
    @Test
    void changeSalaryById() {
        Text text = new Text();
        cache.putText("text", text);
        serviceSalary.setRepositorySalary(repositorySalary);
        serviceSalary.setRepositoryAuthor(repositoryAuthor);
        Salary salary = new Salary();
        salary.setId(1L);
        salary.setPrice(12000);
        Author author = new Author();
        author.setId(1L);
        author.setSalaries(salary);
        Mockito.doReturn(true)
                .when(repositoryAuthor)
                .existsById(author.getId());
        Mockito.doReturn(author)
                .when(repositoryAuthor)
                .findAuthorById(author.getId());
        boolean isChangeSalaryById = serviceSalary.changeSalaryByIdInAuthor(author.getId(), salary.getPrice());
        Assertions.assertTrue(isChangeSalaryById);
        Mockito.doReturn(false)
                .when(repositoryAuthor)
                .existsById(author.getId());
        isChangeSalaryById = serviceSalary.changeSalaryByIdInAuthor(author.getId(), salary.getPrice());
        Assertions.assertFalse(isChangeSalaryById);
    }
}