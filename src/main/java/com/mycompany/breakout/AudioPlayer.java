package com.mycompany.breakout;

import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class AudioPlayer implements Runnable {

    private final int BUFFER_SIZE = 128000;
    private InputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceLine;
    private String filename;

    // Constructor to initialize the filename
    public AudioPlayer(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        try {
            // Access the file as a resource inside the JAR
            audioStream = getClass().getResourceAsStream("/" + filename);
            if (audioStream == null) {
                throw new IOException("File not found in the classpath: " + filename);
            }

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioStream);
            audioFormat = audioInputStream.getFormat();

            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);

            sourceLine.start();

            byte[] abData = new byte[BUFFER_SIZE];
            int nBytesRead = 0;

            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0) {
                    sourceLine.write(abData, 0, nBytesRead);
                }
            }

            sourceLine.drain();
            sourceLine.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
