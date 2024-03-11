package bsuir.group.projectweb.service.impl;

import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Salary;
import bsuir.group.projectweb.model.Text;
import bsuir.group.projectweb.repository.AuthorRepositoryDAO;
import bsuir.group.projectweb.repository.SalaryRepositoryDAO;
import bsuir.group.projectweb.repository.TextRepositoryDAO;
import bsuir.group.projectweb.service.TextService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor

public class TextServiceImpl implements TextService {
    static final Logger logger = LogManager.getLogger(TextServiceImpl.class);
    private final TextRepositoryDAO repositoryText;
    private final AuthorRepositoryDAO repositoryAuthor;
    private final SalaryRepositoryDAO repositorySalary;

    @Override
    public List<Text> findAllText() {
        return repositoryText.findAll();
    }

    public boolean ifNumber(final char number) {
        return (number >= '0') && (number <= '9');
    }

    public int endFindIndex(final String stringForCompare, final int firstFindIndex) {
        char[] charArray;
        int i;
        charArray = stringForCompare.toCharArray();
        for (i = firstFindIndex + 1; i < charArray.length; i++) {
            if (!ifNumber(charArray[i])) {
                return i;
            }
        }
        return i;
    }

    public boolean checkNumber(final String stringForCompare, final int firstIndex) {
        int countForArray = 0;
        char[] charArray;
        charArray = stringForCompare.toCharArray();
        for (int i = firstIndex + 1; i < charArray.length; i++) {
            if (ifNumber(charArray[i])) {
                countForArray++;
            }
            if (!ifNumber(charArray[i]) && countForArray < 12) {
                return false;
            }
            if (countForArray == 12) {
                return true;
            }
        }
        return false;
    }

    public boolean checkEmail(final String stringForCompare, final int firstIndex) {
        int countForArray = 0;
        char[] charArray;
        String domainEmail = "gmail.com";
        char[] charArrayDomain = domainEmail.toCharArray();
        charArray = stringForCompare.toCharArray();
        for (int i = firstIndex + 1; i < charArray.length; i++) {
            if (charArray[i] == charArrayDomain[countForArray]) {
                countForArray++;
            } else {
                return false;
            }
            if (countForArray == 9) {
                return true;
            }
        }
        return false;
    }

    public Text findNumberPhone(final String stringForCompare, final Text information) {
        char[] charArray;
        charArray = stringForCompare.toCharArray();
        int countForArray = 0;
        boolean checkForPlus = false;
        int firstIndex = 0;
        while (!checkForPlus) {
            firstIndex = stringForCompare.indexOf('+', firstIndex + 1);
            checkForPlus = checkNumber(stringForCompare, firstIndex);
            if (firstIndex == -1) {
                return information;
            }
        }
        int endIndex = endFindIndex(stringForCompare, firstIndex);
        char[] charArrayPhoneNumber = new char[endIndex - firstIndex];
        for (int j = firstIndex; j < endIndex; j++) {
            charArrayPhoneNumber[countForArray] = charArray[j];
            charArray[j] = '*';
            countForArray++;
        }
        String stringForUpdate = new String(charArray);
        String stringForMemory = new String(charArrayPhoneNumber);
        information.setInformation(stringForUpdate);
        information.setNumberOfPhone(stringForMemory);
        return information;
    }

    public Text findEmail(final String stringForCompare, final Text information) {
        char[] charArray;
        charArray = stringForCompare.toCharArray();
        int countForArray = 0;
        boolean checkForAt = false;
        int firstIndexAt = 0;
        while (!checkForAt) {
            firstIndexAt = stringForCompare.indexOf('@', firstIndexAt + 1);
            checkForAt = checkEmail(stringForCompare, firstIndexAt);
            if (firstIndexAt == -1) {
                return information;
            }
        }
        int endIndexAt = firstIndexAt + 9;
        char[] charArrayEmail = new char[endIndexAt - firstIndexAt + 2];
        for (int j = firstIndexAt - 1; j <= endIndexAt; j++) {
            charArrayEmail[countForArray] = charArray[j];
            charArray[j] = '*';
            countForArray++;
        }
        String stringForUpdate = new String(charArray);
        String stringForMemory = new String(charArrayEmail);
        information.setInformation(stringForUpdate);
        information.setEmail(stringForMemory);
        return information;
    }

    @Override
    public Text saveText(final Text information) {
        return repositoryText.save(information);
    }

    @Override
    public Author savePerson(final Author author) {
        repositorySalary.save(author.getSalaries());
        return repositoryAuthor.save(author);
    }

    @Override
    public  Boolean addAuthorInText(String informationExist,Author authorAdd){
        if(repositoryText.existsByInformation(informationExist))
        {
            Text informationChange=repositoryText.findByInformation(informationExist);
            Set<Author> authors = informationChange.getAuthors();
            repositorySalary.save(authorAdd.getSalaries());
            authors.add(authorAdd);
            informationChange.setAuthors(authors);
            repositoryText.save(informationChange);
            return true;
        }
        else { return false; }
    }

    @Override
    public Boolean changeSalaryById(Long id, Integer price) {
        if(repositoryAuthor.existsById(id))
        {
            Author authorChange = repositoryAuthor.findAuthorById(id);
            Salary salaryChange = authorChange.getSalaries();
            salaryChange.setPrice(price);
            authorChange.setSalaries(salaryChange);
            repositorySalary.save(salaryChange);
            repositoryAuthor.save(authorChange);
            return true;
        }
        else { return false; }
    }

    @Override
    public Boolean changeByText(final String informationExist,final String information) {
        if(repositoryText.existsByInformation(informationExist))
        {
            Text informationChange=repositoryText.findByInformation(informationExist);
            informationChange.setInformation(information);
            repositoryText.save(informationChange);
            return true;
        }
        else { return false; }
    }

    @Override
    public boolean existByText(final String information) {
        return repositoryText.existsByInformation(information);
    }

    @Override
    public boolean deleteAuthorConnection(Long idAuthor,Long idText) {
        if (repositoryAuthor.existsById(idAuthor)) {
            Text text = repositoryText.findTextById(idText);
            Set<Author> authors = text.getAuthors();
            List<Author> authorsList = new java.util.ArrayList<>(authors.stream().toList());
            for (Author author : authorsList) {
                if(author.getId() == idAuthor)
                {
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

    @Override
    public Text findByText(final String information)
    {
        return repositoryText.findByInformation(information);
    }
    @Override
    @Transactional
    public boolean deleteText(final Long id) {
        if (repositoryAuthor.existsById(id)) {
            Author authorDelete = repositoryAuthor.findAuthorById(id);
            Text text = repositoryText.findTextByAuthors(authorDelete);
            Set<Author> authors = text.getAuthors();
            List<Author> authorsList = new java.util.ArrayList<>(authors.stream().toList());
            authorsList.remove(authorDelete);
            text.setAuthors(new HashSet<>(authorsList));
            repositoryText.save(text);
            repositoryAuthor.delete(authorDelete);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAuthor(Long id) {
        if (repositoryAuthor.existsById(id)) {
            Author authorDelete = repositoryAuthor.findAuthorById(id);
            Text text = repositoryText.findTextByAuthors(authorDelete);
            Set<Author> authors = text.getAuthors();
            List<Author> authorsList = new java.util.ArrayList<>(authors.stream().toList());
            authorsList.remove(authorDelete);
            text.setAuthors(new HashSet<>(authorsList));
            repositoryText.save(text);
            repositoryAuthor.delete(authorDelete);
            return true;
        }
        return false;
    }
    @Override
    public boolean deleteSalary(Long id) {
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

    @Override
    public Text findNumberPhoneAndEmail(Text information) {
        String stringForCompare;
        stringForCompare = information.getInformation();
        information = findNumberPhone(stringForCompare, information);
        stringForCompare = information.getInformation();
        information = findEmail(stringForCompare, information);
        Set<Author> authors;
        authors = information.getAuthors();
        Author authorTemp;
        List<Author> authorsList = authors.stream().toList();
        for (Author author : authorsList) {
            authorTemp = author;
            repositorySalary.save(authorTemp.getSalaries());
        }
        return repositoryText.save(information);
    }

    @Override
    public Salary saveSalary(final Salary salaries) {
        return repositorySalary.save(salaries);
    }

    @Override
    public List<Author> findAuthorByParameters(String lastName, List<String> nameList) {
        return repositoryAuthor.findAuthorByParameters(lastName, nameList);
    }

    @Override
    @Cacheable("text")
    public Text getText(Long id) {
        logger.info("getting user by id: {}", id);
        return repositoryText.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found by id " + id));
    }
}