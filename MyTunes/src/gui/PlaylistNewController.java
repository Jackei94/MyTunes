/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import be.Playlist;
import bll.BLLException;
import dal.DalException;
import gui.model.PlaylistModel;
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
   
   
   private PlaylistModel playlistModel;
   /**
    * Initializes the controller class.
    */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try 
        {
            playlistModel = PlaylistModel.getInstance();
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(PlaylistNewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!playlistModel.getSelectedPlaylist().isEmpty())
        {
            txtPL.setText(playlistModel.getSelectedPlaylist().get(0).getPlName());
        }
    }
    
    @FXML
    private void cancelPlaylist(ActionEvent event)
    {
        playlistModel.getSelectedPlaylist().clear();
        Stage stage = (Stage) cancelBtnPL.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void savePlaylist(ActionEvent event) throws DalException, BLLException
    {
        if (!playlistModel.getSelectedPlaylist().isEmpty()) 
        { 
            Playlist playlist = new Playlist();
            playlist.setPlName(txtPL.getText());
            playlist.setId(playlistModel.getSelectedPlaylist().get(0).getId());
            playlistModel.editPlaylist(playlist);
            playlistModel.getSelectedPlaylist().clear();
        } 
        else 
        {
            Playlist playlist = new Playlist();
            playlist.setId(-1);
            playlist.setPlName(txtPL.getText());
            
            playlistModel.createPlaylist(playlist);
        }
        playlistModel.loadPlaylists();
        Stage stage = (Stage) saveBtnPL.getScene().getWindow();
        stage.close();
    }
    
    public void setModel(PlaylistModel model) 
    {
        this.playlistModel = model;
    }
   
}
