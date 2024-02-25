package bsuir.group.projectweb.service.Impl;

import bsuir.group.projectweb.model.Text;
import bsuir.group.projectweb.repository.InMemoryDAO;
import bsuir.group.projectweb.repository.TextRepository;
import bsuir.group.projectweb.service.TextService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@AllArgsConstructor
@Primary
public class TextServiceImpl implements TextService {
    private final TextRepository repository;
    private final InMemoryDAO repositoryDao;
    @Override
    public List<Text> findAllText() {
        return repository.findAll();
    }

    @Override
    public Text saveText(Text information) {
        return repository.save(information);
    }

    @Override
    public Text findByText(String information) {
        return repository.findByInformation(information);
    }
    @Override
    @Transactional
    public void deleteText(Long id) {
       if(repository.existsById(id)) {
           repository.deleteById(id);
       }
    }
    @Override
    public Text findNumberPhoneAndEmail(Text information) {
        repositoryDao.findNumberPhoneAndEmail(information);
        return repository.save(information);
    }
}
