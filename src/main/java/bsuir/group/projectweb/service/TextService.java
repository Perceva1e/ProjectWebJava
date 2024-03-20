package bsuir.group.projectweb.service;

import bsuir.group.projectweb.model.Text;

import java.util.List;


public interface TextService {
    /**
     * This method find email.
     *
     * @param stringForCompare is string for compare
     * @param information   is entity of text
     * @return text after hard code
     * */
    Text findEmail(String stringForCompare,
                          Text information);
    /**
     * This method find Number Phone.
     *
     * @param stringForCompare is string for compare
     * @param information   is entity of text
     * @return text after hard code
     * */
    Text findNumberPhone(String stringForCompare,
                                Text information);
    /**
     * This method check of email.
     *
     * @param stringForCompare is string for compare
     * @param firstIndex   is index of start
     * @return true is ok, else false
     * */
    Boolean checkEmail(String stringForCompare, int firstIndex);
    /**
     * This method check of Number.
     *
     * @param stringForCompare is string for compare
     * @param firstIndex   is index of start
     * @return true is ok, else false
     * */
    Boolean checkNumber(String stringForCompare,
                               int firstIndex);

    /**
     * This method find index of end.
     *
     * @param stringForCompare is string for compare
     * @param firstFindIndex   is index of start
     * @return index of end
     */
    int endFindIndex(String stringForCompare,
                     int firstFindIndex);

    /**
     * This method check number.
     *
     * @param number is char for compare
     * @return true is ok, false is error
     */
    boolean ifNumber(char number);

    /**
     * This method check ID.
     *
     * @param firstText      is existing for compare
     * @param textForCompare is existing for compare
     * @return true is ok, false is error
     */
    Boolean checkId(Text firstText, Text textForCompare);

    /**
     * This method find All Text.
     *
     * @return true is ok, false is error
     */
    List<Text> findAllText();

    /**
     * This method save Text.
     *
     * @param information is an input parameter
     * @return true is a save, else false
     */
    Boolean saveText(Text information);

    /**
     * This method change By Text.
     *
     * @param informationExist is an existing information
     * @param information      is information for change
     * @return true is ok, false is error
     */
    Boolean changeByText(String informationExist, String information);

    /**
     * This method find by Text.
     *
     * @param information is information for find
     * @return restore true is ok, else false
     */
    Boolean findByText(String information);

    /**
     * This method deleteText.
     *
     * @param id is an id of text for delete
     * @return true is ok, false is error
     */
    Boolean deleteText(Long id);

    /**
     * This method find Number Phone and Email.
     *
     * @param information is a text for hard code
     * @return restore text after hard code
     */
    Text findNumberPhoneAndEmail(Text information);
}
