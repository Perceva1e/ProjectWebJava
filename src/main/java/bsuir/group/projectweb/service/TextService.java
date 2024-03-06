package bsuir.group.projectweb.service;

import bsuir.group.projectweb.model.Author;
import bsuir.group.projectweb.model.Salary;
import bsuir.group.projectweb.model.Text;
import java.util.List;


public interface TextService {

    public List<Text> findAllText() ;
    public Text saveText(Text information) ;
    public Author savePerson(Author author);
    public Text changeText(Text information);
    public Text findByText(String information) ;
    public void deleteText(Long id);
    public Text findNumberPhoneAndEmail(Text information);

    public Salary saveSalary(Salary salary);
}
