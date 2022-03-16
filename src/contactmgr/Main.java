package contactmgr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class Main {


    private static List<String> contactList = new ArrayList<>();
    private static final Input testing = new Input();
    private static final Input menuSelector = new Input();
    private static int queryChoice;
    private static final Path pathToContacts = Paths.get("contacts.txt");



    public static void main(String[] args) {
        initializeContactListVariable();
    }///END OF MAIN


    public static void initializeContactListVariable(){
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
        System.out.println("Name   |   Phone Number");
        for (String contact:contactList) {
            System.out.println(contact);
        }
    }


    public static void createNewContact(){
        Contact contactAddedByBuffer = new Contact();

        System.out.print("Please add First Name: ");
        String nCFN= testing.getString();
        contactAddedByBuffer.setFirstName(nCFN);

        System.out.print("Please add Last Name: ");
        String nCLN = testing.getString();
        contactAddedByBuffer.setLastName(nCLN);

        System.out.print("Please enter phone: ");
        String nCPN = testing.getString();
        contactAddedByBuffer.setPhoneNumber(nCPN);

        System.out.print("Please enter an email: ");
        String nCEMl = testing.getString();
        contactAddedByBuffer.setEmail(nCEMl);

        String demoContact = nCFN + " | " + nCLN + " | " + nCPN + " | " +  nCEMl + "  |";;
        setNewContactToContactList(demoContact);


         //// call setFirstName, setLastName, set phoneNumber
         //// then append list with new object
    }


    public static void setNewContactToContactList(String newContact){

        contactList.add(contactList.size(), newContact);
        System.out.println("new contact added to list");

       updateContactsFile();
    }


    public static void queryContactsByName(){
        System.out.print("Enter a Name to search for: ");
        String searchQuery = testing.getString();
        for (String contact: contactList) {
            if(contact.contains(searchQuery)){
                System.out.println(contact);
            }
        }
        /// allow query by first or last name
        /// loop through both in search function
        /// contactList(i)[0] and contactList(i)[1]

    }


    public static void deleteExistingContact(){

        boolean carryOn = testing.yesNo("YOU ARE DELETING A CONTACT! CONTINUE: (Y/N) ");
        if(!carryOn){
            System.out.println("Exiting......");
        }else{
            queryContactsByName();
            String contactToDelete = testing.getString();
            carryOn = testing.yesNo("WOULD YOU LIKE TO DELETE CONTACT ABOVE?: (Y/N) ");

            if(!carryOn){
                System.out.println("Exiting......");
            }else{
                System.out.println("DELETING CONTACT.....");
                contactList.remove(contactToDelete);
            }
        }



        //// prompt warning before allowing delete
        //// add yesNo confirmation before delete
        //// manipulate list to remove selected name
        //// add yesNo confirmation for delete
    }

    public static void updateContactsFile (){
        try {
            Files.write(pathToContacts,contactList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}/// END OF CLASS
