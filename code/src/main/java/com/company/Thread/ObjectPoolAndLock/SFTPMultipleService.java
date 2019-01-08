package com.company.Thread.ObjectPoolAndLock;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class SFTPMultipleService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SFTPMultipleService.class);

    private static final Integer CTRL_AND_ZIP_IS_DOUBLE = 2;
    private static final String ZIP_POST_SUFFIX = ".zip";
    private static final String CTRL_POST_SUFFIX = ".crtl";
    private static final String DEFAULT_LOCAL_FILE_PATH = ".crtl";

    public List<String> getUploadedFiles(List<String> localPathNeedUpload){

        int threadNum = localPathNeedUpload.size() > 100 ? 10: 6;
        GenericObjectPool<SFTPService> pool = getObjectPool(threadNum);
        ExecutorService executor = Executors.newFixedThreadPool(localPathNeedUpload.size() > 100 ? 10: 6);
        CompletionService<String> completionService =
            new ExecutorCompletionService<>(executor);

        for (String aPathNeedUpload : localPathNeedUpload) {
            String zipFile = aPathNeedUpload.concat(ZIP_POST_SUFFIX);
            completionService.submit(getTask(pool, zipFile));

            String ctrlFile = aPathNeedUpload.concat(CTRL_POST_SUFFIX);
            completionService.submit(getTask(pool, ctrlFile));
        }

        executor.shutdown();
        return collectResult(localPathNeedUpload, completionService);
    }

    private Callable<String> getTask(GenericObjectPool<SFTPService> pool, String fileNeedUpload){
        String ftpFilePath = getFTPFileName(fileNeedUpload);
        return () -> {
            SFTPService sftpService = pool.borrowObject();
            String result = sftpService.sendLocalFile(fileNeedUpload, ftpFilePath);
            pool.returnObject(sftpService);
            return result;
        };
    }

    private String getFTPFileName(String fileNeedUpload){

        File file = new File(fileNeedUpload);
        String fileName = file.getName();
        String ftpFolder = Paths.get(fileNeedUpload).getParent().toString().replace(DEFAULT_LOCAL_FILE_PATH, "")
                .replace("\\", "/");

        return ftpFolder + "/" + fileName;
    }


    private List<String> collectResult(List<String> pathNeedUpload, CompletionService<String> completionService){
        List<String> result = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < pathNeedUpload.size() * CTRL_AND_ZIP_IS_DOUBLE; i++) {
            try {
                result.add(completionService.take().get());
            }catch (InterruptedException | ExecutionException e) {
                logger.error("multiple upload ftp file error. error_message={}", e);
            }
        }

        return result.stream().filter(Objects::nonNull)
                     .map(r -> r.substring(0, r.lastIndexOf(".")))
                     .distinct().collect(Collectors.toList());
    }

    private GenericObjectPool<SFTPService> getObjectPool(int threadNum) {
        SFTPPoolableObjectFactory factory = new SFTPPoolableObjectFactory();

        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(threadNum);
        config.setMaxIdle(threadNum);

        return new GenericObjectPool<>(
            factory, config
        );
    }
}

class SFTPPoolableObjectFactory implements PooledObjectFactory<SFTPService> {

    @Override
    public PooledObject<SFTPService> makeObject() {

        SFTPService service = new SFTPService();
        return new DefaultPooledObject<>(service);
    }

    @Override
    public void destroyObject(PooledObject<SFTPService> p) {
        p.getObject().closeChannel();
    }

    @Override
    public boolean validateObject(PooledObject<SFTPService> p) {

        return p.getObject().isConnect();
    }

    @Override
    public void activateObject(PooledObject<SFTPService> p) {
        p.getObject().connect();
    }

    @Override
    public void passivateObject(PooledObject<SFTPService> p) {

    }
}
