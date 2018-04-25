/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MonopolyApp;

import java.util.LinkedList;

public class ChestDeck 
{

    public LinkedList <Chest> cards;
    public LinkedList <Chest> usedCards;
    private int cardIndex;
    ChestDeck()
    {
        usedCards= new LinkedList<>();
        cards= new LinkedList<>();
        
        cards.add(new Chest(1, " Advance to Go"));
        cards.add(new Chest(2, " Bank erro in you favor(Collect $200)"));
        cards.add(new Chest(3, " Doctor's fee $50"));
        cards.add(new Chest(4, " From sale of stock, you get $50"));
        cards.add(new Chest(5, " Get out of jail free"));
        cards.add(new Chest(6, " Go to jail"));
        cards.add(new Chest(7, " Grand opera night collect $50"));
        cards.add(new Chest(8, " Holiday fund matures, collect $100"));
        cards.add(new Chest(9, " Income tax refund, collect $20"));
        cards.add(new Chest(10, " It's yoour birthday. collect $10 from each player"));
        cards.add(new Chest(11, " Lift insurance matures, collect $100"));
        cards.add(new Chest(12, " Pay hospital fee $100"));
        cards.add(new Chest(13, " pay school fee $150"));
        cards.add(new Chest(14, " Receive $25 consultancy fee."));
        cards.add(new Chest(15, " You are assessed for street repairs($40 house; $115 hotel)"));
        cards.add(new Chest(16, " YOu have won second prize in a beauty contest, collect $10"));
        cards.add(new Chest(17, " You inherit $100"));
        
        shuffle();
       
    }
    public void getChest()
        {
            if(cards.size()==0)
                shuffle();
            System.out.println(cards.get(0));
            cardIndex=cards.get(0).getIndex();
            cards.remove(0);
            usedCards.add(cards.get(0));
           
            
        }
    private void shuffle()
    {
        int index1;
        int index2;
        for(int i=0;i<17;i++)
        {
            index1=1+(int) (Math.random()*17);
            index2=1+(int) (Math.random()*17);
            Chest temp;
            temp=cards.get(index1-1);
            cards.add(index1-1, cards.get(index2-1));
            cards.add(index2-1, temp);
            
        }
    }
    public int getCardIndex()
    {
     return cardIndex;
    }
    
}
