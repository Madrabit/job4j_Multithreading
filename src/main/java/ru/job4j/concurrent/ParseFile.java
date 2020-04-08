package ru.job4j.concurrent;

import java.io.*;

/**
 * @author madrabit
 * Parse file.
 * 1. Synchronized
 * 2. IO bugs fixed
 * 3. Added StringBuilder
 */
public class ParseFile {
    /**
     * Target File for parsing.
     */
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    /**
     * Get content Unicode format.
     *
     * @return File content in String.
     */
    public synchronized String getContent() {
        StringBuilder output = new StringBuilder();
        try (InputStream i = new BufferedInputStream(new FileInputStream(file))) {

            int data;
            while ((data = i.read()) > 0) {
                output.append((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    /**
     * Get content without Unicode format.
     *
     * @return File content in String.
     */
    public synchronized String getContentWithoutUnicode() {
        StringBuilder output = new StringBuilder();

        try (InputStream i = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = i.read()) > 0) {
                if (data < 0x80) {
                    output.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    /**
     * Save content to File.
     *
     * @param content Text content.
     */
    public synchronized void saveContent(String content) {
        try (OutputStream o = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i++) {
                o.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
