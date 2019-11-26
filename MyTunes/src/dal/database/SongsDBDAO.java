/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.database;

import be.Songs;
import dal.DalException;
import java.util.List;

/**
 *
 * @author Tramm
 */
public class SongsDBDAO
{
   private DatabaseConnector dbCon;
   
   public SongsDBDAO() throws Exception
   {
       dbCon = new DatabaseConnector();
   }
   
    public List<Songs> getAllSongs() throws DalException
    {
        
    }
}
