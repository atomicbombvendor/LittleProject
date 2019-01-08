package com.company.Thread.ObjectPoolAndLock;

import com.company.Thread.TestFuture.RetriableException;
import com.company.Thread.TestFuture.RetryPolicy;
import com.jcraft.jsch.*;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class SFTPService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(SFTPService.class);

    private static final String ftpServer = "";
    private static final String ftpUsername = "";
    private static final String ftpPassword = "";
    private Session session;
    private ChannelSftp channel;
    private JSch jsch = new JSch();

    public boolean connect() {

        try {
            session = jsch.getSession(
                ftpUsername,
                ftpServer,
                22
                                     );
            session.setPassword(ftpPassword);
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setTimeout(30 * 1000);
            session.connect();
            channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();
            return true;
        } catch (JSchException e) {
            log.error("SFTP connect failed error_message={}", e);
            return false;
        }
    }

    /**
     * Send sftp file
     *
     * @param local X:\\A\B\C\D.zip
     * @param dest  ftp path: /A/B/C/D.zip
     */
    public String sendLocalFile(String local, String dest) {

        log.info("Start to send file to SFTP. local={} dest={}", local, dest);

        if (!new File(local).exists()){
            System.out.println(local + " is not exists");
            return null;
        }

        try {
            SyncCreateDir.createDirWhenNotExist(channel, Paths.get(dest).getParent().toString().replace("\\", "/"));
            RetryPolicy.get(3, true)
                       .execute(() -> sendLocalFileFunction(local, dest), null);
        }catch (Exception ftpExcetion){
            log.error("create ftp dir unknown error. local={} dest={} error_message={}", local, dest, ftpExcetion);
            return null;
        }
        return local;
    }

    /**
     * Send sftp file
     *
     * @param local local path: X:\\A\B\C\D.zip
     * @param dest  ftp path: /A/B/C/D.zip
     */
    public void sendLocalFileFunction(String local, String dest) {

        SftpProgressMonitorImpl monitor = new SftpProgressMonitorImpl();
        if (!isConnect()) {
            log.error("SFTP not connected cannot send. Connect again");
            connect();
        }

        try {
            channel.put(local, dest, monitor, ChannelSftp.OVERWRITE);
        } catch (SftpException e) {
            log.error("send sftp fail. local={} dest={} error_message={}", local, dest, e);
            throw new RetriableException("Send SFTP file error for IOException", e);
        }
    }

    public void closeChannel() {

        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }

    public boolean isConnect(){
        if (channel == null) {
            return false;
        }
        if (session == null) {
            return false;
        }
        return true;
    }
}

class SyncCreateDir {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(SyncCreateDir.class);

    public static void createDirWhenNotExist(ChannelSftp channel, String directory) throws Exception {

        if (isDirExists(directory, channel)) {
            return;
        }
        log.info("create ftp folder " + directory);
        String[] pathArray = directory.split("/");
        try {
            for (String path : pathArray) {
                if (path.isEmpty()) {
                    continue;
                }

                if (isDirExists(path, channel)) {
                    channel.cd(path);
                } else {
                    synchronized (SyncCreateDir.class) {
                        if (!isDirExists(path, channel)) {
                            channel.mkdir(path);
                            channel.cd(path);
                        }
                    }
                }
            }
        } catch (SftpException e) {
            if (e.getMessage().toLowerCase().contains("file already exists")) {
                log.info("folder " + directory + " already exists");
            }else {
                cdHome(channel);
                throw new Exception("create " + directory + " error. error_message={}",
                        e);
            }
        }
        cdHome(channel);
    }

    private static boolean isDirExists(String directory, ChannelSftp channelInput){
        try {
            SftpATTRS sftpATTRS = channelInput.lstat(directory);
            return sftpATTRS.isDir();
        } catch (SftpException e) {
            log.info(directory + " is not a folder or is not exist");
        }
        return false;
    }

    private static void cdHome(ChannelSftp channelInput) throws SftpException {
        channelInput.cd(channelInput.getHome());
    }
}
