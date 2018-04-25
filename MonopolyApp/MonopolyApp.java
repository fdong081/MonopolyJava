
package MonopolyApp;


import java.util.Scanner;
import static MonopolyApp.Game.*;

public class MonopolyApp 
{

    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);
        
       
        Game g=new Game();
        
        Game.displayBoard();
        
        boolean endTurn=false;
        boolean hasWinner=false;
        int choice;
        while(!hasWinner)
        {
            for(Player p: players)
            {
               
                p.move(g);
                
                while(endTurn==false && p.isOut==false)
                {
                        System.out.println(p.token+" Please choose: ");
                        g.displayMenu();
                        AI.action="mainmenu";
                        choice=Game.getIntWithin(1, 6, sc, p);
                        
                        if(choice==6)
                            p.trade(players);
                        else if(choice==2)
                            p.sell();
                        else if(choice==3)
                            p.build();
                        else if(choice==4)
                            p.mortgage();
                        else if (choice==5)
                            p.liftMortgage();
                        else//choice==1
                            endTurn=true;
                }
                endTurn=false;
                
                if(p.isOut)
                    players.remove(p);
                
                if(players.size()==1)
                {
                    hasWinner=true;
                    break;
                }
            }
            
        }
        System.out.println("Winner is "+players.get(0).token);
    }
}
