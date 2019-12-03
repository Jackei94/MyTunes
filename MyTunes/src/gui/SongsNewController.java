/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import bll.BLLException;
import dal.DalException;
import gui.model.SongModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    ObservableList<String> newCategoryList = FXCollections.observableArrayList("Pop","Rock","Dubstep");

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
//        newCategory.setValue("Pop");
//        newCategory.setItems(newCategoryList);
    }    

    @FXML
    private void newCancelButton(ActionEvent event)
    {
        Stage stage = (Stage) newCancel.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void newSaveButton(ActionEvent event) throws DalException, BLLException
    {
        String songName = newTitle.getText().trim();
        String songArtist = newArtist.getText().trim();
        double time = Double.parseDouble(newTime.getText().trim());
        String category = newCategory.getText().trim();
        String filePath = newFile.getText().trim();
        songModel.createSongs(songName, songArtist, time, category, filePath);
        
//        Stage stage = (Stage) newSave.getScene().getWindow();
//        stage.close();
    }
    
}