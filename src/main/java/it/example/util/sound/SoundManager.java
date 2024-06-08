package it.example.util.sound;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import it.example.util.alert.AlertUtil;

public class SoundManager {
    private AudioFormat audioF;
    private SourceDataLine sourceDL;
    private volatile boolean playing = false;

    private short[] shortData;
    private Thread soundThread;

    private static SoundManager instance;

    private SoundManager() {}

    public static synchronized SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    /**
     * Start playing sound if not already playing and shortData is available.
     */
    public void play() {
        if (!playing && shortData != null) {
            playing = true;
            audioF = new AudioFormat(44100, 16, 1, true, false); // AudioFormat at 16 bit, mono
            try {
                sourceDL = AudioSystem.getSourceDataLine(audioF);
                sourceDL.open(audioF);
                sourceDL.start();
                startSoundThread();
            } catch (LineUnavailableException e) {
                AlertUtil.showErrorAlert("Playback Error", "Failed to start audio playback. Audio line unavailable.");
                playing = false; // Set playing to false to stop the playback process
            }
        }
    }

    private void startSoundThread() {
        soundThread = new Thread(() -> {
            int bufferSize = 44100; // Buffer size in samples, 1 second of audio

            byte[] buffer = new byte[bufferSize * 2]; // 2 bytes per sample (16-bit audio)

            while (playing) {
                for (int i = 0; i < bufferSize && playing; i++) {
                    buffer[2 * i] = (byte) (shortData[i] & 0xff);
                    buffer[2 * i + 1] = (byte) ((shortData[i] >> 8) & 0xff);
                }
                sourceDL.write(buffer, 0, buffer.length);
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
     * @param shortData The array containing the waveform data.
     */
    public void setWaveData(short[] shortData) {
        this.shortData = shortData;
    }
}
