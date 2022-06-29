import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;


public class Contacts_Manager {
    public static String filename = "contacts.txt";

    public static void renderAll() {
        try {
            Path datafile = Paths.get(filename);
            if (Files.notExists(datafile)) {
                Files.createFile(datafile);
            }

            List<String> files = Files.readAllLines(datafile);
            for (String file : files) {
                System.out.println(file);
            }

        } catch (IOException iox) {
            iox.printStackTrace();
        }
    }

    public static void addContact() {
        try {
            Path dataFile = Paths.get(filename);
            Scanner sc = new Scanner(System.in);
            sc.useDelimiter("\n");
            System.out.println("Add a contact name");
            String name = sc.next();
            System.out.println("Add a contact number");
            String number = sc.next();
            Contacts newContact = new Contacts(name, number);
            String contact = "Name: " + newContact.getName() + " | Number: " + newContact.getPhoneNumber();

            Files.write(dataFile, Arrays.asList(contact), StandardOpenOption.APPEND);
            renderAll();
        } catch (IOException iox) {
            iox.printStackTrace();
        }

    }

    public static void search() {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.println("Search a contact by name");
        String input = sc.next();
        try {
            Path data = Paths.get(filename);
            List<String> fileNames = Files.readAllLines(data);
            for (String filename : fileNames) {
                if (filename.toLowerCase().contains(input)) {
                    System.out.println(filename);
                }
            }
        } catch (IOException iox) {
            iox.printStackTrace();
        }


    }
    public static void delete(){
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.println("Type a contact name to delete");
        String input = sc.next();
        try {
            renderAll();
            Path data = Paths.get(filename);
            List<String> fileNames = Files.readAllLines(data);
            for (String filename : fileNames) {
                if (filename.toLowerCase().contains(input)) {

                }
            }
        } catch (IOException iox) {
            iox.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Path dataFile = Paths.get(filename);
        List<Contacts> contacts = new ArrayList<>();
        contacts.add(new Contacts("Miguel", "9789831339"));
        contacts.add(new Contacts("Isiah", "9788354917"));
        contacts.add(new Contacts("Rin", "5106557707"));
        contacts.add(new Contacts("Senju", "8005882300"));
        List<String> contactStrings = new ArrayList<>();
        for (Contacts contact : contacts) {
            contactStrings.add("Name: " + contact.getName() + " | Number: " + contact.getPhoneNumber());
        }
        try {
            Files.write(dataFile, contactStrings);
        } catch (IOException ixo) {
            ixo.printStackTrace();
        }

        renderAll();
        addContact();
        search();


    }
}
