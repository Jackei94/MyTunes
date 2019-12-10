package gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import be.Playlist;
import be.Songs;
import bll.PlaylistManager;
import bll.SongManager;
import dal.DalException;
import gui.model.PlaylistModel;
import gui.model.SongModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Jacob
 */
public class AppController implements Initializable
{
    private SongModel songModel;
    private PlaylistModel playlistModel;
    private final ObservableList<Songs> searchedSongs;
    
    private final SongManager songManager;
    private final PlaylistManager playlistManager;
    private final ToggleButton playButton = new ToggleButton("Play");
    private double currentVolume;

    @FXML
    private TextField txtSearch;
    @FXML
    private ImageView reversebutton;
    @FXML
    private ImageView playbutton;
    @FXML
    private ImageView forwardbutton;
    @FXML
    private Label currentSong;
    @FXML
    private TableView<Playlist> playlists;
    @FXML
    private TableColumn<Playlist, String> playlistsName;
    @FXML
    private TableColumn<Playlist, Integer> playlistsSongs;
    @FXML
    private TableColumn<Playlist, Integer> playlistsTime;
    @FXML
    private Button playlistsNew;
    @FXML
    private Button playlistsEdit;
    @FXML
    private Button playlistsDelete;
    @FXML
   private TableView<Playlist> songPL;
    @FXML
    private TableColumn<Playlist, String> playlistSongs;
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
    private TableColumn<Songs, Integer> time;
    @FXML
    private Slider volumeSlider;
   

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            songModel = SongModel.getInstance();
            playlistModel = PlaylistModel.getInstance();
        } catch (Exception ex)
        {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Sets the items on song table
        allSongs.setItems(songModel.getAllSongs());
        // Sets all cells to their values for song table
        songName.setCellValueFactory(new PropertyValueFactory("songName"));
        songArtist.setCellValueFactory(new PropertyValueFactory("songArtist"));
        category.setCellValueFactory(new PropertyValueFactory("category"));
        time.setCellValueFactory(new PropertyValueFactory("time"));

        // Sets the items on playlist table
        playlists.setItems(playlistModel.getAllPlaylists());
        // Sets all cells to their values for playlist table
        playlistsName.setCellValueFactory(new PropertyValueFactory("plName"));
        
        songPL.setItems(playlistModel.getAllSongsFromPlaylist());
        playlistSongs.setCellValueFactory(new PropertyValueFactory("songName"));
        
        currentVolume = .05;
        volumeSlider.setValue(currentVolume);
        
        try
        {
            // Loads all songs
            songModel.loadSongs();
            // Loads all playlists
           playlistModel.loadPlaylists();
           playlistModel.loadSongsOnPlaylist();
        } catch (DalException ex)
        {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public AppController() throws Exception
    {
        this.searchedSongs = FXCollections.observableArrayList();
        this.songManager = new SongManager();
        this.playlistManager = new PlaylistManager();
    }

    @FXML
    private void exitApp(ActionEvent event) throws IOException
    {

        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Exit", ButtonType.YES, ButtonType.NO);
        deleteAlert.setContentText("Er du helt sikker på det? ");
        deleteAlert.showAndWait();
        if (deleteAlert.getResult() == ButtonType.YES)
        {
            // get a handle to the stage
            Stage stage = (Stage) close.getScene().getWindow();
            // do what you have to do
            stage.close();

        } else
        {
            deleteAlert.close();
        }
    }

    @FXML
    public void songsNewButton(ActionEvent event) throws IOException
    {
        this.songModel = songModel;
        songModel.setNewOrEdit("New Song");

        Parent loader = FXMLLoader.load(getClass().getResource("SongsNew.fxml"));

        Scene scene = new Scene(loader);

        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void volumeSlide(MouseEvent event)
    {
        songModel.setVolume(volumeSlider);
    }

    @FXML
    private void songsEditButton(ActionEvent event) throws IOException
    {
        this.songModel = songModel;
        songModel.setNewOrEdit("Edit Song");

        Songs songs = allSongs.getSelectionModel().getSelectedItem();
        songModel.addSelectedSong(songs);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SongsNew.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        SongsNewController controller = fxmlLoader.getController();
        controller.setModel(songModel);
        Stage stage = new Stage();
        songModel.getSelectedSong().clear();

        stage.setScene(new Scene(root1));
        stage.show();
    }

    Image playImage = new Image(getClass().getResource("img/pausebutton.png").toExternalForm());
    Image pauseImage = new Image(getClass().getResource("img/playbutton.png").toExternalForm());

    @FXML
    public void mousePressedPlay()
    {
        Songs songPlay = allSongs.getSelectionModel().getSelectedItem();
        if (playButton.getText().equals("Play"))
        {
            playbutton.setImage(playImage);
            songModel.PlaySong(songPlay);
            playButton.setText("Pause");
            currentSong.setText(allSongs.getSelectionModel().getSelectedItem().getSongArtist() + " - " + allSongs.getSelectionModel().getSelectedItem().getSongName());
            songModel.getMediaPlayer().setVolume(currentVolume);

        } else if (playButton.getText().equals("Pause"))
        {
            playbutton.setImage(pauseImage);
            songModel.PauseSong(songPlay);
            playButton.setText("Play");
            songModel.setVolume(volumeSlider);
        }

       
    }

    @FXML
    public void mousePressedNext() throws DalException
    {
        songModel.getMediaPlayer().stop();
        allSongs.getSelectionModel().selectNext();
        Songs songPlay = allSongs.getSelectionModel().getSelectedItem();
        songModel.PlaySong(songPlay);
        playButton.setText("Pause");
        currentSong.setText(allSongs.getSelectionModel().getSelectedItem().getSongArtist() + " - " + allSongs.getSelectionModel().getSelectedItem().getSongName());
    }

    @FXML
    public void mousePressedPrevious()
    {
        songModel.getMediaPlayer().stop();
        allSongs.getSelectionModel().selectPrevious();
        Songs songPlay = allSongs.getSelectionModel().getSelectedItem();
        songModel.PlaySong(songPlay);
        playButton.setText("Pause");
        currentSong.setText(allSongs.getSelectionModel().getSelectedItem().getSongArtist() + " - " + allSongs.getSelectionModel().getSelectedItem().getSongName());
    }

    @FXML
    private void searchSong()
    {
        txtSearch.textProperty().addListener((observable, oldValue, newValue)
                ->
        {
            searchedSongs.setAll(songManager.search(songModel.getAllSongs(), newValue));
            allSongs.setItems(searchedSongs);
        });
    }

    @FXML
    private void songDeleteButton(ActionEvent event) throws DalException
    {
        boolean isSongOnPlaylist = false;
        Songs selectedSong
                = allSongs.getSelectionModel().getSelectedItem();

        if (isSongOnPlaylist == true)
        {
            Alert warningAlert = new Alert(Alert.AlertType.WARNING, "The song is part of a playlist. Remove the song from the playlist first");
            warningAlert.showAndWait();

        } else
        {
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Delete", ButtonType.YES, ButtonType.NO);
            deleteAlert.setContentText("Vil du VIRKELIG slette? " + selectedSong.getSongName() + "?");
            deleteAlert.showAndWait();
            if (deleteAlert.getResult() == ButtonType.YES)
            {
                songModel.deleteSong(selectedSong);
            } else
            {
                deleteAlert.close();
            }

        }

    }
/*
    * Adds the selected song to the selected playlist
    */
    @FXML
    private void addToPlaylist(ActionEvent event) {
        Songs selectedSong = allSongs.getSelectionModel().getSelectedItem();
        Playlist getSelectedPlaylist = playlists.getSelectionModel().getSelectedItem();
        getSelectedPlaylist.getSongList().add(selectedSong);
        playlistManager.addSongToPlaylist(getSelectedPlaylist, selectedSong);
       // playlistSongs.setItems(FXCollections.observableArrayList(playlists.getSelectionModel().getSelectedItem().getSongList()));
       
    }
    
    
//    @FXML
//    private void getPlaylistSong(MouseEvent event) {
//        
//        playlistSongs.setItems(FXCollections.observableArrayList(playlists.getSelectionModel().getSelectedItem().getSongList()));
//    }
    @FXML
    private void newPlaylist(ActionEvent event) throws IOException
    {
        Parent loader = FXMLLoader.load(getClass().getResource("PlaylistNew.fxml"));

        Scene root2 = new Scene(loader);

        Stage stage = new Stage();

        stage.setScene(root2);
        stage.show();
    }

//   @FXML
//   private void getPlaylistSong(SortEvent<C> event)
//   {
//   }

   @FXML
   private void getPlaylistSong(MouseEvent event)
   {
   }
}
