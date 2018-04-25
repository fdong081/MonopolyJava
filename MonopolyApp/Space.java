
package MonopolyApp;

public class Space 
{
   public int index;
   public String name;
   public String lname;
   public StringBuilder token;
   
   Space(int i, String n, String ln)
   {
       index=i;
       name=n;
       lname=ln;
       String s="";
       for(int x=0;x<Game.numberOfPlayer;x++)
       {
           s+="-";
       }
           
       token=new StringBuilder(s);
   }
   int getIndex()
   {
       return index;
   }
   @Override
   public String toString()
   {
   return "index: "+index+"\nName: "+name+"\nLast name: "+lname;//+"\n"+token;
   }

 
}

