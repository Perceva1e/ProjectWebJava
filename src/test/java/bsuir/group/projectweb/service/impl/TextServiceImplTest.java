package bsuir.group.projectweb.service.impl;

import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Salary;
import bsuir.group.projectweb.model.Text;
import bsuir.group.projectweb.repository.AuthorRepositoryDAO;
import bsuir.group.projectweb.repository.TextRepositoryDAO;
import bsuir.group.projectweb.service.TextService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RunWith(SpringRunner.class)
@SpringBootTest
class TextServiceImplTest {
    @Autowired
    private TextService serviceText;
    @MockBean
    private TextRepositoryDAO repositoryText;
    @MockBean
    private AuthorRepositoryDAO repositoryAuthor;


    @Test
    void checkId() {
        Text textFirstCheck = new Text();
        textFirstCheck.setId(1L);
        Text textLastCheck = new Text();
        textLastCheck.setId(1L);
        boolean isCheckId = serviceText.checkId(textFirstCheck, textLastCheck);
        Assert.assertFalse(isCheckId);
        textLastCheck.setId(2L);
        isCheckId = serviceText.checkId(textFirstCheck, textLastCheck);
        Assert.assertTrue(isCheckId);
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
        Assert.assertTrue(isTextSave);
        textForCheck.setId(1L);
        isTextSave = serviceText.saveText(textForCheck);
        Assert.assertFalse(isTextSave);
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
        Assert.assertTrue(isChangeByText);
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
        Assert.assertTrue(isDeleteText);
        Mockito.doReturn(false)
                .when(repositoryAuthor)
                .existsById(author.getId());
        isDeleteText = serviceText.deleteAuthorInText(author.getId());
        Assert.assertFalse(isDeleteText);
    }

    @Test
    void ifNumber() {
        char number = '4';
        boolean isIfNumber = serviceText.ifNumber(number);
        Assert.assertTrue(isIfNumber);
        char letters = 'a';
        isIfNumber = serviceText.ifNumber(letters);
        Assert.assertFalse(isIfNumber);
    }

    @Test
    void endFindIndex() {
        String stringForCompare = "da+375295297796net";
        int firstFindIndex = 2;
        int isEndFindIndex = serviceText.endFindIndex(stringForCompare, firstFindIndex);
        Assert.assertEquals(14, isEndFindIndex);
        Assert.assertNotEquals(15, isEndFindIndex);
    }

    @Test
    void checkNumber() {
        String stringForCompare = "da+375295297796net";
        int firstFindIndex = 2;
        boolean isCheckNumber = serviceText.checkNumber(stringForCompare, firstFindIndex);
        Assert.assertTrue(isCheckNumber);
        firstFindIndex = 3;
        isCheckNumber = serviceText.checkNumber(stringForCompare, firstFindIndex);
        Assert.assertFalse(isCheckNumber);
    }

    @Test
    void checkEmail() {
        String stringForCompare = "denis@gmail.com +375295297796";
        int firstFindIndex = 5;
        boolean isCheckEmail = serviceText.checkEmail(stringForCompare, firstFindIndex);
        Assert.assertTrue(isCheckEmail);
        firstFindIndex = 6;
        isCheckEmail = serviceText.checkEmail(stringForCompare, firstFindIndex);
        Assert.assertFalse(isCheckEmail);
    }

    @Test
    void findNumberPhone() {
        String stringForCompare = "denis@gmail.com +375295297796";
        Text text = new Text();
        text.setInformation(stringForCompare);
        Text isFindNumberPhone = serviceText.findNumberPhone(stringForCompare, text);
        text.setNumberOfPhone("+375295297796");
        Assert.assertEquals(isFindNumberPhone.getNumberOfPhone(), text.getNumberOfPhone());
    }

    @Test
    void findEmail() {
        String stringForCompare = "denis@gmail.com +375295297796";
        Text text = new Text();
        text.setInformation(stringForCompare);
        Text isFindEmail = serviceText.findEmail(stringForCompare, text);
        text.setEmail("s@gmail.com");
        Assert.assertEquals(isFindEmail.getEmail(), text.getEmail());
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
        Assert.assertFalse(isFindByText);
        stringForCompare = "denis@gmail.com +3752952977965";
        textForCheckFirst.setInformation(stringForCompare);
        Mockito.doReturn(ListText)
                .when(repositoryText)
                .findAll();
        Mockito.doReturn(textForCheckFirst)
                .when(repositoryText)
                .findFirstByInformation(stringForCompare);
        isFindByText = serviceText.findTextByInformation(stringForCompare);
        Assert.assertTrue(isFindByText);
    }
}