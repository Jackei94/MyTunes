/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import be.Playlist;
import gui.model.SongModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tramm
 */
public class PlaylistNewController implements Initializable
{
   @FXML
   private TextField txtPL;
   @FXML
   private Button saveBtnPL;
   @FXML
   private Button cancelBtnPL;
   
   private SongModel sm;
   /**
    * Initializes the controller class.
    */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try {
            sm = SongModel.getInstance();
        } catch (IOException ex) {
            
        } catch (Exception ex) 
    {
        Logger.getLogger(PlaylistNewController.class.getName()).log(Level.SEVERE, null, ex);
    }
//        if (!sm.getSelectedPlaylist().isEmpty()) {
  //          txtPL.setText(sm.getSelectedPlaylist().get(0).getName());
//    }    
    }
    /*
    * Edit an existing playlist or saves a new one to the database
    */
    @FXML
    private void savePlaylist(ActionEvent event)
    {
        if (!sm.getSelectedPlaylist().isEmpty()) { 
            Playlist playlist = new Playlist();
            playlist.setName(txtPL.getText());
            playlist.setID(sm.getSelectedPlaylist().get(0).getID());
            sm.edit(playlist);
            sm.getSelectedPlaylist().clear();
            sm.loadSongsInPlaylist();
            
        } else {
            
            Playlist playlist = new Playlist();
            playlist.setName(txtPL.getText());
            playlist.setID(-1);
            
            sm.addPlaylist(playlist);
        }
        Stage stage = (Stage) saveBtnPL.getScene().getWindow();
        stage.close();
    }

    /*
    * Closes the playlistview
    */
    @FXML
    private void cancelPlaylist(ActionEvent event)
    {
        //sm.getSelectedPlaylist().clear();
        
        Stage stage = (Stage) cancelBtnPL.getScene().getWindow();
        stage.close();
    }
    
    /*
    * sets the model to MainViewModel
    */
    public void setModel(SongModel model) {
        this.sm = model;
    }
   
}
