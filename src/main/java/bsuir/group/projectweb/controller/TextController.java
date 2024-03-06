package bsuir.group.projectweb.controller;

import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Salary;
import bsuir.group.projectweb.model.Text;
import bsuir.group.projectweb.service.TextService;
import lombok.AllArgsConstructor;
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
    public String saveAuthor(@RequestBody Author author)
    {
        service.savePerson(author);
        return "information is a save";
    }
    @PostMapping("/save_salary")
    public String saveSalary(@RequestBody Salary salary)
    {
        service.saveSalary(salary);
        return "information is a save";
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
    public void deleteText(@PathVariable Long id)
    {
        service.deleteText(id);
    }
}
