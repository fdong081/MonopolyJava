
package MonopolyApp;


import static java.util.Collections.sort;
import java.util.LinkedList;
import java.util.Scanner;
import static MonopolyApp.Game.spaces;



public final class Game 
{

    static LinkedList<Player> players;
    static LinkedList<Space> spaces;
    static int numberOfPlayer;


    public ChanceDeck chanceDeck;
    public ChestDeck chestDeck;
    
    Game()
    {     
        players=new LinkedList<>();
        spaces=new LinkedList<>();
        createPlayers();
        createBoard(); 
        setUpPlayers();
        chanceDeck= new ChanceDeck();
        chestDeck= new ChestDeck();
        
    }
    
    
    public void createPlayers()
    {
        Scanner sc=new Scanner (System.in);
        String t;
        System.out.println("Enter the number of player: ");
        numberOfPlayer=getInt(sc);
        System.out.println("Enter a character as your token : ");
        for(int i=1;i<numberOfPlayer+1;i++)
        {
            System.out.println("Player "+i+ ": ");
            t=Game.getString(sc);
            players.add(new Player(t));
            
            
        }
        players.add(new AI("A"));
        for(Player p: players)
        {
            p.setTurn(p.rollDice());
        }
        sort(players);
         for(Player p: players)
        {
            System.out.println("Player : "+p.token);
        }
        
       
    }
    public void setUpPlayers()
    {
        players.stream().forEach((p) -> 
        {
            spaces.get(0).token.replace(players.indexOf(p), players.indexOf(p)+1, p.token);
        });
            
    }
    
    public void createBoard()
    {
        spaces.add(new Space(0,"Go","<---"));
        spaces.add(new Color(1,"Mediterranean","Ave","brown"));
        spaces.add(new Space(2,"Chest",""));
        spaces.add(new Color(3,"Baltic","Ave","brown"));
        spaces.add(new Space(4,"Income"," tax"));
        spaces.add(new Railroad(5,"Reading ","Railroad","Railroad"));
         spaces.add(new Color(6,"Oriental","Ave","lightBlue"));
        spaces.add(new Space(7,"Chance",""));
        spaces.add(new Color(8,"Vermont","Ave","lightBlue"));
        spaces.add(new Color(9,"Connecticut","Ave","lightBlue"));
        spaces.add(new Space(10,"Visiting"," Jail"));
        spaces.add(new Color(11,"St.Charles","Place","pink"));
         spaces.add(new Utility(12,"Electric","Company","utility"));
        spaces.add(new Color(13,"States","Ave","pink"));
        spaces.add(new Color(14,"Virginia","Ave","pink"));
        spaces.add(new Railroad(15,"Pennsylvania","Railroad","Railroad"));
        spaces.add(new Color(16,"St.James","Place","orange"));
        spaces.add(new Space(17,"Chest",""));
         spaces.add(new Color(18,"Tennessee","Ave","orange"));
        spaces.add(new Color(19,"NewYork","Ave","orange"));
        spaces.add(new Space(20,"Free","Parking"));
        spaces.add(new Color(21,"Kentucky","Ave","red"));
        spaces.add(new Space(22,"Chance",""));
        spaces.add(new Color(23,"Indiana","Ave","red"));
         spaces.add(new Color(24,"Illinois","Ave","red"));
        spaces.add(new Railroad(25,"B&O","Railroad","railroad"));
        spaces.add(new Color(26,"Atlantic","Ave","yellow"));
        spaces.add(new Color(27,"Ventnor","Ave","yellow"));
        spaces.add(new Utility(28,"Water","Works","utility"));
        spaces.add(new Color(29,"Marvin","Garden","yellow"));
         spaces.add(new Space(30,"Go to jail",""));
        spaces.add(new Color(31,"Pacific","Ave","green"));
        spaces.add(new Color(32,"NorthCarolina","Ave","green"));
        spaces.add(new Space(33,"Chest",""));
        spaces.add(new Color(34,"Pennsylvinia","Ave","green"));
        spaces.add(new Railroad(35,"ShortLine","Railroad","railroad"));
         spaces.add(new Space(36,"Chance",""));
        spaces.add(new Color(37,"Park","Place","blue"));
        spaces.add(new Space(38,"Luxury","Tax"));
        spaces.add(new Color(39,"Board","walk","blue"));
       
        
        
    }
    



    public void displayMenu()
    {
        System.out.println("1. End turn");
        System.out.println("2. Sell House/ Hotel");
        System.out.println("3. Build House");
        System.out.println("4. Mortgage");
        System.out.println("5. Lift mortgage");
        System.out.println("6. Trade");
        
    }
    public static void setPosition(Player p,int position) 
    {
        if (position-p.currentLocation<0)
        {
                     
            System.out.println("player "+p.token +" collect 200 ");
            p.setBalance(200);
             
        }
        
        spaces.get(p.currentLocation).token.replace(players.indexOf(p), players.indexOf(p)+1, "-");
        
        p.currentLocation=position;
        
        spaces.get(p.currentLocation).token.replace(players.indexOf(p), players.indexOf(p)+1, p.token);
        displayBoard();
    }
    
    public static void goBackTo(Player p, int position)
    
    {
        spaces.get(p.currentLocation).token.replace(players.indexOf(p), players.indexOf(p)+1, "-");
        
        p.currentLocation=position;
        
        spaces.get(p.currentLocation).token.replace(players.indexOf(p), players.indexOf(p)+1, p.token);
        displayBoard();
        
    }
    public static void clearScreen()
    {  
     
   
   } 
    
    
    public void buyOrPay(Player p)
    {
        Scanner sc=new Scanner(System.in);
        Property sp=(Property)(spaces.get(p.currentLocation));
        
        if (sp instanceof Color)
            System.out.println((Color)sp);
        else if(sp instanceof Utility)
            System.out.println((Utility)sp); 
        else if(sp instanceof Railroad)
            System.out.println((Railroad)sp);
        
        if(sp.isOwned)
        {
            if(!sp.isMortgaged)
            {
                int rent = 0;
                if(sp instanceof Color)
                     rent= ((Color)sp).getCurrentRent();
                
                else if (sp instanceof Utility)
                {
                    int dice1=p.rollDice();
                    int dice2=p.rollDice();
                    rent=((Utility)sp).getCurrentRent(dice1+dice2,sp.getOwner().utility.size());
                }
                
                else if(sp instanceof Railroad)
                    rent=((Railroad)sp).getCurrentRent(sp.getOwner().railroad.size());
                
                p.payRent(rent,((Property)sp).getOwner());
            }
        }
        else if(!sp.isOwned)
        {
            System.out.println("This property is available");
            System.out.println("Your balance is "+p.getBalance());
            System.out.println("Do you want to buy this property? ");
            String choice;
            
            if(p instanceof AI)
            {
                if(p.getBalance()>=300)
                    choice="yes";
                else
                    choice="no";
            
                System.out.println("AI's choice is: "+choice);
       
            }
            else
                choice= sc.next();
            
            
            while(!choice.equalsIgnoreCase("yes")&& !choice.equalsIgnoreCase("no"))
            {
                System.out.println("Do you want to buy this property? ");
                choice= sc.next();
            }
            if (choice.equalsIgnoreCase("yes")&& p.getBalance() >=((Property)spaces.get(p.currentLocation)).getPrice())
            {
                p.buyProperty(spaces.get(p.currentLocation));
            }
            else 
            {
                if(choice.equalsIgnoreCase("no"))
                {
                    System.out.println("You did not want this property . Banker put this property up for auction!");
                    auction();
                }
                else 
                {
                    System.out.println("You do not have enough money for this property now !");
                    p.getCash(((Property)spaces.get(p.currentLocation)).getPrice());
                    if(p.getBalance() >=((Property)spaces.get(p.currentLocation)).getPrice())
                         p.buyProperty(spaces.get(p.currentLocation));
                    else
                        auction();
                }
                
            }
        }
    }
    public void auction()
    {
        /*Scanner sc = new Scanner(System.in);
        
        System.out.println("This property is up for auction. ");
         String choice;
         int base=10;
         for(Player p: players)
         {
             System.out.println(p.token+" : ");
             System.out.println("fold");
             System.out.println("place bid: ");
             choice=sc.next();
             if(choice.equalsIgnoreCase("fold"))
                 players.remove(p);
             else 
             {
                 placeBid(base);
             
             }
         }*/
    }
    
    public static void displayBoard()
    {
       // clearScreen();
        String firstNameLine;
        String lastNameLine;
        String tokenLine;
        String border;
        String borderO="";
        String borderI="";
        int wide=15;
        for(int i=0;i<wide*11;i++)
        {
        borderO+="-";
        }
        for(int i=0;i<wide*11;i++)
        {
            
            if( (i>=0 && i<wide)|| (i>=wide*10 && i<wide*11))
                borderI+="-";
            else
                borderI+=" ";
        }
        
        
        for(int row=0;row<11;row++)
        {
            firstNameLine="";
            lastNameLine="";
            tokenLine="";
            if(row==0 || row==10)
                border=borderO;
            else
                border=borderI;
                
            if(row==0)
            {
                
                for(int column=0;column<11; column++)
                {
                    firstNameLine+="|"+spaces.get(20-row+column).name;
                    lastNameLine+="|"+spaces.get(20-row+column).lname;
                    tokenLine+="|"+spaces.get(20-row+column).token;
                    for(int x=spaces.get(20-row+column).name.length()+1; x<wide;x++)
                        firstNameLine+=" ";
                    for(int x=spaces.get(20-row+column).lname.length()+1; x<wide;x++)
                        lastNameLine+=" ";
                    for(int x=spaces.get(20-row+column).token.length()+1; x<wide;x++)
                        tokenLine+=" ";  
                }
                firstNameLine+="|";
                lastNameLine+="|";
                tokenLine+="|";
                System.out.println(border);
                System.out.println(firstNameLine);
                System.out.println(lastNameLine);
                System.out.println(tokenLine);
                System.out.println(border);

            }
            else if(row==10)
            {
                
                for(int column=0;column<11; column++)
                {
                    firstNameLine+="|"+spaces.get(10-column).name;
                    lastNameLine+="|"+spaces.get(10-column).lname;
                    tokenLine+="|"+spaces.get(10-column).token;
                    for(int x=spaces.get(10-column).name.length()+1; x<wide;x++)
                        firstNameLine+=" ";
                    for(int x=spaces.get(10-column).lname.length()+1; x<wide;x++)
                        lastNameLine+=" ";
                    for(int x=spaces.get(10-column).token.length()+1; x<wide;x++)
                        tokenLine+=" ";  
                }
                firstNameLine+="|";
                lastNameLine+="|";
                tokenLine+="|";
                System.out.println(border);
                System.out.println(firstNameLine);
                System.out.println(lastNameLine);
                System.out.println(tokenLine);
                System.out.println(border);

            
            }
            else
            {
                for(int column=0;column<11; column++)
                {
                    
                    if(column==0 )
                    {
                        firstNameLine+="|"+spaces.get(20-row).name;
                        lastNameLine+="|"+spaces.get(20-row).lname;
                        tokenLine+="|"+spaces.get(20-row).token;
                        for(int x=spaces.get(20-row).name.length()+1; x<wide;x++)
                            firstNameLine+=" ";
                        for(int x =spaces.get(20-row).lname.length()+1; x<wide;x++)
                            lastNameLine+=" ";
                        for(int x=spaces.get(20-row).token.length()+1; x<wide;x++)
                            tokenLine+=" ";  
                        firstNameLine+="|";
                        lastNameLine+="|";
                        tokenLine+="|";
                       
                    }
                    else if(column==10 )
                    {

                        firstNameLine+="|"+spaces.get(30+row).name;
                        lastNameLine+="|"+spaces.get(30+row).lname;
                        tokenLine+="|"+spaces.get(30+row).token;
                        for(int x=spaces.get(30+row).name.length()+1; x<wide;x++)
                            firstNameLine+=" ";
                        for(int x=spaces.get(30+row).lname.length()+1; x<wide;x++)
                            lastNameLine+=" ";
                        for(int x=spaces.get(30+row).token.length()+1; x<wide;x++)
                            tokenLine+=" ";
                        firstNameLine+="|";
                        lastNameLine+="|";
                        tokenLine+="|";
                    }      
                    else 
                    {
                        int s;
                        if(column==1||column==9)
                        {
                            s=1;
                        }
                        else
                            s=0;
                        
                        for(int x=s ; x<wide;x++)
                            {
                                firstNameLine+=" ";
                                lastNameLine+=" "; 
                                tokenLine+=" ";
                            }    

                    }

                }
               
                System.out.println(border);
                System.out.println(firstNameLine);
                System.out.println(lastNameLine);
                System.out.println(tokenLine);
                System.out.println(border);
               
            
            }
        }
        
    }
    
    
    
    public void instructionChance(Player p)
    {
        chanceDeck.getChance();
        switch(chanceDeck.getCardIndex())
        {
            case 1:
                setPosition(p,0);
                break;
            case 2:
                {
                   setPosition(p,24);
                   buyOrPay(p);
                   break;
                }
                
            case 3:
                {
                   setPosition(p,11);
                   buyOrPay(p);
                   break;
                }
                
            case 4:
                {
                   if(p.currentLocation>=12 && p.currentLocation< 28)
                       setPosition(p,28);
                   else
                       setPosition(p, 12);
                   buyOrPay(p);
                   break;
                }
                
            case 5:
                {
                   if(p.currentLocation<5 || p.currentLocation >= 35)
                       setPosition(p,5);
                   else if(p.currentLocation >=5 && p.currentLocation <15)
                       setPosition(p, 15);
                   else if(p.currentLocation >=15 && p.currentLocation <25)
                        setPosition(p, 25);
                   else
                        setPosition(p, 35);
                   buyOrPay(p);
                   break; 
                }
                
             case 6:
                p.setBalance(50);
                break; 
                 
             case 7:
                 p.holdOrSellCard(players);
                 break;
                 
            case 8:
                {
                   goBackTo(p,(p.currentLocation+37)%40);
                   buyOrPay(p);
                   break;
                }
                
            case 9:
                {
                    p.inJail=true;
                    p.currentLocation=10;
                    break;
                }
                     
            case 10:
                p.setBalance(-(25*p.house+50*p.hotel));
                break;
                
            case 11:
                p.setBalance(-15);
                break;
                
            case 12:
                {
                   setPosition(p,5);
                   buyOrPay(p);
                   break;
                }
                
            case 13:
                {
                   setPosition(p,39);
                   buyOrPay(p);
                   break;                  

                }
                
            case 14:
                {  
                p.setBalance(-(50*players.size()));
                players.stream().forEach((x) -> {
                    x.setBalance(50);
                    });
                break;
                }
                   
            case 15:
                 p.setBalance(150);
                break; 
            case 16:
                p.setBalance(100);
                break;        
        }
        
        
    }

    public void instructionChest(Player p)
    {
        chestDeck.getChest();
        switch(chestDeck.getCardIndex())
        {
            case 1:
                setPosition(p,0);
                break;
            case 2:
                p.setBalance(200);
                break;
            case 3:
                p.setBalance(-50);
                break;
            case 4:
                p.setBalance(50);
                break;
            case 5:
                p.holdOrSellCard(players);
                break; 
            case 6:
                {
                    p.goToJail();
                    break;
                }
                    
            case 7:
                {
                p.setBalance(50*players.size());
                players.stream().forEach((x) -> {
                    x.setBalance(-50);
            });
                 break;
                }
               
            case 8:
            case 11:
            case 17:
                 p.setBalance(100);
                break; 
            case 9:
                 p.setBalance(20);
                break;   
            case 10:
                {  
                p.setBalance(10*players.size());
                for(Player x: players)
                    x.setBalance(-10);
                break;
                }
                
            case 12:
                p.setBalance(-100);
                break;
            case 13:
                p.setBalance(-150);
                break;
            case 14:
                p.setBalance(25);
                break;
                
            case 15:
                p.setBalance(-(40*p.house+115*p.hotel));
                break;
            case 16:
                p.setBalance(10);
                break;
        }
        
        
    }
    public static int getIntWithin(int min, int max,Scanner sc, Player p)
    {
        
        int choice=0;
        if(p instanceof AI)
        {
            int range;
            if(AI.action.equalsIgnoreCase("mainmenu"))
            {
                range=1+(int) (Math.random()*(10));
                switch(range)
                {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        choice=1;
                        break;
                    case 6:
                        choice=2;
                        break;
                    case 7:
                        choice=3;
                        break;
                    case 8:
                        choice=4;
                        break;
                    case 9:
                        choice=5;
                        break;
                    case 10:
                        choice=6;
                        break;
                }

            }
            else if(AI.action.equalsIgnoreCase("getCash"))
            {
                 if(p.house>0 || p.hotel>0)
                     choice=1;
                 else if(p.hotel==0||p.house==0 && p.unimproved.size()>0)
                     choice=3;
                 else if(p.mortgaged.size()>0)
                     choice=2;
                 else if(p.getBalance()<=0)
                     choice=4;
                 else
                     choice=5;
            }
            else if(AI.action.equalsIgnoreCase("getOutOfjail"))
            {
                   if(p.getOutJailCard>=1)
                       choice=1;
                   else
                   {
                       choice=2+(int) (Math.random()*2);
   
                   }
            
            }
            else 
            {
                choice=min+(int) (Math.random()*(max-min)+1);
 
            }
        
            System.out.println("AI choose: "+choice);
        
        }
        else
        {    
                
            boolean isValid=false;

            while (!isValid )
            {
                choice=getInt(sc);
                if(choice<min)
                {
                    System.out.println("Invalid input. Choice must be greater than "+min+" !");

                }
                else if(choice>max)
                {
                    System.out.println("Invalid input. Choice must be less than "+max+" !");
                }
                else
                    isValid=true;

            }
                
        }
        return choice;
    
    }
    public static int getInt(Scanner sc)
    {
        int choice=0;
        boolean isValid=false;
        while(!isValid)
        {
            if (sc.hasNextInt())
            {
                choice=sc.nextInt();
                isValid=true;
            }
            else
            {
                System.out.println("Invalid Integer.Try again.");
            }
            sc.nextLine();
        }
        return choice;
    
    }

    public static String getString(Scanner sc)
    {
        String input = null;
        boolean isValid=false;
        while(!isValid)
        {
            if (sc.hasNext())
            {
                input=Character.toString(sc.next().charAt(0));
                isValid=true;
            }
            else
            {
                System.out.println("Invalid .Try again.");
            }
            sc.nextLine();
        }
        return input;
    }
    



      
}
