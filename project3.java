import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.sound.midi.SysexMessage;

public class project3 {
  int j;
  String validWord;
  static project3 biggestvalidword(HashMap<String, String> wordlist,String encrypt,int biggestword,project3 customvariable,int i,int j) 
  {
    if (j >= encrypt.length()) 
        return customvariable;
    j++;
    String selected = encrypt.substring(i, j);
    String respond = wordlist.get(selected);
    if (respond != null) {
      customvariable.j = j;
      customvariable.validWord = selected;
    } 
    else if (j == biggestword) return customvariable;
      return biggestvalidword(wordlist,encrypt,biggestword,customvariable,i,j);
  }
  static void valid(HashMap<String, String> wordlist,String encrypt,int biggestword,int i,int j) 
  {
    if (j >= encrypt.length()) 
      return;
    String selected = encrypt.substring(i, j);
    String respond = wordlist.get(selected);
    if (respond != null) 
    {
      project3 customvariable = new project3();
      customvariable.validWord = selected;
      customvariable.j = j;
      project3 temp = biggestvalidword(wordlist,encrypt,biggestword,customvariable,i,j);
      System.out.print(temp.validWord + " ");
      i = temp.j;
      j = 1 + i;
    } else j++;
    valid(wordlist, encrypt, biggestword, i, j);
  }
  public static void main(String[] args) {
    int biggestword = 0;
    HashMap<String, String> wordlist = new HashMap<String, String>();
    try {
      File myObj = new File("words_alpha.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        if (data.length() > biggestword) biggestword = data.length();
        wordlist.put(data, data);
      }
      myReader.close();
    } 
    catch (FileNotFoundException e) {
      System.out.println("An error occurred: FileNotFoundException");
      e.printStackTrace();
    }
    System.out.println("enter encrypted message:");
    Scanner input = new Scanner(System.in);
    String encrypt = input.nextLine();
    encrypt = encrypt.toLowerCase();
    valid(wordlist, encrypt, biggestword, 0, 1);
  }
}
