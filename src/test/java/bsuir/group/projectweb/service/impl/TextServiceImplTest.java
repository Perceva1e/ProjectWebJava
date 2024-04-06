package bsuir.group.projectweb.service.impl;

import bsuir.group.projectweb.cache.TextDataCache;
import bsuir.group.projectweb.dto.BulkTextRequestDTO;
import bsuir.group.projectweb.dto.TextDTO;
import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Salary;
import bsuir.group.projectweb.model.Text;
import bsuir.group.projectweb.repository.AuthorRepositoryDAO;
import bsuir.group.projectweb.repository.SalaryRepositoryDAO;
import bsuir.group.projectweb.repository.TextRepositoryDAO;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@ExtendWith(MockitoExtension.class)
class TextServiceImplTest {
    @Mock
    private TextDataCache cache = new TextDataCache();
    @Mock
    private SalaryRepositoryDAO repositorySalary;
    @Mock
    private AuthorRepositoryDAO repositoryAuthor;
    @Mock
    private TextRepositoryDAO repositoryText;

    @InjectMocks
    private TextServiceImpl serviceText = new TextServiceImpl(cache, repositoryText, repositoryAuthor, repositorySalary);

    @Test
    void checkId() {
        Text textFirstCheck = new Text();
        textFirstCheck.setId(1L);
        Text textLastCheck = new Text();
        textLastCheck.setId(1L);
        boolean isCheckId = serviceText.checkId(textFirstCheck, textLastCheck);
        Assertions.assertFalse(isCheckId);
        textLastCheck.setId(2L);
        isCheckId = serviceText.checkId(textFirstCheck, textLastCheck);
        Assertions.assertTrue(isCheckId);
    }

    @Test
    void saveText() {
        Text textForCheck = new Text();
        Salary salary = new Salary();
        salary.setId(1L);
        salary.setPrice(1200);
        Author author = new Author();
        author.setId(1L);
        author.setFirstName("denis");
        author.setLastName("shagun");
        author.setSalaries(salary);
        Set<Author> authors = new HashSet<>();
        authors.add(author);
        textForCheck.setAuthors(authors);
        boolean isTextSave = serviceText.saveText(textForCheck);
        Assertions.assertTrue(isTextSave);
        textForCheck.setId(1L);
        isTextSave = serviceText.saveText(textForCheck);
        Assertions.assertFalse(isTextSave);
    }

    @Test
    void changeByText() {
        Text textForCheck = new Text();
        String informationExist = "Hi";
        textForCheck.setInformation("Hi");
        String information = "Hu";
        Mockito.doReturn(true)
                .when(repositoryText)
                .existsByInformation("Hi");
        Mockito.doReturn(new Text())
                .when(repositoryText)
                .findByInformation("Hi");
        boolean isChangeByText = serviceText.changeByText(informationExist, information);
        Assertions.assertTrue(isChangeByText);
        Mockito.doReturn(false)
                .when(repositoryText)
                .existsByInformation("Hi");
        isChangeByText = serviceText.changeByText(informationExist, information);
        Assertions.assertFalse(isChangeByText);
    }

    @Test
    void deleteText() {
        Text textForCheck = new Text();
        Salary salary = new Salary();
        salary.setId(1L);
        salary.setPrice(1200);
        Author author = new Author();
        author.setId(1L);
        author.setFirstName("denis");
        author.setLastName("shagun");
        author.setSalaries(salary);
        Set<Author> authors = new HashSet<>();
        authors.add(author);
        textForCheck.setAuthors(authors);
        Mockito.doReturn(true)
                .when(repositoryAuthor)
                .existsById(author.getId());
        Mockito.doReturn(author)
                .when(repositoryAuthor)
                .findAuthorById(author.getId());
        Mockito.doReturn(textForCheck)
                .when(repositoryText)
                .findTextByAuthors(author);
        boolean isDeleteText = serviceText.deleteAuthorInText(author.getId());
        Assertions.assertTrue(isDeleteText);
        Mockito.doReturn(false)
                .when(repositoryAuthor)
                .existsById(author.getId());
        isDeleteText = serviceText.deleteAuthorInText(author.getId());
        Assertions.assertFalse(isDeleteText);
    }

    @Test
    void ifNumber() {
        char number = '4';
        boolean isIfNumber = serviceText.ifNumber(number);
        Assertions.assertTrue(isIfNumber);
        char letters = 'a';
        isIfNumber = serviceText.ifNumber(letters);
        Assertions.assertFalse(isIfNumber);

    }

    @Test
    void endFindIndex() {
        String stringForCompare = "da+375295297796net";
        int firstFindIndex = 2;
        int isEndFindIndex = serviceText.endFindIndex(stringForCompare, firstFindIndex);
        Assertions.assertEquals(14, isEndFindIndex);
        Assertions.assertNotEquals(15, isEndFindIndex);
    }

    @Test
    void checkNumber() {
        String stringForCompare = "da+375295297796net";
        int firstFindIndex = 2;
        boolean isCheckNumber = serviceText.checkNumber(stringForCompare, firstFindIndex);
        Assertions.assertTrue(isCheckNumber);
        firstFindIndex = 3;
        isCheckNumber = serviceText.checkNumber(stringForCompare, firstFindIndex);
        Assertions.assertFalse(isCheckNumber);
    }

    @Test
    void checkEmail() {
        String stringForCompare = "denis@gmail.com +375295297796";
        int firstFindIndex = 5;
        boolean isCheckEmail = serviceText.checkEmail(stringForCompare, firstFindIndex);
        Assertions.assertTrue(isCheckEmail);
        firstFindIndex = 6;
        isCheckEmail = serviceText.checkEmail(stringForCompare, firstFindIndex);
        Assertions.assertFalse(isCheckEmail);
    }

    @Test
    void findNumberPhone() {
        String stringForCompare = "denis@gmail.com +375295297796";
        String stringForFalseCompare = "denis@gmail.com 375295297796";
        Text text = new Text();
        text.setInformation(stringForFalseCompare);
        Text isFindNumberPhone = serviceText.findEmail(stringForFalseCompare, text);
        Assertions.assertEquals(isFindNumberPhone,text);
        isFindNumberPhone = serviceText.findNumberPhone(stringForCompare, text);
        text.setNumberOfPhone("+375295297796");
        Assertions.assertEquals(isFindNumberPhone.getNumberOfPhone(), text.getNumberOfPhone());
    }

    @Test
    void findEmail() {
        String stringForCompare = "denis@gmail.com +375295297796";
        String stringForFalseCompare = "denisgmail.com +375295297796";
        Text text = new Text();
        Text isFindEmail = serviceText.findEmail(stringForFalseCompare, text);
        text.setInformation(stringForFalseCompare);
        Assertions.assertEquals(isFindEmail,text);
        isFindEmail = serviceText.findEmail(stringForCompare, text);
        text.setEmail("s@gmail.com");
        Assertions.assertEquals(isFindEmail.getEmail(), text.getEmail());
    }

    @Test
    void findTextByInformation() {
        String stringForCompare = "denis@gmail.com +375295297796";
        Text textForCheckFirst = new Text();
        textForCheckFirst.setId(1L);
        textForCheckFirst.setInformation(stringForCompare);
        Text textForCheckLast = new Text();
        textForCheckLast.setInformation(stringForCompare);
        textForCheckLast.setId(2L);
        List<Text> ListText = new ArrayList<>();
        ListText.add(textForCheckFirst);
        ListText.add(textForCheckLast);
        Mockito.doReturn(ListText)
                .when(repositoryText)
                .findAll();
        Mockito.doReturn(textForCheckFirst)
                .when(repositoryText)
                .findFirstByInformation(stringForCompare);
        boolean isFindByText = serviceText.findTextByInformation(stringForCompare);
        Assertions.assertFalse(isFindByText);
        Mockito.doReturn(ListText)
                .when(repositoryText)
                .findAll();
        Mockito.doReturn(null)
                .when(repositoryText)
                .findFirstByInformation(stringForCompare);
        isFindByText = serviceText.findTextByInformation(stringForCompare);
        Assertions.assertFalse(isFindByText);
        stringForCompare = "denis@gmail.com +3752952977965";
        textForCheckFirst.setInformation(stringForCompare);
        Mockito.doReturn(ListText)
                .when(repositoryText)
                .findAll();
        Mockito.doReturn(textForCheckFirst)
                .when(repositoryText)
                .findFirstByInformation(stringForCompare);
        isFindByText = serviceText.findTextByInformation(stringForCompare);
        Assertions.assertTrue(isFindByText);
    }

    @Test
    void findAllText() {
        List<Text> texts = new ArrayList<>();
        Text textForCheck = new Text();
        Salary salary = new Salary();
        salary.setId(1L);
        salary.setPrice(1200);
        Author author = new Author();
        author.setId(1L);
        author.setFirstName("denis");
        author.setLastName("shagun");
        author.setSalaries(salary);
        Set<Author> authors = new HashSet<>();
        authors.add(author);
        textForCheck.setAuthors(authors);
        textForCheck.setId(1L);
        textForCheck.setInformation("impotent informations");
        textForCheck.setEmail("denis@gmail.com");
        textForCheck.setNumberOfPhone("+375295297796");
        texts.add(textForCheck);
        Mockito.when(cache.getText("text")).thenReturn(texts);
        Mockito.when(repositoryText.findAll()).thenReturn(texts);
        List<Text> isFindAllText = serviceText.findAllText();
        Assertions.assertEquals(texts, isFindAllText);
        Mockito.when(cache.getText("text")).thenReturn(null);
        Mockito.when(repositoryText.findAll()).thenReturn(texts);
        isFindAllText = serviceText.findAllText();
        Assertions.assertEquals(texts, isFindAllText);
    }

    @Test
    void saveBulkText() {
        BulkTextRequestDTO bulkTextRequestDTO = new BulkTextRequestDTO();
        List<TextDTO> textDTOs = new ArrayList<>();
        TextDTO textDTO = new TextDTO();
        textDTO.setInformation("impotent informations");
        textDTO.setEmail("denis@gmail.com");
        textDTO.setNumberOfPhone("+375295297796");
        Set<Author> authors = new HashSet<>();
        authors.add(new Author());
        textDTO.setAuthors(authors);
        textDTOs.add(textDTO);
        bulkTextRequestDTO.setTexts(textDTOs);
        Mockito.when(repositorySalary.save(Mockito.any())).thenReturn(new Salary());
        Mockito.when(repositoryText.save(Mockito.any())).thenReturn(new Text());
        boolean result = serviceText.saveBulkText(bulkTextRequestDTO);
        Assertions.assertTrue(result);
        result = serviceText.saveBulkText(null);
        Assertions.assertFalse(result);
    }

    @Test
    void findNumberPhoneAndEmail() {
        String stringForCompare = "denis@gmail.com +375295297796";
        Text text = new Text();
        Salary salary = new Salary();
        salary.setId(1L);
        salary.setPrice(1200);
        Author author = new Author();
        author.setId(1L);
        author.setFirstName("denis");
        author.setLastName("shagun");
        author.setSalaries(salary);
        Set<Author> authors = new HashSet<>();
        authors.add(author);
        text.setInformation(stringForCompare);
        text.setAuthors(authors);
        Text isFindNumberPhoneAndEmail = serviceText.findNumberPhoneAndEmail(text);
        Assertions.assertEquals(isFindNumberPhoneAndEmail, text);
    }
}