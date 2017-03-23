package multithreading;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by eli9 on 3/21/2017.
 */
public class MuchThreadDown {

    private String path = "http://sw.bos.baidu.com/sw-search-sp/software/a818cd14ce5ee/npp_7.3.3_Installer.exe";
    private String targetFilePath = "C:\\Users\\eli9\\Desktop"; //download path
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
                }//Set start Index and end Index
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
                RandomAccessFile downThreadStream = null;
                if (downThreadFile.exists()) {
                    System.out.println("downThreadFile exists");
                    downThreadStream = new RandomAccessFile(downThreadFile, "rwd");
                    String startIndex_str = downThreadStream.readLine();
                    this.startIndex = Integer.parseInt(startIndex_str);//set download point
                    //There have a problem
                } else {
                    downThreadStream = new RandomAccessFile(downThreadFile, "rwd");
                }

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(10000);

                //set the top information/ Range:
                connection.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);
                System.out.println("Thread_" + threadId + " Start Index:" + startIndex + " End Index:" +
                        endIndex);

                if (connection.getResponseCode() == 206) {//206 Request for part resource succeed
                    InputStream inputStream = connection.getInputStream();
                    RandomAccessFile randomAccessFile = new RandomAccessFile(
                            new File(targetFilePath, getFileName(url)), "rw");//get file that had been created
                    randomAccessFile.seek(startIndex);

                    /**
                     * write net stream to local file
                     */
                    byte[] buffer = new byte[1024];
                    int length = -1;
                    int total = 0;//Record size that this time download
                    while ((length = inputStream.read(buffer)) > 0) {
                        randomAccessFile.write(buffer, 0, length);
                        total += length;
                        /**
                         * keep current position to file
                         */
                        downThreadStream.seek(0);
                        downThreadStream.write((startIndex + total + "").getBytes("UTF-8"));
                    }

                    downThreadStream.close();
                    inputStream.close();
                    randomAccessFile.close();
                    cleanTemp(downThreadFile);
                    System.out.println("Thread " + threadId + "end");
                } else {
                    System.out.println("Response code is " + connection.getResponseCode() + ". Server is not support " +
                            "for much thread");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getFileName(URL url){
        String filename = url.getFile();
        return filename.substring(filename.lastIndexOf("/")+1);
    }

    private synchronized void cleanTemp(File file){
        file.delete();
    }

    public static void main(String[] args) {
        String path = "http://117.169.69.238/mp3.9ku.com/m4a/186947.m4a";
        String path2 = "http://sc1.111ttt.com/2015/1/01/03/94030951030.mp3";
        String notpad = "http://sw.bos.baidu.com/sw-search-sp/software/a818cd14ce5ee/npp_7.3.3_Installer.exe";
        String targetFilePath = "C:\\Users\\eli9\\Desktop"; //download path
        int threadCount = 3;
        System.out.println("Hello World!");
        try {
            new MuchThreadDown(notpad,targetFilePath,threadCount).download();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
