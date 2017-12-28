package com.company.DesignMode.AdapterDesign;

/**
 * Created by eli9 on 8/28/2017.
 */
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String audioType, String fileName) {
        System.out.println("Playing Vlc file. Type: "+audioType + " Name: " + fileName);
    }

    @Override
    public void playMp4(String audioType, String fileName) {
        //System.out.println("Playing Mp4 file. Type: "+audioType + " Name: " + fileName);
    }
}
