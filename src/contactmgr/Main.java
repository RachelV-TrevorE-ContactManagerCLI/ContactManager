package contactmgr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class Main {
    private static List<String> contactList;

    public static void main(String[] args) {
        Input testing = new Input();

//        System.out.println();
//        testing.yesNo("would you like to continue ");
//        testing.getInt();
//        testing.getString();

        Path pathtoContacts = Paths.get("contacts.txt");
        System.out.println("does it exist " + Files.exists(pathtoContacts));
        try{
          contactList = Files.readAllLines(pathtoContacts);
            System.out.println(contactList);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

printContactList();
    }///END OF MAIN

    public static void printContactList(){
        System.out.println("Name   |   Phone Number");
        for (String contact:contactList) {
            System.out.println(contact);
        }
    }






}/// END OF CLASS
