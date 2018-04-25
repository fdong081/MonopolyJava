/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MonopolyApp;

import java.util.LinkedList;


public class Property extends Space
{
    String lastName;
    public boolean isOwned;
    public boolean isMortgaged=false;
    private Player owner;
    private int currentRent;
    public LinkedList<Integer> rentlist;
    public int price;
    public String type;
    
    Property(int i, String n,  String lname, String t)
    {
        super(i, n, lname);
        this.type=t;
        this.rentlist = new LinkedList<>();
        isOwned=false;
        isMortgaged=false;
        owner=null;

    }  

 
    public void setOwner(Player p)
    {
         this.owner=p;
    }
     public Player getOwner()
    {
         return owner;
    }
    public int getPrice()
    {
       return price;
    }
}

class Color extends Property
{

    public int house;
    public int hotel;
    
    public int houseCost;
    public boolean isMonopoly;
    
    
    public Color(int i, String n,String ln, String t) 
    {
        super(i, n, ln,t);
        house=0;
        hotel=0;
        setValues();
        isMonopoly=false;
    }
    
    public void setValues()
    {
        switch(this.index)
        {
            case 1:
                {
                    rentlist.add(2);
                    rentlist.add(10);
                    rentlist.add(30);
                    rentlist.add(90);
                    rentlist.add(160);
                    rentlist.add(250);
                    this.price=60;
                    this.houseCost=50;
                }
                break;
            case 3:
                {
                    rentlist.add(4);
                    rentlist.add(20);
                    rentlist.add(60);
                    rentlist.add(180);
                    rentlist.add(320);
                    rentlist.add(450);
                    this.price=60;
                    this.houseCost=50;
                }
                break;
            case 6:
            case 8:
                {
                    rentlist.add(6);
                    rentlist.add(30);
                    rentlist.add(90);
                    rentlist.add(270);
                    rentlist.add(400);
                    rentlist.add(550);
                    this.price=100;
                    this.houseCost=50;
                }
                break;
            case 9:
                {
                     rentlist.add(8);
                    rentlist.add(40);
                    rentlist.add(100);
                    rentlist.add(300);
                    rentlist.add(450);
                    rentlist.add(600);
                    this.price=120;
                    this.houseCost=50;
                }
                break;
            case 11:
            case 13:
                {
                     rentlist.add(10);
                    rentlist.add(50);
                    rentlist.add(150);
                    rentlist.add(450);
                    rentlist.add(625);
                    rentlist.add(750);
                    this.price=140;
                    this.houseCost=100;
                }
                break;
            case 14:
                {
                     rentlist.add(12);
                    rentlist.add(60);
                    rentlist.add(180);
                    rentlist.add(500);
                    rentlist.add(700);
                    rentlist.add(900);
                    this.price=160;
                    this.houseCost=100;
                }
                break;
            case 16:
            case 18:
                {
                     rentlist.add(14);
                    rentlist.add(70);
                    rentlist.add(200);
                    rentlist.add(350);
                    rentlist.add(750);
                    rentlist.add(950);
                    this.price=180;
                    this.houseCost=100;
                }
                break;
            case 19:
                {
                    rentlist.add(16);
                    rentlist.add(80);
                    rentlist.add(220);
                    rentlist.add(600);
                    rentlist.add(800);
                    rentlist.add(1000);
                    this.price=200;
                    this.houseCost=100;
                }
                break;
            case 21:
            case 23:
                {
                    rentlist.add(18);
                    rentlist.add(90);
                    rentlist.add(250);
                    rentlist.add(700);
                    rentlist.add(875);
                    rentlist.add(1050);
                    this.price=220;
                    this.houseCost=150;
                }
                break;
            case 24:
                {
                    rentlist.add(20);
                    rentlist.add(100);
                    rentlist.add(300);
                    rentlist.add(750);
                    rentlist.add(925);
                    rentlist.add(1100);
                    this.price=240;
                    this.houseCost=150;
                }
                break;     
            case 26:
            case 27:
                {
                    rentlist.add(22);
                    rentlist.add(110);
                    rentlist.add(330);
                    rentlist.add(800);
                    rentlist.add(975);
                    rentlist.add(1150);
                    this.price=260;
                    this.houseCost=150;
                }
                break;
            case 29:
                {
                    rentlist.add(24);
                    rentlist.add(120);
                    rentlist.add(360);
                    rentlist.add(850);
                    rentlist.add(1025);
                    rentlist.add(1200);
                    this.price=140;
                    this.houseCost=150;
                }
                break; 
            case 31:
            case 32:
                {
                    rentlist.add(26);
                    rentlist.add(130);
                    rentlist.add(390);
                    rentlist.add(900);
                    rentlist.add(1100);
                    rentlist.add(1275);
                    this.price=300;
                    this.houseCost=200;
                }
                break;    
            case 34:
                {
                    rentlist.add(28);
                    rentlist.add(150);
                    rentlist.add(450);
                    rentlist.add(1000);
                    rentlist.add(1200);
                    rentlist.add(1400);
                    this.price=320;
                    this.houseCost=200;
                }
                break; 
            case 37:
                {
                    rentlist.add(35);
                    rentlist.add(175);
                    rentlist.add(500);
                    rentlist.add(1100);
                    rentlist.add(1300);
                    rentlist.add(1500);
                    this.price=350;
                    this.houseCost=200;
                }
                break;
            case 39:
                {
                    rentlist.add(50);
                    rentlist.add(200);
                    rentlist.add(600);
                    rentlist.add(1400);
                    rentlist.add(1700);
                    rentlist.add(2000);
                    this.price=400;
                    this.houseCost=200;
                }
                break;                
        }
    }
    
    public int getCurrentRent()
    {
        if(isMonopoly && this.house==0 && this.hotel==0)
            return rentlist.get(0)*2;
        else 
            {  
               int number=this.house+this.hotel*5;
               return rentlist.get(number);
            }
    }
    
    @Override
    public String toString()
    {
        return super.toString()+"\nRent: "+getCurrentRent()+"\nPrice: "+getPrice()+"\nStatus: has owner ? "+isOwned;
    }
    

}



class Utility extends Property
{

    public int rent;
    public Utility(int i, String n,String ln, String t) 
    {
        super(i, n,ln, t);
        price=150;  
        rent=0;
    }

    
    public int getCurrentRent(int numberDice, int numberOfUtility)
    {
        if(numberOfUtility==1)
        { 
            rent=numberDice*4;
            return rent;
        }
        else //if(numberOfUtility==2)
        {
            rent=numberDice*10;
            return rent;
        }
    }
    
           
    @Override
    public String toString()
    {
        return super.toString()+"\nRent: "+rent+"\nPrice: "+getPrice()+"\nOwenr: "+"\nStatus: has owner ? "+isOwned;
    }
    
}
class Railroad extends Property
{

    public int rent=0;
    public Railroad(int i, String n,String ln, String t)
    {
        super(i, n,ln, t);
        price=200;  
    }
    
    public int getCurrentRent(int numberOfRailroad)
    {
        
        switch (numberOfRailroad)
        {
            case 1:
                rent=25;
                break;
            case 2:
                rent=50;
                break;
            case 3:
                rent=100;
                break;
            case 4:
                rent=200;
                break;
        }
        return rent;
    }
    
    @Override
    public String toString()
    {
        return super.toString()+"\nRent: "+rent+"\nPrice: "+getPrice()+"\nOwenr: "+"\nStatus: has owner ? "+isOwned ;
    }
    
}