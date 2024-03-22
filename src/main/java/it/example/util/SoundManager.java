package it.example.util;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class SoundManager {
    private AudioFormat audioF;
    private SourceDataLine sourceDL;
    private volatile boolean playing = false;

    private double[] waveData;
    private Thread soundThread;

    private static final SoundManager instance = new SoundManager();

    private SoundManager() {}

    public static synchronized SoundManager getInstance() {
        return instance;
    }

    /**
     * Start playing sound if not already playing and waveData is available.
     */
    public void play() {
        if (!playing && waveData != null) {
            playing = true;
            audioF = new AudioFormat(44100, 8, 1, true, false); // AudioFormat at 8 bit, mono
            try {
                sourceDL = AudioSystem.getSourceDataLine(audioF);
                sourceDL.open(audioF);
                sourceDL.start();
                startSoundThread();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
                playing = false; // Set playing to false to stop the playback process
            }
        }
    }

    private void startSoundThread() {
        soundThread = new Thread(() -> {
            int bufferSize = 1024;
            byte[] buf = new byte[bufferSize];

            for (int i = 0; playing; i = (i + 1) % waveData.length) {
                byte sample = (byte) (waveData[i] * 100);

                buf[i % bufferSize] = sample;
                if (i % bufferSize == bufferSize - 1 || i == waveData.length - 1) {
                    sourceDL.write(buf, 0, (i % bufferSize) + 1);
                }
            }

            sourceDL.drain();
            sourceDL.stop();
            sourceDL.close();
        });
        soundThread.start();
    }

    /**
     * Stop playing the sound.
     */
    public void stop() {
        playing = false;
        if (soundThread != null && soundThread.isAlive()) {
            try {
                soundThread.join(); // Wait for the thread to finish
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Preserve interrupt status
            }
        }
        if (sourceDL != null) {
            sourceDL.stop();
            sourceDL.flush();
            sourceDL.close();
        }
    }

    /**
     * Set the wave data to be played.
     * @param waveData The array containing the waveform data.
     */
    public void setWaveData(double[] waveData) {
        this.waveData = waveData;
    }

    public double[] getWaveData() {
        return waveData;
    }
}
