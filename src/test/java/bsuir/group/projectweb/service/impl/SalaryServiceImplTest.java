package bsuir.group.projectweb.service.impl;

import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Salary;
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

    @InjectMocks
    private SalaryServiceImpl serviceSalary;

    @Mock
    private SalaryRepositoryDAO repositorySalary;

    @Mock
    private AuthorRepositoryDAO repositoryAuthor;

    @Test
    void saveSalary() {
        Salary salary = new Salary();
        salary.setId(1L);
        boolean isSaveSalary = serviceSalary.saveSalary(salary);
        Assert.assertFalse(isSaveSalary);
    }
}