/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import be.Songs;
import bll.BLLException;
import dal.DalException;
import gui.model.SongModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jacob
 */
public class SongsNewController implements Initializable
{

   ObservableList<String> newCategoryList = FXCollections.observableArrayList("Pop", "Rock", "Dubstep");

   @FXML
   private Button newCancel;
   @FXML
   private TextField newTitle;
   @FXML
   private TextField newArtist;
   @FXML
   private TextField newTime;
   @FXML
   private Button newSave;
   @FXML
   private TextField newCategory;
   @FXML
   private TextField newFile;

   private SongModel songModel;

   /**
    * Initializes the controller class.
    */
   @Override
   public void initialize(URL url, ResourceBundle rb)
   {
      try
      {
         songModel = new SongModel();
      } 
      catch (Exception ex)
      {
         Logger.getLogger(SongsNewController.class.getName()).log(Level.SEVERE, null, ex);
      }

//        try {
//            songModel = SongModel.getInstance();
//        } catch (Exception ex)
//        {
//            Logger.getLogger(SongsNewController.class.getName()).log(Level.SEVERE, null, ex);
//        }        if(!songModel.getSelectedSong().isEmpty()) {
//        newTitle.setText(songModel.getSelectedSong().get(0).getSongName());
//        newArtist.setText(songModel.getSelectedSong().get(0).getSongArtist());
//        newCategory.setText(songModel.getSelectedSong().get(0).getCategory());
//        newFile.setText(songModel.getSelectedSong().get(0).getFilePath());
//        newTime.setText(Double.toString(songModel.getSelectedSong().get(0).getTime()));
//        }
   }
//        newCategory.setValue("Pop");
//        newCategory.setItems(newCategoryList);

   @FXML
   private void newCancelButton(ActionEvent event)
   {
      Stage stage = (Stage) newCancel.getScene().getWindow();
      stage.close();
   }

   @FXML
   private void newSaveButton(ActionEvent event) throws DalException, BLLException
   {
//        if(!songModel.getSelectedSong().isEmpty())
//        {
//            Songs song = new Songs();
//            song.setSongName(newTitle.getText());
//            song.setSongArtist(newArtist.getText());
//            song.setCategory(newCategory.getText());
//            song.setFilePath(newFile.getText().trim());
//            song.setTime(Double.parseDouble(newTime.getText()));
//            song.setId(songModel.getSelectedSong().get(0).getId());
//            songModel.edit(song);
//            songModel.getSelectedSong().clear();
//        }
//        else
//        {
      Songs songs = new Songs();
//            songs.setId(-1);
      songs.setSongName(newTitle.getText());
      songs.setSongArtist(newArtist.getText());
      songs.setTime(Integer.parseInt(newTime.getText()));
      songs.setCategory(newCategory.getText());
      songs.setFilePath(newFile.getText());

      songModel.createSongs(songs);
      Stage stage = (Stage) newSave.getScene().getWindow();
      stage.close();
   }
   
}
