package contactmgr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class Main {
    private static List<String> contactList;
    private final Input testing = new Input();
    private static final Input menuSelector = new Input();
    private static int queryChoice;


    public static void main(String[] args) {


//        System.out.println();
//        testing.yesNo("would you like to continue ");
//        testing.getInt();
//        testing.getString();

initializeContactListVariable();

    }///END OF MAIN

    public static void initializeContactListVariable(){
        Path pathToContacts = Paths.get("contacts.txt");
        try{
            contactList = Files.readAllLines(pathToContacts);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        populateBuffer();
    }

    public static void populateBuffer(){
        System.out.print("1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5):  ");

        queryChoice = menuSelector.getInt();
        queryContactsByMenuSelection(queryChoice);

    }

    public static void queryContactsByMenuSelection(int queryChoice){
        switch (Main.queryChoice){
            case 1:
                System.out.println("print all contacts");
                printContactList();
                break;
            case 2:
                System.out.println("add contact");
                addContactToDataBase();
                break;
            case 3:
                System.out.println("search by name");
                queryContactsByName();
                break;
            case 4:
                System.out.println("delete existing contact");
                deleteExistingContact();
                break;
            case 5:
                System.out.println("Have a great day");
                break;
            default:
                System.out.println("not sure what to put here");
        }
    }

    public static void printContactList(){
        System.out.println("Name   |   Phone Number");
        for (String contact:contactList) {
            System.out.println(contact);
        }
    }

    public static void addContactToDataBase(){
        System.out.println("add contact to contact list");
    }

    public static void queryContactsByName(){
        System.out.println("query cl by name");
    }


    public static void deleteExistingContact(){
        System.out.println("delete existing contact");
    }





}/// END OF CLASS
