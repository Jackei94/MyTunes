/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be;

/**
 *
 * @author Tramm
 */
public class Songs
{
   private int id;
   private String title;
   private double length;

   public Songs(int id, String title, double length)
   {
      this.id = id;
      this.title = title;
      this.length = length;
   }

   public int getId()
   {
      return id;
   }

   public void setId(int id)
   {
      this.id = id;
   }

   public String getTitle()
   {
      return title;
   }

   public void setTitle(String title)
   {
      this.title = title;
   }

   public double getLength()
   {
      return length;
   }

   public void setLength(double length)
   {
      this.length = length;
   }
   
   
}
