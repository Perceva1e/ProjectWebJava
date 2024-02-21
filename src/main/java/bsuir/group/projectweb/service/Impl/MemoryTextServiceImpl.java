package bsuir.group.projectweb.service.Impl;

import bsuir.group.projectweb.model.Text;
import bsuir.group.projectweb.repository.InMemoryDAO;
import bsuir.group.projectweb.service.TextService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
@Primary
public class MemoryTextServiceImpl implements TextService {
    private final InMemoryDAO repository;
    @Override
    public List<Text> findAllText() {
        return repository.findAllText();
    }
    @Override
    public Text saveText(Text information)
    {
        return repository.saveText(information);
    }
    @Override
    public Text findByText(String information)
    {
        return repository.findByText(information);
    }
    @Override
    public Text updateText(Text information)
    {
     return repository.updateText(information);
    }
    @Override
    public void deleteText(String information)
    {
        repository.deleteText(information);
    }
    @Override
    public Text findNumberPhoneAndEmail(Text information)
    {
        return repository.findNumberPhoneAndEmail(information);
    }
}
