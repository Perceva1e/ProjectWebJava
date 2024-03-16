package bsuir.group.projectweb.controller;

import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.service.AuthorService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static bsuir.group.projectweb.controller.TextController.LOGGER;

@RestController
@RequestMapping("/api/v1/text")
@AllArgsConstructor
public class AuthorController {

    /**
     * This is a server.
     */
    private AuthorService service;

    /**
     * This method find author by parameters.
     *
     * @param lastName is a string for find
     * @param nameList is a list of string for find
     * @return restore list of author after find
     */
    @GetMapping("/find_text_lastname/{lastName}/{nameList}")
    public List<Author> findAuthorByParameters(
            @PathVariable final String lastName,
            @PathVariable final List<String> nameList) {
        LOGGER.info("start find author by lastname and name");
        return service.findAuthorByParameters(lastName, nameList);
    }

    /**
     * This method save an author.
     *
     * @param author is an entity for save
     * @return restore author after save
     */
    @PostMapping("/save_people")
    public Author saveAuthor(@RequestBody final Author author) {
        LOGGER.info("start save a author");
        return service.savePerson(author);
    }

    /**
     * This method add author in text.
     *
     * @param informationExist is a string extisting
     * @param authorAdd        is an entity for add
     * @return restore http status
     */
    @PutMapping("add_author_text/{informationExist}")
    public ResponseEntity<String> addAuthorInText(
            @PathVariable final String informationExist,
            @RequestBody final Author authorAdd) {
        LOGGER.info("start add author in text");
        boolean checkError = service.addAuthorInText(
                informationExist, authorAdd);
        if (checkError) {
            return new ResponseEntity<>("add author in text", HttpStatus.OK);
        } else {
            LOGGER.error("text not found");
            return new ResponseEntity<>(
                    "add author in text not complete", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method delete connection author.
     *
     * @param idAuthor is an id of author for delete connection
     * @param idText   is an id of text from delete connection
     * @return restore http status
     */
    @DeleteMapping("delete_author_connection/{idAuthor}/{idText}")
    public ResponseEntity<String> deleteAuthorConnection(
            @PathVariable final Long idAuthor,
            @PathVariable final Long idText) {
        LOGGER.info("start delete author");
        if (service.deleteAuthorConnection(idAuthor, idText)) {
            return new ResponseEntity<>("Delete author", HttpStatus.OK);
        } else {
            LOGGER.error("author not found by id");
            return new ResponseEntity<>(
                    "author not found", HttpStatus.NOT_FOUND);
        }
    }
}
