package com.company.DesignMode.AdapterDesign;

/**
 * Created by atomic on 8/28/2017.
 */
public class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String audioType, String fileName) {
        //nothing
    }

    @Override
    public void playMp4(String audioType, String fileName) {
        System.out.println("Playing Mp4 file. Type: "+audioType + " Name: " + fileName);
    }
}
