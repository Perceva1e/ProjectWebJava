package bsuir.group.projectweb.service.impl;

import bsuir.group.projectweb.cache.TextDataCache;
import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Salary;
import bsuir.group.projectweb.model.Text;
import bsuir.group.projectweb.repository.AuthorRepositoryDAO;
import bsuir.group.projectweb.repository.SalaryRepositoryDAO;
import bsuir.group.projectweb.service.SalaryService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;

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
}