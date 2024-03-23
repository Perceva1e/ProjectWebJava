package bsuir.group.projectweb.dto;

import bsuir.group.projectweb.model.Text;

import java.util.List;

public class BulkTextRequestDTO {
    private List<TextDTO> text;

    public List<TextDTO> getTexts() {
        return text;
    }

    public void setTexts(List<TextDTO> texts) {
        this.text = texts;
    }
}
