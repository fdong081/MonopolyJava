
package MonopolyApp;


import java.util.LinkedList;
import java.util.Scanner;

public class Player implements Comparable
{
    String token;
    private int turn;
    
    private int balance;
    public int currentLocation;
    public boolean inJail;
    public boolean isOut;
    public int getOutJailCard;
    
    public int house;
    public int hotel;
    public static int count_Double=0;
    public static int failToRollDouble=0;
    
    public LinkedList<Space> redProperty ;
    public LinkedList<Space> lightBlueProperty ;
    public LinkedList<Space> pinkProperty;
    public LinkedList<Space> yellowProperty;
    public LinkedList<Space> brownProperty;
    public LinkedList<Space> greenProperty;
    public LinkedList<Space> blueProperty ;
    public LinkedList<Space> orangeProperty;
    public LinkedList<Space> railroad ;
    public LinkedList<Space> utility ;
    
    public LinkedList<String> toBuild ;
    public LinkedList<String> toSell ;
    public LinkedList<Space> mortgaged ;
    public LinkedList<Space> unimproved ;
    
    private static int startBuild;
    private static int startSell;
    
    Player(String t)
    {
        token=t;
        balance=1500;
        currentLocation=0;
        inJail=false;
        isOut=false;
        getOutJailCard=0;
        house=0;
        hotel=0;
        startBuild=0;
        startSell=0;
        redProperty =new LinkedList<>();
        lightBlueProperty =new LinkedList<>();
        pinkProperty =new LinkedList<>();
        yellowProperty =new LinkedList<>();
        brownProperty =new LinkedList<>();
        greenProperty =new LinkedList<>();
        blueProperty =new LinkedList<>();
        orangeProperty =new LinkedList<>();
        railroad =new LinkedList<>();
        utility =new LinkedList<>();
        
        toSell =new LinkedList<>();
        toBuild =new LinkedList<>();
        mortgaged =new LinkedList<>();
        unimproved=new LinkedList<>();
    }
    public int getTurn()
    {
        return turn;
    }
    
    public void setTurn(int a)
    {
        turn=a;
    }
    
    public int getBalance()
    {
        return balance;
    }
    public void setBalance(int amount)
    {
        if (this.balance+amount<0)
            getCash(amount);
        if(this.balance+amount>=0)
        {
            this.balance+=amount;
            System.out.println("Player "+this.token+" balance is "+this.getBalance());
        }
        else
        {
            this.balance=0;
            System.out.println(this.token+ " is out.");
            this.isOut=true;
        }
    
    }
    public void getCash(int amount)
    {
            Scanner sc=new Scanner(System.in);
            int choice;
            while (this.balance<amount)
            {
                System.out.println("1.Sell");
                System.out.println("2.trade");
                System.out.println("3. Mortgage");
                System.out.println("4. Declare bankruptcy");
                System.out.println("5.Exit");
                AI.action="getCash";
                choice=Game.getIntWithin(1, 5, sc, this);
                            
                if(choice==1)
                    this.sell();
                else if(choice==2)
                    this.trade(Game.players);
                else if(choice==3)
                    this.mortgage();
                else if(choice==5)
                    break;
                else
                {
                    this.isOut=true;
                    break;
                }
            }
        
    }
    
    public int rollDice()
    {
        return 1+(int)(Math.random()*6);
    
    }
    public void checkSpace(Game g,Space s)
    {
        if(s.getIndex()==7 || s.getIndex()==22 || s.getIndex()==36)
        {
            System.out.println("Take one card from chance deck");
            g.instructionChance(this);
        }
        else if(s.getIndex()==2 || s.getIndex()==17 || s.getIndex()==33)
        {
            System.out.println("Take one card from community chest deck");
            g.instructionChest(this);
        }
            
        else if(s.getIndex()==4)
        {
            System.out.println("Pay Income tax $ 200 ");
            this.setBalance(-200);
        }
        else if(s.getIndex()==0)
        {
            System.out.println("Collect $200 ");
            this.setBalance(200);
            
        }
        else if(s.getIndex()==30)
        {
            System.out.println("Go to jail ");
            this.goToJail();
        }
        else if(s.getIndex()==38)
        {
            System.out.println("Pay Luxury tax $ 200 ");
            this.setBalance(-100);
        }
        else if(s.getIndex()==20)
            System.out.println("Free Parking!");
        else if(s.getIndex()==10)
        {
            if(this.inJail==false)
                System.out.println("Visiting!");
            else
                System.out.println("In Jail!");
        }
            
        else
            g.buyOrPay(this);
        
       
    }
    public void move(Game g)
    {
        int dice1;
        int dice2;
        Scanner sc=new Scanner(System.in);
        if(!this.inJail)
        {
            dice1=rollDice();
            dice2= rollDice();
            int totalDice=dice1+dice2;
            int position;
            position = this.currentLocation + totalDice;
        
            System.out.println("Player: "+this.token+" rolled "+totalDice);
            if (dice1==dice2)
            {
                System.out.println("Player: "+this.token+" Rolled double.");
                count_Double++;
                if(count_Double!=3)
                {  
                    if (!this.isOut)
                    {
                        Game.setPosition(this,position%40);
                        this.checkSpace(g, Game.spaces.get(this.currentLocation));
                        this.move(g);
                    }
                }
                else
                {
                    System.out.println("Player "+this.token+" rolled 3 times double, go to jail");
                    this.goToJail();
                }
            }
            
            else
            {
                Game.setPosition(this,position%40);
        
                this.checkSpace(g, Game.spaces.get(this.currentLocation));
            }
            count_Double=0;
        } 
        
        else
        {
            System.out.println("Player "+this.token+" turn."+"\nYou are in Jail");
            System.out.println("1. Use get out of jail card.");
            System.out.println("2. Roll Double");
            System.out.println("3. Pay $50");
            AI.action="getOutOfjail";
            int choice=Game.getIntWithin(1, 3, sc,this); 
            
            while(choice==1 && getOutJailCard==0)
            { 
                System.out.println("You do not have GET OUT OF JAIL card. Please choose");
                
                System.out.println("2. Roll Double");
                System.out.println("3. Pay $50");
                choice=Game.getIntWithin(1, 3, sc,this); 
            }
            
            if(choice==1)
            { 
                this.inJail=false;
                getOutJailCard--;
                System.out.println("You used one Get out of Jail Free Card");
                this.move(g);
            }
           
            else if(choice==3)
            {
                this.inJail=false;
                System.out.println("Player "+this.token+" pay 50 fine");
                this.setBalance(-50);
                if(!this.isOut)
                    this.move(g);
                else
                    System.out.println("Player: "+this.token +" is out");
            }
            else if(choice==2)
            {
                dice1=rollDice();
                dice2= rollDice();
                if(dice1!=dice2)
                {
                    System.out.println("Player: "+this.token+" rolled "+dice1+" and "+ dice2);
                    failToRollDouble++;
                    System.out.println("Player: "+this.token+" fail to roll double "+ failToRollDouble+" times");
                    if(failToRollDouble==3)
                    {
                        System.out.println("Player: "+this.token+" fail to roll double within 3 times  pay $50 fine and roll dice");
                        this.setBalance(-50);
                        if(!this.isOut)
                        {
                            this.inJail=false;
                            this.move(g);
                        }
                        else
                            System.out.println("Player: "+this.token +" is out");
                    }
                }
                else
                {
                    failToRollDouble=0;
                    System.out.println("Player: "+this.token+" rolled "+dice1+" and "+ dice2);
                    Game.setPosition(this, 10+dice1+dice2);
                }
            }
                
        }
    }
    
    public void goToJail()
    {
        inJail=true;
        Game.goBackTo(this,10);
    
    }
   public void payRent(int rent,Player p)
    {
        System.out.println("Player "+this.token+" pay rent "+rent+" to Player "+p.token);
        this.setBalance(-rent);
        p.setBalance(rent);
        
    }
    public void buyProperty(Space s)
    {
      
          ((Property)s).setOwner(this);
            
            ((Property)s).isOwned=true;
            
            this.setBalance(-((Property)s).getPrice());
            
            addToProperty(s);
        
    }
    public void addToProperty(Space s)
    {
        if(((Property)s).type.equalsIgnoreCase("red"))
        {
            redProperty.add((Color)s);
            if (redProperty.size()==3)
            {
                toBuild.add("red");
                ((Color)s).isMonopoly=true;
            }
            
        }
        
        else if(((Property)s).type.equalsIgnoreCase("blue"))
        {
            blueProperty.add((Color)s);
            if (blueProperty.size()==2)
            {
                toBuild.add("blue");
                ((Color)s).isMonopoly=true;
            }
            
        }
            
      
        else if(((Property)s).type.equalsIgnoreCase("lightBlue"))
          {
            lightBlueProperty.add((Color)s);
            if (lightBlueProperty.size()==3)
            {
                toBuild.add("lightBlue");
                ((Color)s).isMonopoly=true;
            }
            
        }
      
            
        else if(((Property)s).type.equalsIgnoreCase("orange")) 
          {
            orangeProperty.add((Color)s);
            if (orangeProperty.size()==3)
            {
                toBuild.add("orange");
                ((Color)s).isMonopoly=true;
            }
            
        }
            
      
        else if(((Property)s).type.equalsIgnoreCase("pink"))
           {
            pinkProperty.add((Color)s);
            if (pinkProperty.size()==3)
            {
                toBuild.add("pink");
                ((Color)s).isMonopoly=true;
            }
            
        }
           
       
        else if(((Property)s).type.equalsIgnoreCase("brown"))
            {
            brownProperty.add((Color)s);
            if (brownProperty.size()==2)
            {
                toBuild.add("brown");
                ((Color)s).isMonopoly=true;
            }
            
        }
                
        else if(((Property)s).type.equalsIgnoreCase("green"))
            {
            greenProperty.add((Color)s);
            if (greenProperty.size()==2)
            {
                toBuild.add("green");
                ((Color)s).isMonopoly=true;
            }
            
            }      
                
        else if(((Property)s).type.equalsIgnoreCase("yellow"))
           {
            yellowProperty.add((Color)s);
            if (yellowProperty.size()==2)
            {
                toBuild.add("yellow");
                ((Color)s).isMonopoly=true;
            }
            
            }  
                       
        else if(((Property)s).type.equalsIgnoreCase("Utility"))
            utility.add((Utility)s);
    
        else if(((Property)s).type.equalsIgnoreCase("Railroad"))
            railroad.add((Railroad)s);        
        
        
        unimproved.add((Property)s);
    }
    
    public void holdOrSellCard(LinkedList<Player> players)
    {
        System.out.println("1. Trade card");
        System.out.println("2. Hold card");
      
        Scanner sc =new Scanner(System.in);
        AI.action="holdOrSellCard";
        int choice=Game.getIntWithin(1,2,sc, this);
        if(choice==1)
        {
            trade(players);
        }
        else//choice===2
        {
            this.getOutJailCard++;
        }
           
    } 
    
    public void sell()
    {
        if(toSell.size()==0)
            System.out.println("You do not have any Hous to sell");
        else
        {
            Scanner sc=new Scanner(System.in);
            LinkedList<Space> propertylist=new LinkedList<>();
            Color c;
            
            for(int i=0;i<toSell.size();i++)
            {
                System.out.println(i+1+". "+toSell.get(i));
            }
        System.out.println("What color group do you want to sell? (Enter 0 to Exit)");
        AI.action="sell";
        int color;
        
        color=Game.getIntWithin(0, toSell.size(), sc, this);
        
        if(color!=0)
        {
            if(toSell.get(color-1).equalsIgnoreCase("red"))
                propertylist=redProperty;
            else if (toSell.get(color-1).equalsIgnoreCase("lightBlue"))
                propertylist=lightBlueProperty;
            else if (toSell.get(color-1).equalsIgnoreCase("blue"))
                propertylist=blueProperty;
            else if (toSell.get(color-1).equalsIgnoreCase("orange"))
                propertylist=orangeProperty;
            else if (toSell.get(color-1).equalsIgnoreCase("pink"))
                propertylist=pinkProperty;
            else if (toSell.get(color-1).equalsIgnoreCase("yellow"))
                propertylist=yellowProperty;
            else if (toSell.get(color-1).equalsIgnoreCase("brown"))
                propertylist=brownProperty;
            else if (toSell.get(color-1).equalsIgnoreCase("green"))
                propertylist=greenProperty;       
        
       
            for (Space x:propertylist)
            {
                this.house+=((Color)x).house;
                this.hotel+=((Color)x).hotel;
            }
            
            int totalHouse=this.house+this.hotel*5;
            
            System.out.println("How many houses do you want to sell?(0-"+totalHouse+" )?" );
        
            int number=Game.getIntWithin(0, totalHouse, sc, this);
            
            for(int i=startSell;i<number+startSell;i++)
            {
                c=(Color)propertylist.get(i%propertylist.size());
                
                if(c.house==0 && c.hotel>=1)
                {
                    c.house=5;
                    c.hotel--;
                }
                c.house--;
               
            }
            
                  
            startSell=number%propertylist.size();

            //recalculate current palyer's house and hotel
            this.house=0;
            this.hotel=0;
            
            for (Space x:propertylist)
            {

            this.house+=((Color)x).house;
            this.hotel+=((Color)x).hotel;
                
            }
            
            
            if (this.house==0 || this.hotel==0 )
            {
                toSell.remove(color-1);
                for(Space x:propertylist)
                    unimproved.add(x);
            }
            System.out.println(number+" houses have been sold on "+((Color)propertylist.get(0)).type);
            System.out.println("Player "+this.token+" balance is "+this.getBalance());
        }
        }
    }
    
    public void build()
    {
                
        if(toBuild.size()==0)
            System.out.println("You cannot build house now");
        else
        {
        Scanner sc=new Scanner(System.in);
        Color c;
        LinkedList<Space> propertylist=new LinkedList<>();
        for(int i=0;i<toBuild.size();i++)
            {
                System.out.println(i+1+". "+toBuild.get(i));
            }
        System.out.println("What color group do you want to build house on (Enter 0 to Exit)");
        
        AI.action="build";
        
        int color=Game.getIntWithin(0, toBuild.size(), sc, this);
        
        if(color!=0)
        {       
            if(toBuild.get(color-1).equalsIgnoreCase("red"))
                propertylist=redProperty;
            else if (toBuild.get(color-1).equalsIgnoreCase("lightBlue"))
                propertylist=lightBlueProperty;
            else if (toBuild.get(color-1).equalsIgnoreCase("blue"))
                propertylist=blueProperty;
            else if (toBuild.get(color-1).equalsIgnoreCase("orange"))
                propertylist=orangeProperty;
            else if (toBuild.get(color-1).equalsIgnoreCase("pink"))
                propertylist=pinkProperty;
            else if (toBuild.get(color-1).equalsIgnoreCase("yellow"))
                propertylist=yellowProperty;
            else if (toBuild.get(color-1).equalsIgnoreCase("brown"))
                propertylist=brownProperty;
            else if (toBuild.get(color-1).equalsIgnoreCase("green"))
                propertylist=greenProperty;       

            System.out.println("How many houses do you want to build? $" +((Color)propertylist.get(0)).getPrice()+"per house");
        
            int number;
            
            if(this instanceof AI)
                number=Game.getIntWithin(0,this.balance/2/((Color)propertylist.get(0)).getPrice() , sc, this);
            else
                number=Game.getInt(sc);
            
            while(number*((Color)propertylist.get(0)).houseCost>this.balance)
            {
                System.out.println("You currently do not have enough money for these houses. Please choose: ");
                System.out.println("1. re-enter house number");
                
                System.out.println("2. Get Cash.");
            
                int choice=Game.getIntWithin(1,2, sc, this);
                if(choice==1)
                    number=Game.getInt(sc);
                else if(choice==2)
                {
                    getCash(number*((Color)propertylist.get(0)).houseCost);
                }
             
            }
        
        
        for(int i=startBuild;i<number+startBuild;i++)
        {
            c=(Color)propertylist.get(i%propertylist.size());
            c.house++;
            if( c.house>4)
            {
                c.house=0;
                c.hotel++;
            }
            
        }
        
        startBuild=number%propertylist.size();
             
        for (Space x:propertylist)
        {
            if(((Color)x).house!=0)
            {
                toSell.add(((Color)propertylist.get(0)).type);
                unimproved.remove(x);
            }
            this.house+=((Color)x).house;
            this.hotel+=((Color)x).hotel;
                
        }
        if(number>=1)
        {
            System.out.println(number+" houses have been built on "+ ((Color)propertylist.get(0)).type + " color group " );
            
        }
        
        } 
        
        
     }
    
    }
    public void liftMortgage()
    {
        if(mortgaged.size()==0)
            System.out.println("Your mortgaged property list is empty.");
        else
        {
            Scanner sc = new Scanner(System.in);
            int number;
            for(int i=1;i<=mortgaged.size();i++)
            {
                Space sp=mortgaged.get(i-1);
                System.out.println("     #"+i+" : ");
                if (sp instanceof Utility)
                    System.out.println((Utility)sp);
                else if (sp instanceof Railroad)
                    System.out.println((Railroad)sp);
                else if (sp instanceof Color)
                    System.out.println((Color)sp);
            }
            System.out.println("Which property do you want to lift morgage? Enter 0 to exit");
            AI.action="liftMortgage";
            number=Game.getIntWithin(0, mortgaged.size(), sc, this);
            if(number!=0)
            {
                ((Property)mortgaged.get(number-1)).isMortgaged=false;
                this.unimproved.add((Property)mortgaged.get(number-1));

                this.setBalance(-((Property)mortgaged.get(number-1)).getPrice()/2);
                this.setBalance(-((Property)mortgaged.get(number-1)).getPrice()/10);
                if(this.isOut==false)
                {
                    this.mortgaged.remove(number-1);

                    System.out.println("You have lifted mortgage for: "+(Property)unimproved.getLast());
                    System.out.println("Your current balance is "+this.getBalance());
                }
            }
        }
    }
    public void mortgage()
    {
        if(unimproved.size()==0)
            System.out.println("Your unimproved property list is empty. Can't implement mortgage ");
        else
        {        
            Scanner sc = new Scanner(System.in);
            int number;
            for(int i=1;i<=unimproved.size();i++)
            {
                Space sp=unimproved.get(i-1);
                System.out.println("     #"+i+" : ");
                if (sp instanceof Utility)
                    System.out.println((Utility)sp);
                else if (sp instanceof Railroad)
                    System.out.println((Railroad)sp);
                else if (sp instanceof Color)
                    System.out.println((Color)sp);
            }
            System.out.println("Which property do you want to mortgage? Enter 0 to exit");
            AI.action="mortgage";

            number=Game.getIntWithin(0, unimproved.size(), sc, this);
            if(number!=0)
            {   ((Property)unimproved.get(number-1)).isMortgaged=true;
                this.mortgaged.add((Property)unimproved.get(number-1));

                this.setBalance(((Property)unimproved.get(number-1)).getPrice()/2);

                this.unimproved.remove(number-1);

                System.out.println("You have mortgaged: "+(Property)mortgaged.getLast());
                System.out.println("Your current balance is "+this.getBalance());
            }
        }
    }
    
    public void trade(LinkedList<Player> players)
    {
    
    }
    

    @Override
    public int compareTo(Object t) 
    {
        Player p=(Player)t;
        if (this.turn<p.turn)
            return -1;
        else if (this.turn==p.turn)
            return 0;
        else
            return 1;
    }
    
}
