package bsuir.group.projectweb.dto;


import java.util.List;

public class BulkTextRequestDTO {
    /**
     * This List of text.
     *
     */
    private List<TextDTO> text;
    /**
     * This method get List of Text.
     *
     * @return restore List of Text
     */

    public List<TextDTO> getTexts() {
        return text;
    }
    /**
     * This method set text.
     *
     * @param texts is a List of entity Text
     */

    public void setTexts(final List<TextDTO> texts) {
        this.text = texts;
    }
}
