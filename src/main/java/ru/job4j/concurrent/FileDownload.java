package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author madrabit
 * Wget programm.
 * Download data from Url.
 * If data is big progmramm pause. 1 sleep sedcond equals 1Kb.
 */
public class FileDownload implements Runnable {
//    String file = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";

    /**
     * Target URL.
     */
    private final String url;
    /**
     * Expected download speed.
     */
    private final int speed;

    public FileDownload(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        wget(url, speed);
    }

    /**
     * wget simulator.
     * 1) Get file size
     * 2) Calculate expected Time = file Size / expected speed.
     * 3) Calculate actual Time = current time - start time
     * 4) Compare actual and expected time
     * 5) Delay = actualTime - expectedTime
     *
     * @param url   Target URL.
     * @param speed Expected download speed.
     */
    private void wget(String url, int speed) {
        int fileSize = 1;
        try {
            URLConnection connection = new URL(url).openConnection();
            connection.connect();
            fileSize = connection.getContentLength();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());

             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            System.out.println("Download starts");
            long startTime = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                long actualTime = System.currentTimeMillis() - startTime;
                long expectedTime = fileSize / speed;
                if (actualTime > expectedTime) {
                    System.out.println("Process slows down...");
                    try {
                        Thread.sleep(actualTime - expectedTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Download finished");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Thread thread = new Thread(new FileDownload(args[0], Integer.parseInt(args[1])));
        thread.start();
    }


}
