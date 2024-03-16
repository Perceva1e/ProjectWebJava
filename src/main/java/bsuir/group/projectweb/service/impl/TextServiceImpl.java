package bsuir.group.projectweb.service.impl;

import bsuir.group.projectweb.cache.TextDataCache;
import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Text;
import bsuir.group.projectweb.repository.AuthorRepositoryDAO;
import bsuir.group.projectweb.repository.SalaryRepositoryDAO;
import bsuir.group.projectweb.repository.TextRepositoryDAO;
import bsuir.group.projectweb.service.TextService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class TextServiceImpl implements TextService {
    /**
     * This cache for text.
     */
    private final TextDataCache cache;

    /**
     * This logger.
     *
     * @param LOGGER is a server
     */
    static final Logger LOGGER = LogManager.getLogger(TextServiceImpl.class);
    /**
     * This is a repository of entity text.
     */
    private final TextRepositoryDAO repositoryText;
    /**
     * This is a repository of entity author.
     */
    private final AuthorRepositoryDAO repositoryAuthor;
    /**
     * This is a repository of entity salary.
     */
    private final SalaryRepositoryDAO repositorySalary;

    /**
     * This method find All Text.
     *
     * @return restore list of text
     */
    @SneakyThrows
    @Override
    public List<Text> findAllText() {
        List<Text> text = (List<Text>) cache.getText("text");
        if (text == null) {
            TimeUnit.SECONDS.sleep(5);
            text = repositoryText.findAll();
            cache.putText("text", text);
            LOGGER.info("input list in cache");
        } else {
            LOGGER.info("text from cache");
        }
        return text;
    }

    /**
     * This method if Number.
     *
     * @param number is a char number
     * @return restore true if a number, else false
     */
    public boolean ifNumber(final char number) {
        return (number >= '0') && (number <= '9');
    }

    /**
     * This method end Find Index.
     *
     * @param stringForCompare is a string for compare
     * @param firstFindIndex   is an index of
     * @return restore index
     */
    public int endFindIndex(final String stringForCompare,
                            final int firstFindIndex) {
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

    /**
     * This method check Number.
     *
     * @param stringForCompare is a string for compare
     * @param firstIndex       is an index of start email
     * @return restore true if a number, else false
     */
    public boolean checkNumber(final String stringForCompare,
                               final int firstIndex) {
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

    /**
     * This method check Email.
     *
     * @param stringForCompare is a string for compare
     * @param firstIndex       is an index of start email
     * @return restore true if a email, else false
     */
    public boolean checkEmail(final String stringForCompare,
                              final int firstIndex) {
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

    /**
     * This method find Number Phone.
     *
     * @param stringForCompare is a string for compare
     * @param information      is an entity of text
     * @return restore text after hard code
     */
    public Text findNumberPhone(final String stringForCompare,
                                final Text information) {
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

    /**
     * This method find Email.
     *
     * @param stringForCompare is a string for compare
     * @param information      is an entity of text
     * @return restore text after hard code
     */
    public Text findEmail(final String stringForCompare,
                          final Text information) {
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

    /**
     * This method save Text.
     *
     * @param information is an entity of text for save
     * @return restore text after save
     */
    @Override
    public Text saveText(final Text information) {
        cache.clearText();
        LOGGER.info("save text");
        return repositoryText.save(information);
    }

    /**
     * This method change By Text.
     *
     * @param informationExist is an id of entity exists
     * @param information      is an entity for changed
     * @return restore true if changed, else false
     */
    @Override
    public Boolean changeByText(final String informationExist,
                                final String information) {
        if (repositoryText.existsByInformation(informationExist)) {
            LOGGER.info("change by text");
            Text informationChange = repositoryText.
                    findByInformation(informationExist);
            informationChange.setInformation(information);
            repositoryText.save(informationChange);
            cache.clearText();
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method check Id.
     *
     * @param firstText      is an entity for compare
     * @param textForCompare is an entity for compare
     * @return restore true if same, else false
     */
    public boolean checkId(final Text firstText, final Text textForCompare) {
        return !textForCompare.getId().equals(firstText.getId());
    }

    /**
     * This method find By Text.
     *
     * @param information is an id of entity for find
     * @return restore true if ok, else false
     */
    @Override
    public boolean findByText(final String information) {
        LOGGER.info("find by text");
        boolean checkError = false;
        List<Text> textList = repositoryText.findAll();
        Text firstText = repositoryText.findFirstByInformation(information);
        for (Text text : textList) {
            if (text.getInformation().equals(firstText.getInformation())) {
                checkError = checkId(firstText, text);
            }
        }
        return !checkError;
    }

    /**
     * This method delete Text.
     *
     * @param id is an id of entity for delete
     * @return restore true if delete, else false
     */
    @Override
    @Transactional
    public boolean deleteText(final Long id) {
        if (repositoryAuthor.existsById(id)) {
            LOGGER.info("delete text");
            Author authorDelete = repositoryAuthor.findAuthorById(id);
            Text text = repositoryText.findTextByAuthors(authorDelete);
            Set<Author> authors = text.getAuthors();
            List<Author> authorsList = new java.util.ArrayList<>(
                    authors.stream().toList());
            authorsList.remove(authorDelete);
            text.setAuthors(new HashSet<>(authorsList));
            repositoryText.save(text);
            repositoryAuthor.delete(authorDelete);
            cache.clearText();
            return true;
        }
        return false;
    }

    /**
     * This method find Number Phone And Email.
     *
     * @param information is an entity for hard code
     * @return restore text after hard code
     */
    @Override
    public Text findNumberPhoneAndEmail(Text information) {
        LOGGER.info("make main task");
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
}