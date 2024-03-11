package bsuir.group.projectweb.controller;

import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.service.TextService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static bsuir.group.projectweb.controller.TextController.loggerMain;

@RestController
@RequestMapping("/api/v1/text")
@AllArgsConstructor
public class AuthorController {
    private TextService service;
    @GetMapping("/find_text_lastname/{lastName}/{nameList}")
    public List<Author> findAuthorByParameters(@PathVariable final String lastName,
                                               @PathVariable final List<String> nameList) {
        loggerMain.info("start find author by lastname and name");
        return service.findAuthorByParameters(lastName, nameList);
    }
    @PostMapping("/save_people")
    public Author saveAuthor(@RequestBody final Author author) {
        loggerMain.info("start save a author");
        return  service.savePerson(author);
    }
    @PutMapping("add_author_text/{informationExist}")
    public ResponseEntity<String> addAuthorInText(@PathVariable final String informationExist, @RequestBody Author authorAdd) {
        loggerMain.info("start add author in text");
        boolean checkError =service.addAuthorInText(informationExist,authorAdd);
        if (checkError) {
            return new ResponseEntity<>("add author in text", HttpStatus.OK); }
        else {
            loggerMain.error("text not found");
            return new ResponseEntity<>("add author in text not complete", HttpStatus.NOT_FOUND); }
    }
    @DeleteMapping("delete_author_connection/{idAuthor}/{idText}")
    public ResponseEntity<String> deleteAuthorConnection(@PathVariable final Long idAuthor,@PathVariable final Long idText) {
        loggerMain.info("start delete author");
        if (service.deleteAuthorConnection(idAuthor,idText)) {
            return new ResponseEntity<>("Delete author", HttpStatus.OK); }
        else {
            loggerMain.error("author not found by id");
            return new ResponseEntity<>("author not found", HttpStatus.NOT_FOUND); }
    }
}
