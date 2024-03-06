package bsuir.group.projectweb.controller;

import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Salary;
import bsuir.group.projectweb.model.Text;
import bsuir.group.projectweb.service.TextService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/text")
@AllArgsConstructor
public class  TextController {
    private TextService service;
    @GetMapping
    public List<Text> findAllText()
    {
        return service.findAllText();
    }
    @PostMapping("/save_people")
    public Author saveAuthor(@RequestBody Author author)
    {
        return  service.savePerson(author);
    }
    @PostMapping("/save_salary")
    public Salary saveSalary(@RequestBody Salary salary)
    {
        return service.saveSalary(salary);
    }
    @PostMapping("/save_information")
    public Text saveText(@RequestBody Text information )
    {
        return service.saveText(information);
    }
    @PostMapping("/find_phone_number_email")
    public Text findNumberPhoneAndEmail(@RequestBody Text information)
    {
        return service.findNumberPhoneAndEmail(information);
    }
    @PutMapping("change")
    public Text changeText(@RequestBody Text information)
    {
        return service.changeText(information);
    }
    @GetMapping("/{text}")
    public Text findByText(@PathVariable String text)
    {
        return service.findByText(text);
    }
    @DeleteMapping("delete_text/{id}")
    public ResponseEntity<String> deleteText(@PathVariable Long id)
    {
        if(service.deleteText(id))
        {
          return new ResponseEntity<>("Delete information", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("information not found", HttpStatus.NOT_FOUND);
        }
    }
}
