package com.mycompany.breakout;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class AudioMngr {

    // Create a pool of 5 Clip objects (adjust size based on expected concurrency)
    private static final int POOL_SIZE = 5;
    private static final BlockingQueue<Clip> clipPool = new ArrayBlockingQueue<>(POOL_SIZE);

    static {
        try {
            // Initialize the pool of clips
            for (int i = 0; i < POOL_SIZE; i++) {
                clipPool.put(AudioSystem.getClip());
            }
        } catch (LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void playSound(String filename) {
        // Run sound playback in a separate thread to avoid blocking the main program
        new Thread(new Runnable() {
            @Override
            public void run() {
                Clip clip = null; // Declare the clip object inside the thread
                AudioInputStream audioInputStream = null;
                try {
                    // Get the sound file
                    File soundFile = new File(filename);

                    // Check if the file exists
                    if (!soundFile.exists()) {
                        System.out.println("Error: Sound file not found - " + filename);
                        return;
                    }

                    // Load the audio input stream
                    audioInputStream = AudioSystem.getAudioInputStream(soundFile);

                    // Try to obtain an available clip from the pool
                    clip = clipPool.take();
                    clip.open(audioInputStream);

                    // Start playing the clip in the background
                    clip.start();

                    // Optional: Clean up after the sound finishes playing
                    // Use a separate thread to allow sound to finish and return the clip to the pool
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                // Wait for the clip to finish playing
                                Thread.sleep(clip.getMicrosecondLength() / 1000); // Convert microseconds to milliseconds

                                // Clean up resources after playing
                                clip.close();
                                audioInputStream.close();

                                // Return the clip to the pool
                                try {
                                    clipPool.put(clip); // Reuse the clip for later use
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt(); // Restore interrupted status
                                    e.printStackTrace();
                                }
                            } catch (InterruptedException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // Make sure to release resources even if something goes wrong
                    if (audioInputStream != null) {
                        try {
                            audioInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (clip != null && clip.isOpen()) {
                        clip.close();
                    }
                }
            }
        }).start();
    }
}
