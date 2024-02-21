package bsuir.group.projectweb.service;

import bsuir.group.projectweb.model.Text;
import java.util.List;


public interface TextService {

    public List<Text> findAllText() ;
    public Text saveText(Text information) ;
    public Text findByText(String information) ;
    public Text updateText(Text information);
    public void deleteText(String information);
    public Text findNumberPhoneAndEmail(Text information);
}
