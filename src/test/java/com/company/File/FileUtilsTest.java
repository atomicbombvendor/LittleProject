package com.company.File;

import org.junit.Test;

/**
 * Created by eli9 on 11/23/2017.
 */
public class FileUtilsTest {
    @Test
    public void test_processStr1() throws Exception {
        String s1 = "284047GY7\t101\t2017-11-21\t67.52\t\t\t\t2017-11-22 01:50:08";
        String s2 = "284183BQ2\t101\t2017-11-21\t100.03\t2.65625\t3.74888\t0.0271122262295\t2017-11-22 01:50:10";
        String s3 = "284183BQ2\t101\t2017-11-21\t100.03\t2.65625\t3.74888\t0.4271162262295\t2017-11-22 01:50:10";
        System.out.println(FileUtils.processStr1(s1));
        System.out.println(FileUtils.processStr1(s2));
        System.out.println(FileUtils.processStr1(s3));
    }

    @Test
    public void test_processPortfolioFile() throws Exception {
        String path = "D:\\Work\\Feed\\Compare\\Dev\\Portfolio\\Portfolio_20171122070251_Dev.txt";
        String target = "D:\\Work\\Feed\\Compare\\Dev\\Portfolio\\target_Portfolio_20171122070251_Dev.txt";
        FileUtils.writeFile(target, FileUtils.getFileContent(path));
    }
}