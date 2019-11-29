package gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import be.Songs;
import gui.model.SongModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 *
 * @author Jacob
 */
public class AppController implements Initializable
{

    @FXML
    private TextField search;
    @FXML
    private ImageView reversebutton;
    @FXML
    private ImageView playbutton;
    @FXML
    private ImageView forwardbutton;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Label currentSong;
    @FXML
    private ListView<?> playlists;
    @FXML
    private Button playlistsNew;
    @FXML
    private Button playlistsEdit;
    @FXML
    private Button playlistsDelete;
    @FXML
    private ListView<?> playlistSongs;
    @FXML
    private Button playlistSongsUp;
    @FXML
    private Button playlistSongsDown;
    @FXML
    private Button playlistSongsDelete;
    @FXML
    private Button songToPlaylistMove;
    @FXML
    private Button songsNew;
    @FXML
    private Button songsEdit;
    @FXML
    private Button songsDelete;
    @FXML
    private Button close;
    @FXML
    private TableView<Songs> allSongs;
    @FXML
    private TableColumn<Songs, String> songName;
    @FXML
    private TableColumn<Songs, String> songArtist;
    @FXML
    private TableColumn<Songs, String> category;
    @FXML
    private TableColumn<Songs, Double> time;
    
    private SongModel songModel;
    private MediaPlayer mediaPlay;
    
        
    public void songsEditButton(ActionEvent event) throws IOException
    {
        Parent loader = FXMLLoader.load(getClass().getResource("SongsEdit.fxml"));

        Scene scene = new Scene(loader);

        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }
    
    public void songsNewButton(ActionEvent event) throws IOException
    {
        Parent loader = FXMLLoader.load(getClass().getResource("SongsNew.fxml"));

        Scene scene = new Scene(loader);
        
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        songName.setCellValueFactory(new PropertyValueFactory<Songs, String>("songName"));
        songArtist.setCellValueFactory(new PropertyValueFactory<Songs, String>("songArtist"));
        category.setCellValueFactory(new PropertyValueFactory<Songs, String>("category"));
        time.setCellValueFactory(new PropertyValueFactory<Songs, Double>("time"));
        try
        {
            songModel = new SongModel();
            allSongs.setItems(songModel.getAllSongs());
        } catch (Exception ex)
        {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mousePressedPlay()
    {
        String bip = "file:/C:/Users/Jacob/Documents/GitHub/MyTunes/MyTunes/Music/Song1.mp3";
        Media hit = new Media(bip);
        mediaPlay = new MediaPlayer(hit);
        mediaPlay.play();
    }

    public void mousePressedNext()
    {
        
    }

    public void mousePressedPrevious()
    {
        
    }
    
}
