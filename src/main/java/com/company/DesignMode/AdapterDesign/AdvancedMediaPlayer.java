package com.company.DesignMode.AdapterDesign;

/**
 * Created by atomic on 8/28/2017.
 */
public interface AdvancedMediaPlayer {
    void playVlc(String audioType, String fileName);
    void playMp4(String audioType, String fileName);
}
