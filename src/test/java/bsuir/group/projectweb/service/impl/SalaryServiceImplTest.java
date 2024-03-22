package bsuir.group.projectweb.service.impl;

import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Salary;
import bsuir.group.projectweb.repository.AuthorRepositoryDAO;
import bsuir.group.projectweb.repository.SalaryRepositoryDAO;
import bsuir.group.projectweb.service.SalaryService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class SalaryServiceImplTest {

    @Autowired
    private SalaryService serviceSalary;
    @MockBean
    private SalaryRepositoryDAO repositorySalary;
    @MockBean
    private AuthorRepositoryDAO repositoryAuthor;

    @Test
    void saveSalary() {
        Salary salary = new Salary();
        salary.setId(1L);
        boolean isSaveSalary = serviceSalary.saveSalary(salary);
        Assert.assertFalse(isSaveSalary);
    }

    @Test
    void deleteSalary() {
        Salary salary = new Salary();
        salary.setId(1L);
        Mockito.doReturn(true)
                .when(repositorySalary)
                .existsById(salary.getId());
        Mockito.doReturn(salary)
                .when(repositorySalary)
                .findSalariesById(salary.getId());
        Mockito.doReturn(new Author())
                .when(repositoryAuthor)
                .findAuthorBySalaries(salary);
        boolean isDeleteSalary = serviceSalary.deleteSalaryInAuthor(salary.getId());
        Assert.assertTrue(isDeleteSalary);
        Mockito.doReturn(false)
                .when(repositorySalary)
                .existsById(salary.getId());
        isDeleteSalary = serviceSalary.deleteSalaryInAuthor(salary.getId());
        Assert.assertFalse(isDeleteSalary);
    }

    @Test
    void changeSalaryById() {
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
        boolean isChangeSalaryById = serviceSalary.changeSalaryById(author.getId(), salary.getPrice());
        Assert.assertTrue(isChangeSalaryById);
        Mockito.doReturn(false)
                .when(repositoryAuthor)
                .existsById(author.getId());
        isChangeSalaryById = serviceSalary.changeSalaryById(author.getId(), salary.getPrice());
        Assert.assertFalse(isChangeSalaryById);
    }
}