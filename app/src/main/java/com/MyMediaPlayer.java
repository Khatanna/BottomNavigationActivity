package com;

import android.media.MediaPlayer;

public class MyMediaPlayer {
    private MyMediaPlayer() {}
    private static MediaPlayer instance;

    public static MediaPlayer getInstance() {
        if(instance == null) {
            instance = new MediaPlayer();
        }

        return instance;
    }

    public static int currentIndex = -1;
}
