package RandomAccessFile;

import java.io.*;

/**
 * Created by eli9 on 3/22/2017.
 */
public class RandomAccessFileTest {

    public static void readRandomPosition(String path, int point) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(path, "rw");
            System.out.println("initial position of file read:" + randomAccessFile.getFilePointer());
            randomAccessFile.seek(point);// move pointer to point
            byte[] buff = new byte[4];

            int hasRead;
            /**
             * Reads up to {@code b.length} bytes of data from this file
             * into an array of bytes. This method blocks until at least one byte
             * of input is available.
             */
            while ((hasRead = randomAccessFile.read(buff)) > 0) {
                System.out.println("hasRead: " + hasRead + " Content: " + new String(buff, 0, hasRead, "UTF-8"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testReadLine(String path, int point) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(path, "rw");
            //ReadLine 会读取一行数据，以“carriage-return”结束的行
            //会持续的读下去，直至file end
            String buff = randomAccessFile.readLine();
            System.out.println(buff);
            System.out.println(Integer.parseInt(buff));
            String buff2 = randomAccessFile.readLine();
            System.out.println(buff2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Replace char
    public static void writeRandom(String path, int point) {
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(path, "rw");
            randomAccessFile.seek(point);
            randomAccessFile.write("测试中文字符, test English\r\n".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param path          target file
     * @param points        point in target file
     * @param insertContent content that will insert into target file
     *                      will not replace char
     */
    public static void insertRandom(String path, long points, String insertContent) {
        try {
            File tmp = File.createTempFile("tmp", ".txt");
            tmp.deleteOnExit();

            RandomAccessFile randomAccessFile = new RandomAccessFile(path, "rw");

            FileOutputStream tmpOut = new FileOutputStream(tmp);
            FileInputStream tmpIn = new FileInputStream(tmp);

            randomAccessFile.seek(points);

            // keep temp data read from file
            byte[] buff = new byte[1024];

            //Read data from target file, and write into tmp file.
            int hasRead = 0;
            while ((hasRead = randomAccessFile.read(buff)) > 0) {
                tmpOut.write(buff, 0, hasRead);
            }

            //write insertContent to target file
            randomAccessFile.seek(points);
            randomAccessFile.write(insertContent.getBytes());

            //read data from tmp file, and write into target file
            //hasRead = 0;
            // buff's length always is 1024. When read last content, buff will be filled with "NUL" if content is less
            // than 1024
            //read return: the total number of bytes read into the buffer
            while ((hasRead = tmpIn.read(buff)) > 0) {
                System.out.println("Buffer: " + buff.length);
                randomAccessFile.write(buff, 0, hasRead);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "C:\\Users\\eli9\\Desktop\\test.txt"; //download path
        int seekPoint = 2;
        //readRandomPosition(path,seekPoint);
        //testReadLine(path, seekPoint);
        //writeRandom(path, seekPoint);
        //insertRandom(path,5,"big dream");
        System.out.println("测试中文字符");
    }
}
