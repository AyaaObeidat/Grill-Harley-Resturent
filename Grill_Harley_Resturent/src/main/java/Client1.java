
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client1 {
public static void burgerOrder(Socket client1,DataOutputStream out,DataInputStream input)
{
    try {
            Scanner n = new Scanner(System.in);
            System.out.println(input.readUTF());
            String typeOfBurger = n.nextLine();
            out.writeUTF(typeOfBurger);
            //===========================
            String favorType, burgerSize , ServerMes; 
            int quantity;
            boolean flag = true;
            while(flag)
             {   
                 ServerMes = input.readUTF();
                 if(!ServerMes.equalsIgnoreCase("Worng input .. try again"))
                 {
                     System.out.println(ServerMes);
                     favorType = n.nextLine();
                     out.writeUTF(favorType);
                     boolean f=true;
                     while(f){
                     if(favorType.equalsIgnoreCase("Happy Meal")) {System.out.println(input.readUTF());f=false;}
                     else if(favorType.equalsIgnoreCase("Grill Harley")||favorType.equalsIgnoreCase("White Mushroom")||favorType.equalsIgnoreCase("Maxican"))
                     {
                         System.out.println(input.readUTF());
                         burgerSize = n.nextLine();
                         out.writeUTF(burgerSize);
                         f=false;
                     }
                     else
                     {
                         System.out.println(input.readUTF());
                         favorType = n.nextLine();
                         out.writeUTF(favorType);
                     }}
                     System.out.println(input.readUTF());
                     quantity = n.nextInt();
                     out.writeInt(quantity);
                     System.out.println(input.readUTF()+ input.readDouble()+"JD");
                     flag = false;
                 }
                 else
                 {
                      System.out.println(ServerMes);
                      typeOfBurger = n.nextLine();
                      out.writeUTF(typeOfBurger);
                 }
             }} catch (IOException ex) {
        Logger.getLogger(Client1.class.getName()).log(Level.SEVERE, null, ex); }
}
//========================================================================
    public static void main(String[] args) {
        try {
            Socket client1 = new Socket("localhost",1515);
            DataOutputStream out = new DataOutputStream(client1.getOutputStream());
            DataInputStream input = new DataInputStream(client1.getInputStream());
            Scanner n = new Scanner(System.in);
            System.out.println(input.readUTF());
            System.out.println("========================================");
            out.writeUTF("Hello server! Please send me the burger Menu "); 
            System.out.println(input.readUTF());
            System.out.println("========================================");
            //===========================================
            //===========================================
            burgerOrder(client1,out,input);
            System.out.println(input.readUTF());
            String otherOrder = n.nextLine();
            out.writeUTF(otherOrder);
            String msg  ;
            boolean flag = true;
            while(flag)
            {
                 msg = input.readUTF();
                 if(msg.equalsIgnoreCase("repeated"))  
                 { 
                     burgerOrder(client1,out,input);
                     System.out.println(input.readUTF());
                     otherOrder = n.nextLine();
                     out.writeUTF(otherOrder);
                     
                 }
                 else {flag=false;}
            }
            System.out.println(input.readUTF()+ input.readDouble() +"JD");
            //===========================================
        } catch (IOException ex) {
            Logger.getLogger(Client1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
