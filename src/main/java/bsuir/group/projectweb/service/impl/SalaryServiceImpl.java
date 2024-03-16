package bsuir.group.projectweb.service.impl;

import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Salary;
import bsuir.group.projectweb.repository.AuthorRepositoryDAO;
import bsuir.group.projectweb.repository.SalaryRepositoryDAO;
import bsuir.group.projectweb.service.SalaryService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SalaryServiceImpl implements SalaryService {
    /**
     * This method demonstrates javadoc format.
     *
     * @param LOGGER is a server
     */
    static final Logger LOGGER = LogManager.getLogger(TextServiceImpl.class);
    /**
     * This method demonstrates javadoc format.
     * is a repository of entity text
     */
    private final AuthorRepositoryDAO repositoryAuthor;
    /**
     * This method demonstrates javadoc format.
     * is a repository of entity salary
     */
    private final SalaryRepositoryDAO repositorySalary;

    /**
     * This method demonstrates javadoc format.
     *
     * @param salaries is an entity for save
     * @return restore salary after save
     */
    @Override
    public Salary saveSalary(final Salary salaries) {
        return repositorySalary.save(salaries);
    }

    /**
     * This method demonstrates javadoc format.
     *
     * @param id is an id of entity for delete
     * @return restore true if delete, else false
     */
    @Override
    public boolean deleteSalary(final Long id) {
        if (repositorySalary.existsById(id)) {
            Salary salary = repositorySalary.findSalariesById(id);
            Author author = repositoryAuthor.findAuthorBySalaries(salary);
            author.setSalaries(null);
            repositoryAuthor.save(author);
            repositorySalary.delete(salary);
            return true;
        }
        return false;
    }

    /**
     * This method demonstrates javadoc format.
     *
     * @param id    is an id of entity
     * @param price is a string for changed
     * @return restore true if changed, else false
     */
    @Override
    public Boolean changeSalaryById(final Long id, final Integer price) {
        if (repositoryAuthor.existsById(id)) {
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
