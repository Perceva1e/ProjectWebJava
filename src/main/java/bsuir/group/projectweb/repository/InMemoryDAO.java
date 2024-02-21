package bsuir.group.projectweb.repository;

import bsuir.group.projectweb.model.Text;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
@Repository
public class InMemoryDAO {

    private final List<Text> TEXT = new ArrayList<>();
    public boolean ifNumber(char number)
    {
        return (number >= '0') && (number <= '9');
    }
    public int endFindIndex(@NotNull String stringForCompare, int firstFindIndex)
    {
        char[] charArray;
        int i;
        charArray=stringForCompare.toCharArray();
        for( i = firstFindIndex+1; i< charArray.length; i++) {
            if (!ifNumber(charArray[i])) {
                return i;
            }
        }
        return i;
    }
    public boolean CheckNumber(String stringForCompare,int firstIndex)
    {
        int countForArray=0;
        char[] charArray;
        charArray=stringForCompare.toCharArray();
        for(int i=firstIndex+1;i <charArray.length;i++)
        {
            if (ifNumber(charArray[i])) {
                countForArray++;
            }
            if(!ifNumber(charArray[i]) && countForArray<12 ){return false;}
            if(countForArray==12){return true;}
        }
        return false;
    }
    public boolean CheckEmail(String stringForCompare,int firstIndex)
    {
        int countForArray=0;
        char[] charArray;
        String domainEmail ="gmail.com";
        char[] charArrayDomain = domainEmail.toCharArray();
        charArray=stringForCompare.toCharArray();
        for(int i=firstIndex+1;i < charArray.length;i++)
        {
            if (charArray[i]==charArrayDomain[countForArray]) {
                countForArray++;
            }
            else{return false;}
            if(countForArray == 9) {return true;}
        }
        return countForArray == 9;
    }
    public Text findNumberPhone(String stringForCompare,Text information)
    {
        char[] charArray;
        charArray = stringForCompare.toCharArray();
        int countForArray=0;
        boolean checkForPlus=false;
        int firstIndex=0;
        while(!checkForPlus) {
            firstIndex = stringForCompare.indexOf('+',firstIndex+1);
            checkForPlus=CheckNumber(stringForCompare,firstIndex);
            if(firstIndex==-1){return information;}
        }
        int endIndex = endFindIndex(stringForCompare, firstIndex);
        char[] charArrayPhoneNumber = new char[endIndex-firstIndex];
        for (int j = firstIndex; j < endIndex; j++) {
            charArrayPhoneNumber[countForArray]= charArray[j];
            charArray[j] = '*';
            countForArray++;
        }
        String stringForUpdate=new String(charArray);
        String stringForMemory=new String(charArrayPhoneNumber);
        information.setInformation(stringForUpdate);
        information.setNumberOfPhone(stringForMemory);
        return information;
    }
    public Text findEmail(String stringForCompare,Text information)
    {
        char[] charArray;
        charArray = stringForCompare.toCharArray();
        int countForArray=0;
        boolean checkForAt=false;
        int firstIndexAt = 0;
        while(!checkForAt) {
            firstIndexAt = stringForCompare.indexOf('@',firstIndexAt+1);
            checkForAt=CheckEmail(stringForCompare,firstIndexAt);
            if(firstIndexAt==-1) {return information;}
        }
        int endIndexAt = firstIndexAt+9;
        char[] charArrayEmail = new char[endIndexAt-firstIndexAt+1];
        for (int j = firstIndexAt; j <= endIndexAt; j++) {
            charArrayEmail[countForArray]= charArray[j];
            charArray[j] = '*';
            countForArray++;
        }
        String stringForUpdate=new String(charArray);
        String stringForMemory=new String(charArrayEmail);
        information.setInformation(stringForUpdate);
        information.setEmail(stringForMemory);
        return information;
    }
    public Text findNumberPhoneAndEmail(Text information)
    {
        String stringForCompare;
        stringForCompare = information.getInformation();
        information=findNumberPhone(stringForCompare,information);
        stringForCompare = information.getInformation();
        information=findEmail(stringForCompare,information);
            TEXT.add(information);
        return information;
    }
    public List<Text> findAllText() {
        {
            return TEXT;
        }
    }
    public Text saveText(Text information) {
        TEXT.add(information);
        return information;
    }
    public Text findByText(String information) {
        return TEXT.stream()
                .filter(element->element.getInformation().equals(information))
                .findFirst()
                .orElse(null);
    }
    public Text updateText(Text information) {
        var textIndex = IntStream.range(0,TEXT.size())
                .filter(index-> TEXT.get(index).getInformation().equals(information.getInformation()))
                .findFirst()
                .orElse(-1);
        if(textIndex > -1)
        {
            TEXT.set(textIndex,information);
            return information;
        }
        return null;
    }
    public void deleteText(String information) {
        var Text = findByText(information);
        if(Text != null) {
            TEXT.remove(Text);
        }
    }

}
