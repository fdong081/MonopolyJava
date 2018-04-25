package MonopolyApp;

import java.util.LinkedList;

public final class ChanceDeck 
{
   
    private final LinkedList <Chance> cards;
    private final LinkedList <Chance> usedCards;
    private int cardIndex;
    ChanceDeck()
    {
        cards= new LinkedList<>();
        usedCards=new LinkedList<>();
        cards.add( new Chance( 1 ," Advance to Go" ));
        cards.add( new Chance( 2 ," Advance to Illinois Ave" ));
        cards.add( new Chance( 3 ," Advance to St.Charles Place" ));
        cards.add( new Chance( 4 ," Advance to nearest Utility" ));
        cards.add( new Chance( 5 ," Advance to nearest Railroad" ));
        cards.add( new Chance( 6 ," Bank pays you 50" ));
        cards.add( new Chance( 7 ," Get out of jail free" ));
        cards.add( new Chance( 8 ," Go back 3 spaces" ));
        cards.add( new Chance( 9 ," Go to jail" ));
        cards.add( new Chance( 10 ," Make general repairs on your property(house 25; hotel 100)" ));
        cards.add( new Chance( 11 ," Pay poor tax $15" ));
        cards.add( new Chance( 12," Take a trip to Reading Railroad" ));
        cards.add( new Chance( 13," Take a walk on Boardwalk" ));
        cards.add( new Chance( 14 ," You have been elected chairman of the board(pay each player $50)" ));
        cards.add( new Chance( 15 ," Your building loan matures,collect $150" ));
        cards.add( new Chance( 16 ," You have won a crossword competition, collect $100" ));
        shuffle();
       
    }
    
     void shuffle()
    {
        for(int i=0;i<16;i++)
        {
            int index1=1+(int) (Math.random()*16);
            int index2=1+(int) (Math.random()*16);
            Chance temp;
            
            temp=cards.get(index1-1);
            cards.add(index1-1, cards.get(index2-1));
            cards.add(index2-1, temp);
            
        }
        
    }
     void getChance()
        {
            if(cards.size()==0)
                shuffle();
          
            System.out.println(cards.get(0));
            cardIndex=cards.get(0).getIndex();
            cards.remove(0);
            usedCards.add(cards.get(0));
            
            
        }
    public int getCardIndex()
    {
     return cardIndex;
    }
}