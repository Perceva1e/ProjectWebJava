package bsuir.group.projectweb.controller;

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
    @PostMapping("/save_information")
    public String saveText(@RequestBody Text information )
    {
        service.saveText(information);
        return "information is a save";
    }
    @PostMapping("/find_phone_number_email")
    public String findNumberPhoneAndEmail(@RequestBody Text information)
    {
        service.findNumberPhoneAndEmail(information);
        return "information is a save";
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
