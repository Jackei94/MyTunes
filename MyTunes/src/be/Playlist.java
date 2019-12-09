/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tramm
 */
public class Playlist
{

    
    public String plName;
    private int PlaylistId;
    private final List<Songs> songList;
    
    // Constructer for Playlist
    public Playlist() {
        this.songList = new ArrayList();
    }
    
    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getPlaylistId() {
        return PlaylistId;
    }
    
    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setPlaylistId(int PlaylistId) {
        this.PlaylistId = PlaylistId;
    }
    
    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getPlName() {
        return plName;
    }
    
    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setPlName(String plName) {
        this.plName = plName;
    }
    
    /*
     * returns songList
     */
    public List<Songs> getSongList() {
        return songList;
    }
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Playlist{" + "name=" + plName + ", id=" + PlaylistId + '}';
    }
}
