package multithreading;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by eli9 on 3/21/2017.
 */
public class MuchThreadDown {

    private String path = "http://117.169.69.238/mp3.9ku.com/m4a/186947.m4a";
    private String targetFilePath = "/"; //download path
    private int threadCount = 3;

    /**
     * @param path
     * @param targetFilePath
     * @param threadCount
     */
    public MuchThreadDown(String path, String targetFilePath, int threadCount){
        this.path = path;
        this.targetFilePath = targetFilePath;
        this.threadCount = threadCount;
    }

    /**
     *
     */
    public void download() throws Exception{
        //connect to resource
        URL url = new URL(path);

        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10000);

        int code = connection.getResponseCode();
        if(code == 200){
            // get target file's size
            int connectionLength = connection.getContentLength();
            System.out.println(connectionLength);

            //Create a same size placeholder file as resource file
            RandomAccessFile randomAccessFile = new RandomAccessFile(new File(targetFilePath, getFileName(url)), "rw");
            randomAccessFile.setLength(connectionLength);

            int blockSize = connectionLength/threadCount;//the amount of every thread to download
            for(int threadId = 0; threadId < threadCount; threadId++){
                int startIndex = threadId*blockSize;
                int endIndex = (threadId+1)*blockSize-1;
                if(threadId == threadCount - 1){
                    endIndex = connectionLength-1;
                }
                new DownloadThread(threadId, startIndex, endIndex).start();//Start to download
            }
        }
    }

    private class DownloadThread extends Thread{
        private int threadId;
        private int startIndex;
        private int endIndex;

        public DownloadThread(int threadId, int startIndex, int endIndex){
            this.threadId = threadId;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public void run(){
            System.out.println("Thread " + threadId + " start download");

            try {
                URL url = new URL(path);

                File downThreadFile = new File(targetFilePath, "downThread_" + threadId + ".dt");
                
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    private String getFileName(URL url){
        String filename = url.getFile();
        return filename.substring(filename.lastIndexOf("/")+1);
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
