package com.java.system.logiccode;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MediaAlert {//������Ч����
    Player player;
    File music;
    //���췽��  ������һ��.mp3��Ƶ�ļ�
    public MediaAlert(File file) {
        this.music = file;
    }
    //���ŷ���
    public void play() throws FileNotFoundException, JavaLayerException {
            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
            player = new Player(buffer);
            player.play();
    }
}