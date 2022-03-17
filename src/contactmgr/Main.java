package contactmgr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class Main {


//    private static List<String> contactList = new ArrayList<>();
    private static ArrayList<Contact> contactList;
    private static final Input testing = new Input();
    private static final Input menuSelector = new Input();
    private static int queryChoice;
    private static final Path pathToContacts = Paths.get("contacts.txt");



    public static void main(String[] args) {
        initializeContactListVariable();
        writeContactsToFile();
    }///END OF MAIN

    private static void writeContactsToFile() {

        ArrayList <String> contactStrings = new ArrayList<>();

        for (Contact contact: contactList) {
            String contactString = contact.getFirstName() + " | " + contact.getLastName() + " | " + contact.getPhoneNumber() + " | " +
                    contact.getEmail();
            contactStrings.add(contactString);
        }
        try {

            Files.write(pathToContacts,contactStrings);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void initializeContactListVariable(){

        contactList = new ArrayList<>();

        try{

            List<String> stringOfContacts = Files.readAllLines(pathToContacts);

            for (String contact : stringOfContacts) {

                String [] dataParts = contact.split("\\|");
                String firstName = dataParts[0].trim();
                String lastName = dataParts[1].trim();
                String phoneNumber = dataParts[2].trim();
                String emailAddy = dataParts[3].trim();

                Contact contactFromFile  = new Contact(firstName,lastName,phoneNumber,emailAddy);
                contactList.add(contactFromFile);
            }

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
                System.out.println("Printing......");
                printContactList();
                break;
            case 2:
                System.out.println("You're adding a new contact");
                createNewContact();
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
        for (Contact contact:contactList) {
            System.out.printf("|| %-12s |  %-12s |  %12s |  %24s ||\n" ,
                    contact.getFirstName(),contact.getLastName(),contact.getPhoneNumber(),contact.getEmail());
        }
    }


    public static void createNewContact(){
        Contact contactFromBuffer = new Contact();

        System.out.print("Please add First Name: ");
        String nCFN= testing.getString();
        contactFromBuffer.setFirstName(nCFN);

        System.out.print("Please add Last Name: ");
        String nCLN = testing.getString();
        contactFromBuffer.setLastName(nCLN);

        System.out.print("Please enter phone: ");
        String nCPN = testing.getString();
        contactFromBuffer.setPhoneNumber(nCPN);

        System.out.print("Please enter an email: ");
        String nCEMl = testing.getString();
        contactFromBuffer.setEmail(nCEMl);


        setNewContactToContactList(contactFromBuffer);
    }


    public static void setNewContactToContactList(Contact newContact){

        contactList.add(newContact);
        System.out.println("Contact Added.....");

        printContactList();
    }

    public static void queryContactsByName(){
        System.out.print("Enter a Name to search for: ");
        String searchQuery = testing.getString();
        for (Contact contact: contactList) {
            if(contact.getFullName().matches("(?i).*" + searchQuery + ".*")){
                System.out.println(contact);
            }
        }
    }


    public static void deleteExistingContact(){
        boolean carryOn = testing.yesNo("YOU ARE DELETING A CONTACT! CONTINUE: (Y/N) ");
        if(!carryOn){
            System.out.println("Exiting......");
        }else{
            System.out.println("Enter Contact to delete");
            String contactToDelete = testing.getString();
            carryOn = testing.yesNo("WOULD YOU LIKE TO DELETE CONTACT ABOVE?: (Y/N) ");

            if(!carryOn){
                System.out.println("Exiting......");
            }else{
                System.out.println("DELETING CONTACT.....");
                contactList.removeIf(contact -> contact.getFirstName().equalsIgnoreCase(contactToDelete));
            }
        }
    }







}/// END OF CLASS
