package it.example.util;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class SoundUtil {
    private static AudioFormat audioF;
    private static SourceDataLine sourceDL;
    private static volatile boolean playing;

    private static double[] waveData;
    private static Thread soundThread;

    private SoundUtil() {}

    /**
     * Start playing sound if not already playing and waveData is available.
     */
    public static void play() {
        if (!playing && waveData != null) {
            playing = true;
            audioF = new AudioFormat(44100, 8, 1, true, false); // AudioFormat at 8 bit, mono
            try {
                sourceDL = AudioSystem.getSourceDataLine(audioF);
                sourceDL.open(audioF);
                sourceDL.start();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
                playing = false; // Set playing to false to stop the playback process
                return;
            }

            soundThread = new Thread(() -> {
                int bufferSize = 1024;
                int vol = 100;
                byte[] buf = new byte[bufferSize];

                int i = 0;
                while (playing) {
                    byte sample = (byte) (waveData[i] * vol);

                    // Write the sample to the buffer
                    buf[i] = sample;

                    if (i % bufferSize == bufferSize - 1 || i == waveData.length - 1) {
                        sourceDL.write(buf, 0, i % bufferSize + 1);
                    }

                    i = (i + 1) % waveData.length;
                }

                sourceDL.stop();
                sourceDL.close();
            });
            soundThread.start();
        }
    }

    /**
     * Stop playing the sound.
     */
    public static void stop() {
        if (playing) {
            playing = false;
            try {
                Thread.sleep(10); // Add a small delay to allow sound to stop
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sourceDL.stop();
            sourceDL.flush();
            sourceDL.close();
        }
    }

    /**
     * Set the wave data to be played.
     * @param waveData The array containing the waveform data.
     */
    public static void setWaveData(double[] waveData) {
        SoundUtil.waveData = waveData;
    }

    public static double[] getWaveData() {
        return waveData;
    }
}
