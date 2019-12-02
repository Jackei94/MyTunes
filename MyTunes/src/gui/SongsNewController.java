/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import be.Songs;
import dal.DalException;
import dal.database.DatabaseConnector;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jacob
 */
public class SongsNewController implements Initializable
{

    @FXML
    private Button newCancel;
   @FXML
   private TextField newTitle;
   @FXML
   private TextField newArtist;
   @FXML
   private TextField newTime;
   @FXML
   private TextField NewFile;
   @FXML
   private Button saveButton;
   @FXML
   private TextField newCategory;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void newCancelButton(ActionEvent event)
    {
        Stage stage = (Stage) newCancel.getScene().getWindow();
        stage.close();
    }
private DatabaseConnector dbCon;
   @FXML
   private void newSaveButten(ActionEvent event) throws DalException
   {
      {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "INSERT INTO Songs VALUES (?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            String songName = newTitle.getText();
            String songArtist = newArtist.getText();
           // double time = newTime.getText();
            String category = newCategory.getText();
           String filePath = NewFile.getText();
            ps.setString(1, songName);
            ps.setString(2, songArtist);
           // ps.setDouble(3, time);
              ps.setString(4, category);
            ps.setString(5, filePath);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next());
                
                
            }
            throw new DalException();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException();
        }
    }
     
      
   }
   
    
    
}
