package com.company.DesignMode.AdapterDesign;

/**
 * Created by eli9 on 8/28/2017.
 */
public class MediaAdapter implements MediaPlay {

    AdvancedMediaPlayer aplayer;

    public MediaAdapter(String audioType) {
        if(audioType.equalsIgnoreCase("VLC")){
            this.aplayer = new VlcPlayer();
        }else{
            this.aplayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName){
        if(audioType.equalsIgnoreCase("vlc")){
            aplayer.playVlc(audioType, fileName);
        }else if(audioType.equalsIgnoreCase("mp4")){
            aplayer.playMp4(audioType, fileName);
        }
    }
}
