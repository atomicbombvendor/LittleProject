package com.company.DesignMode.AdapterDesign;

/**
 * Created by eli9 on 8/28/2017.
 */
public class AudioPlayer implements MediaPlay {

    //to implement function play
    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("MP3")){
            System.out.println("Play ma3 file. Name: "+fileName);
        }else if(audioType.equalsIgnoreCase("VLC")|| audioType.equalsIgnoreCase("mp4")){
           MediaPlay adapter = new MediaAdapter(audioType);
           adapter.play(audioType, fileName);
        }else{
            System.out.println("Invalid media." + audioType + " format not supported");
        }
    }
}
