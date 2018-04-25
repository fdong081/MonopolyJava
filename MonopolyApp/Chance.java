
package MonopolyApp;

class Chance
{
       public int index;
       public String label;
       Chance(int a, String b)
       {
           index=a;
           label=b;
       }
       public int getIndex()
       {
           return index;
       }
       @Override
       public String toString()
       {
           return index+label;
       }
       
}
