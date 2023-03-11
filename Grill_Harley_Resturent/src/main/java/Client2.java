
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client2 {
    public static void PizzaOrder( Socket client2,DataOutputStream out,DataInputStream input)
    {
        try {
            Scanner n = new Scanner(System.in);
            String favorType,Smsg,size="s";
            int quantity;
            boolean flag = true;
            System.out.println(input.readUTF());
            favorType = n.nextLine();
            out.writeUTF(favorType);
            //===================================
            while(flag)
            {
                
                Smsg = input.readUTF();
                if(!Smsg.equalsIgnoreCase("Worng input ... try again "))
                {
                    System.out.println(Smsg);
                    size=n.nextLine();
                    out.writeUTF(size);
                    System.out.println(input.readUTF());
                    quantity = n.nextInt();
                    out.writeInt(quantity);
                    System.out.print(input.readUTF()+"    ");
                    System.out.println(input.readDouble());
                    flag = false;
                }
                else
                {
                    System.out.println(Smsg);
                    favorType=n.nextLine();
                    out.writeUTF(favorType);
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Client2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //===========================================================
    public static void main(String[] args) {
        try {
            Scanner n = new Scanner(System.in);
            Socket client2 = new Socket("localhost",1515);
            DataOutputStream out = new DataOutputStream(client2.getOutputStream());
            DataInputStream input = new DataInputStream(client2.getInputStream());
            //======================================
            System.out.println(input.readUTF());
            System.out.println("========================================");
            out.writeUTF("Hello server! Please send me the Pizza Menu "); 
            System.out.println("========================================");
            System.out.println(input.readUTF());
            PizzaOrder(client2,out,input);
            //=====================================
            boolean flag = true;
            String otherOrder,rep;
            while(flag)
            {
                System.out.println(input.readUTF());
                otherOrder = n.nextLine();
                out.writeUTF(otherOrder);
                rep = input.readUTF();
                if(rep.equalsIgnoreCase("repeated!"))
                {
                    PizzaOrder(client2,out,input);
                }
                else flag=false;
            }
             System.out.print(input.readUTF());
             System.out.println(input.readDouble()+"JD");
        } catch (IOException ex) {
            Logger.getLogger(Client2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
