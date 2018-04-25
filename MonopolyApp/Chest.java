/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolyApp;


public class Chest
{
  
    
       public int index;
       public String label;
       Chest(int a, String b)
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

    
