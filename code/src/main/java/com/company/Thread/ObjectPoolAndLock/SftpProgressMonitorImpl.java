package com.company.Thread.ObjectPoolAndLock;

import com.jcraft.jsch.SftpProgressMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SftpProgressMonitorImpl implements SftpProgressMonitor {
    private static final Logger logger = LoggerFactory.getLogger(SftpProgressMonitorImpl.class);
    /** current transferred **/
    private long transferred = 0;
    private String src;
    private String dest;
    /** a code indicating the direction of transfer, one of PUT and GET **/
    private int op;
    /** length of file to transfer **/
    private long max;

    @Override
    public void init(int op, String src, String dest, long max) {
        this.src = src;
        this.dest = dest;
        this.op = op;
        this.max = max;
    }

    @Override
    public boolean count(long count) {
        transferred += count;
        return true;
    }

    @Override
    public void end() {
//        logger.info("end transfer");
    }

    public String getSrc() {

        return src;
    }

    public void setSrc(String src) {

        this.src = src;
    }

    public String getDest() {

        return dest;
    }

    public void setDest(String dest) {

        this.dest = dest;
    }

    public int getOp() {

        return op;
    }

    public void setOp(int op) {

        this.op = op;
    }

    public long getMax() {

        return max;
    }

    public void setMax(long max) {

        this.max = max;
    }

    public long getTransferred() {

        return transferred;
    }

    public void setTransferred(long transferred) {

        this.transferred = transferred;
    }
}
