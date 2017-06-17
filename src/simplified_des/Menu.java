/**
 * @author vamartid
 * vamartid
 * Vasileios Martidis
 * A.E.M.: 2372
 */

package simplified_des;

import java.util.Scanner;

public class Menu {

    private Scanner input = new Scanner(System.in);
    
    private void exit(){
        System.out.println("Exiting...");
        System.exit(0); 
    }
    
    private void encrypt(){
        //create a DES_operator object in order
        //we can use the functions on the key
        //(which use the private functions of DES_operator class).
        DES_operator des=new DES_operator();
        
        System.out.println(">Ender an input key(10 bits)");
        //read input key String
        Scanner scan = new Scanner(System.in);
        String key;
        do{
            System.out.print("Input Key is : ");
            key = scan.next();
        }while(!des.checkInput(key,10));
        
        
        //in our example
        //String key ="1010000010";
        
        System.out.println("----------------------------------");
        System.out.println("         Generating keys");
        System.out.println("----------------------------------");
        //Generate the two keys key1=keys[0] and key2=keys[1]
        //process is same for decypt
        String[] keys=des.keyGen(key);
        //Enter the plaintext to convert it on cyphertext
        //input
        System.out.println("----------------------------------");
        System.out.println(">Enter an input plaintext(8 bits)");
        System.out.println("----------------------------------");
        String plaintext;
        do{
            System.out.print("Input Plaintext is : ");
            plaintext = scan.next();
        }while(!des.checkInput(plaintext,8));
        
        //in our example
        //String plaintext = "01110010";
        //cypher should be 01110111
        System.out.println("----------------------------------");
        System.out.println("         Start encryption");
        System.out.println("----------------------------------");
        //encryption
        String cyphertext=des.encrypt(keys,plaintext);
        //print encryption output
        System.out.println("----------------------------------");
        System.out.print("         Cyphertext is : ");
        System.out.print(cyphertext+"\n");
    }
    private void decrypt(){
        //create a DES_operator object in order
        //we can use the functions on the key
        //(which use the private functions of DES_operator class).
        DES_operator des=new DES_operator();
        
        System.out.println(">Ender an input key(10 bits)");
        //read input key String
        Scanner scan = new Scanner(System.in);
        String key;
        do{
            System.out.print("Input Key is : ");
            key = scan.next();
        }while(!des.checkInput(key,10));
        
        //in our example
        //String key ="1010000010";
        //
        System.out.println("----------------------------------");
        System.out.println("          Generating keys");
        System.out.println("----------------------------------");
        //Generate the two keys key1=keys[0] and key2=keys[1]
        //process is same for decypt
        String[] keys=des.keyGen(key);
        //Enter the plaintext to convert it on cyphertext
        //input
        System.out.println("----------------------------------");
        System.out.println(">Enter an input Cyphertext(8 bits)");
        System.out.println("----------------------------------");
        String cyphertext;
        do{
            System.out.print("Input cyphertext is : ");
            cyphertext = scan.next();
        }while(!des.checkInput(cyphertext,8));
        
        System.out.println("----------------------------------");
        System.out.println("         Start decryption");
        System.out.println("----------------------------------");
        //decryption
        String input=des.decrypt(keys,cyphertext);
        //print decryption output
        System.out.println("----------------------------------");
        System.out.print("         Input was : ");
        System.out.print(input+"\n");
    }
    public void display() {
        //keep start time
        final long startTime = System.nanoTime();
        
        System.out.println("----------------------------------");
        System.out.println("          Simplified DES");
        System.out.println("----------------------------------");
        System.out.println(">Actions");
        System.out.print(
                "Select an option: \n"
                + "  1) Encrypt\n"
                + "  2) Decrypt\n"
                + "  3) Exit\n"+ "Input command is : "
        );

        Scanner scan = new Scanner(System.in);
        String selection = scan.next();

        switch (selection) {
            case "1":
                this.encrypt();
                break;
            case "2":
                this.decrypt();
                break;
            case "3":
                final long endTime = System.nanoTime();
                System.out.println("Total execution time: " + (endTime - startTime) +" nanoseconds (ns).");
                this.exit();
                break;
            default:
                System.out.println("Invalid selection.");
                break;
        }

    }
}
