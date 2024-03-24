package bsuir.group.projectweb.service.impl;

import bsuir.group.projectweb.cache.TextDataCache;
import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Salary;
import bsuir.group.projectweb.repository.AuthorRepositoryDAO;
import bsuir.group.projectweb.repository.SalaryRepositoryDAO;
import bsuir.group.projectweb.service.SalaryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SalaryServiceImpl implements SalaryService {
    /**
     * This cache for text.
     */
    private final TextDataCache cache;

    /**
     * This is a repository of entity text.
     */
    private final AuthorRepositoryDAO repositoryAuthor;
    /**
     * This method is a repository of entity salary.
     */
    private final SalaryRepositoryDAO repositorySalary;

    /**
     * This method save Salary.
     *
     * @param salaries is an entity for save
     * @return restore salary after save
     */
    @Override
    public Boolean saveSalary(final Salary salaries) {
        if (salaries.getId() == null) {
            cache.clearText();
            repositorySalary.save(salaries);
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method delete Salary.
     *
     * @param id is an id of entity for delete
     * @return restore true if delete, else false
     */
    @Override
    public Boolean deleteSalaryInAuthor(final Long id) {
        if (repositorySalary.existsById(id)) {
            cache.clearText();
            Salary salary = repositorySalary.findSalariesById(id);
            Author author = repositoryAuthor.findAuthorBySalaries(salary);
            if (author == null) {
                return false;
            }
            author.setSalaries(null);
            repositoryAuthor.save(author);
            repositorySalary.delete(salary);
            return true;
        }
        return false;
    }

    /**
     * This method change Salary By id.
     *
     * @param id    is an id of entity
     * @param price is a string for changed
     * @return restore true if changed, else false
     */
    @Override
    public Boolean changeSalaryByIdInAuthor(
            final Long id, final Integer price) {
        if (repositoryAuthor.existsById(id)) {
            cache.clearText();
            Author authorChange = repositoryAuthor.findAuthorById(id);
            Salary salaryChange = authorChange.getSalaries();
            salaryChange.setPrice(price);
            authorChange.setSalaries(salaryChange);
            repositorySalary.save(salaryChange);
            repositoryAuthor.save(authorChange);
            return true;
        } else {
            return false;
        }
    }
}
