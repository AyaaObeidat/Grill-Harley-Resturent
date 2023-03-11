
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server {
    
    public static double BurgerOrder(Socket client1 ,DataInputStream input1 , DataOutputStream out1)
    {  
        double price = 0;
        try {
            out1.writeUTF("Please enter your type of Burger (Beef, Chicken, Kids) : ");
            String typeOfBurger,favorType,burgerSize="";
            int quantity;
            boolean flag = true;
            while(flag)
            {   typeOfBurger = input1.readUTF();
            if(typeOfBurger.equalsIgnoreCase("Beef") || typeOfBurger.equalsIgnoreCase("Chicken") || typeOfBurger.equalsIgnoreCase("Kids"))
            {
                boolean f = true;
                out1.writeUTF("Please enter your favor for :\n - Beef and Chicken: 1. Grill Harley    2. White Mushroom    3. Maxican \n - kids: 1. Happy Meal ");
                favorType = input1.readUTF();
                while(f)
                {
                    if(favorType.equalsIgnoreCase("Grill Harley")||favorType.equalsIgnoreCase("White Mushroom")||favorType.equalsIgnoreCase("Maxican"))
                    {out1.writeUTF("Do you want Sandwitch or meal ? ");burgerSize += input1.readUTF();f=false;}
                    else if(favorType.equalsIgnoreCase("Happy Meal")) {out1.writeUTF("the kids type .. just only Meal");f=false;}
                    else {out1.writeUTF("the type doesn't found .. try again");favorType = input1.readUTF();}
                }
                //=================================================================
                out1.writeUTF("Please enter the quantity : ");
                quantity = input1.readInt();
                if(favorType.equalsIgnoreCase("White Mushroom")||favorType.equalsIgnoreCase("Maxican"))
                {
                    if(burgerSize.equalsIgnoreCase("sandwitch")) price+=quantity*3.75;
                    else price+=quantity*4.95;
                }
                else if(favorType.equalsIgnoreCase("Grill Harley"))
                {
                    if(burgerSize.equalsIgnoreCase("sandwitch")) price+=quantity*3.25;
                    else price+=quantity*4.45;
                }
                else {price+=quantity*2.25;}
                out1.writeUTF("The price is : ");
                out1.writeDouble(price);
                flag = false;
            }
            else
            {out1.writeUTF("Worng input .. try again");}
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);}
        
            return price;
    }
 //=======================================================================================================
 //=======================================================================================================
  public static double PizzaOrder(Socket client2 , DataInputStream input2,DataOutputStream out2)
  {     
        double price=0;
        try {
            
            Scanner n = new Scanner(System.in);
            String favType,size="s";
            int quantity;
            boolean flag = true;
            out2.writeUTF("Please enter your favor of Pizza :\n 1. Margarita    2. Vegetables    3. Chicken ");
            while(flag)
            {   favType = input2.readUTF();
                if(favType.equalsIgnoreCase("Margarita")||favType.equalsIgnoreCase("Vegetables")||favType.equalsIgnoreCase("Chicken"))
                {
                    out2.writeUTF("Please enter the size of your pizza(L,M,S)..the size by default : S ");
                    size = input2.readUTF();
                    out2.writeUTF("How many boxes you want of this pizzza siza ? ");
                    quantity = input2.readInt();
                 switch (favType.toUpperCase())
                 {
                    case "MARGARITA" : 
                         if(size.equalsIgnoreCase("L")) price+=quantity*4.50;
                         else if(size.equalsIgnoreCase("M")) price+=quantity*2.50;
                         else price+=quantity*2.00;
                         break;
                    case "VEGETABLES" : 
                         if(size.equalsIgnoreCase("L")) price+=quantity*4.50;
                         else if(size.equalsIgnoreCase("M")) price+=quantity*3.25;
                         else  ;price+=quantity*2.50;
                         break;
                    case "CHICKEN" : 
                         if(size.equalsIgnoreCase("L")) price+=quantity*5.00;
                         else if(size.equalsIgnoreCase("M")) price+=quantity*3.25;
                         else price+=quantity*2.75;
                         break; 
                    default: System.out.println("wrong!");
                 }
                    out2.writeUTF("The price is : ");
                    out2.writeDouble(price);
                    flag=false;
                }
                else {out2.writeUTF("Worng input ... try again ");}
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
      return price;
  }
 //=======================================================================================================
 //=======================================================================================================
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(1515);
            Socket client1 = server.accept();
            Socket client2 = server.accept();
            DataInputStream input1 = new DataInputStream(client1.getInputStream());
            DataOutputStream out1 = new DataOutputStream(client1.getOutputStream());
            DataInputStream input2 = new DataInputStream(client2.getInputStream());
            DataOutputStream out2 = new DataOutputStream(client2.getOutputStream());
            System.out.println("Connection is done!");
            System.out.println("========================================");
            out1.writeUTF("Server said : hello client1");
            out2.writeUTF("Server said : hello client2");
            //========================================================================
            //======================================================================== 
            //======================================================================== 
            //======================================================================== 
            //Clint1...............................
            String BurgerMenu = "Grill Harley Resturent Menu : \n"+
                                "        ******Beef Burger******        \n"+
                                "               Sandwich     Meal\n"+
                                "Grill Harley   3.25JD       4.45JD\n"+
                                "White Mushroom 3.75JD       4.95JD\n"+
                                "Maxican        3.75JD       4.95JD\n"+
                                "        ******Chicken Burger******        \n"+
                                "               Sandwich     Meal\n"+
                                "Grill Harley   3.25JD       4.45JD\n"+
                                "White Mushroom 3.75JD       4.95JD\n"+
                                "Maxican        3.75JD       4.95JD\n"+
                                "        ******Kids Meal******        \n"+
                                "Happy Meal     2.25JD          ";
            System.out.println(input1.readUTF());
            out1.writeUTF(BurgerMenu);
            String otherOrder;
            double total1=0 , total2=0;
            boolean flag1=true,flag2 = true;;
            total1 +=BurgerOrder(client1,input1,out1);
            out1.writeUTF("Do you want to choose another burger?");
            otherOrder = input1.readUTF();
            while(flag1)
            {
                
                if(otherOrder.equalsIgnoreCase("yes"))  
                {
                    out1.writeUTF("repeated");
                    total1 += BurgerOrder(client1,input1,out1);
                }
                else {flag1=false;out1.writeUTF("not repeated");}
            }
            out1.writeUTF("The Total Price is :  ");
            out1.writeDouble(total1);
            //========================================================================
            //======================================================================== 
            //======================================================================== 
            //======================================================================== 
            //Clint2...............................
            String PizzaMenu = "Grill Harley Resturent Menu : \n"+
                                "        ******Pizza******        \n\n"+
                                "               S            M            L\n"+
                                "Margarita      2.00JD       2.50JD       4.50\n"+
                                "Vegetables     2.50JD       3.25JD       4.50\n"+
                                "Chicken        2.75JD       3.25JD       5.00JD\n";
            System.out.println(input2.readUTF());
            out2.writeUTF(PizzaMenu);
            total2+=PizzaOrder(client2,input2,out2);
            String othOrder;
            while(flag2)
            {
               out2.writeUTF("Do you want to choose another Pizza?");
               othOrder = input2.readUTF();
               if(othOrder.equalsIgnoreCase("yes"))
               {
                   out2.writeUTF("repeated!");
                   total2+=PizzaOrder(client2,input2,out2);
               }
               else {flag2=false;out2.writeUTF("not repeated!");}
            }
            out2.writeUTF("The total price of your order :  ");
            out2.writeDouble(total2);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
