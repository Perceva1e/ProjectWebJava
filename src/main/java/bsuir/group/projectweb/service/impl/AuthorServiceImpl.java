package bsuir.group.projectweb.service.impl;

import bsuir.group.projectweb.cache.TextDataCache;
import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Text;
import bsuir.group.projectweb.repository.AuthorRepositoryDAO;
import bsuir.group.projectweb.repository.SalaryRepositoryDAO;
import bsuir.group.projectweb.repository.TextRepositoryDAO;
import bsuir.group.projectweb.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    /**
     * This is setter for repository of text.
     * @param repositoryTextSet repository for set
     */
    public void setRepositoryText(final TextRepositoryDAO repositoryTextSet) {
        this.repositoryText = repositoryTextSet;
    }
    /**
     * This is setter for repository of author.
     * @param repositoryAuthorSet repository for set
     */
    public void setRepositoryAuthor(
            final AuthorRepositoryDAO repositoryAuthorSet) {
        this.repositoryAuthor = repositoryAuthorSet;
    }
    /**
     * This cache for text.
     */
    private final TextDataCache cache;
    /**
     * This is a repository of entity text.
     */
    private TextRepositoryDAO repositoryText;
    /**
     * This is a repository of entity author.
     */
    private AuthorRepositoryDAO repositoryAuthor;
    /**
     * This is a repository of entity salary.
     */
    private SalaryRepositoryDAO repositorySalary;

    /**
     * This savePerson.
     *
     * @param author is an entity of author for save
     * @return restore author after save
     */

    @Override
    public Boolean savePerson(final Author author) {
        if (author.getId() == null) {
            repositorySalary.save(author.getSalaries());
            repositoryAuthor.save(author);
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method delete Author Connection.
     *
     * @param idAuthor is an id of entity for delete
     * @param idText   is an id of entity for delete
     * @return restore true if delete, else false
     */
    @Override
    public boolean deleteAuthorConnection(final Long idAuthor,
                                          final Long idText) {
        if (repositoryAuthor.existsById(idAuthor)) {
            cache.clearText();
            Text text = repositoryText.findTextById(idText);
            Set<Author> authors = text.getAuthors();
            List<Author> authorsList = new java.util.ArrayList<>(
                    authors.stream().toList());
            for (Author author : authorsList) {
                if (author.getId().equals(idAuthor)) {
                    authorsList.remove(author);
                    break;
                }
            }
            text.setAuthors(new HashSet<>(authorsList));
            repositoryText.save(text);
            return true;
        }
        return false;
    }

    /**
     * This method add Author In Text.
     *
     * @param informationExist is a string exists
     * @param authorAdd        is an entity for add
     * @return restore true if changed, else false
     */
    @Override
    public Boolean addAuthorInText(final String informationExist,
                                   final Author authorAdd) {
        if (repositoryText.existsByInformation(informationExist)) {
            cache.clearText();
            Text informationChange = repositoryText.
                    findByInformation(informationExist);
            Set<Author> authors = informationChange.getAuthors();
            if (authors == null) {
                authors = new HashSet<>();
                repositorySalary.save(authorAdd.getSalaries());
                authors.add(authorAdd);
                informationChange.setAuthors(authors);
                repositoryText.save(informationChange);
                return true;
            }
            repositorySalary.save(authorAdd.getSalaries());
            authors.add(authorAdd);
            informationChange.setAuthors(authors);
            repositoryText.save(informationChange);
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method find Author By Parameters.
     *
     * @param lastName is a string for find
     * @param nameList is a list of string for find
     * @return restore list author after find
     */
    @Override
    public List<Author> findAuthorByParameters(final String lastName,
                                               final List<String> nameList) {
        return repositoryAuthor.findAuthorByParameters(lastName, nameList);
    }
}
