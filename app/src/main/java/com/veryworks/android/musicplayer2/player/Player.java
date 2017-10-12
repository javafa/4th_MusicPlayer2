package com.veryworks.android.musicplayer2.player;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

/**
 * Created by pc on 10/12/2017.
 */

public class Player {
    //Singleton
    private static Player instance = new Player();
    private Player(){ }
    public static Player getInstance(){
        if(instance == null)
            instance = new Player();
        return instance;
    }

    private MediaPlayer player = null;
    private boolean loop = false;

    // 음원 세팅
    public void set(Context context, Uri musicUri){
        if(player != null) {
            player.release();
            player = null;
        }
        player = MediaPlayer.create(context, musicUri);
        player.setLooping(loop);
    }

    public void start(){
        if(player != null)
            player.start();
    }

    public void pause(){
        if(player != null)
            player.pause();
    }

    public void stop(){
        if(player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }
}
