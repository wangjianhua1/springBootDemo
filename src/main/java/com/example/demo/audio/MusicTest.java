package com.example.demo.audio;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class MusicTest {

    private AudioFormat audioFormat = null;
    private SourceDataLine sourceDataLine = null;
    private DataLine.Info dataLine_info = null;
    private String file = "G:\\苟利国家.wav";
    private AudioInputStream audioInputStream = null;

    public MusicTest(InputStream inputStream) {
        try {
            if (inputStream != null) {
                BufferedInputStream bis=new BufferedInputStream(inputStream);
                audioInputStream = AudioSystem.getAudioInputStream(bis);
            } else {
                audioInputStream = AudioSystem.getAudioInputStream(new File(file));
            }
            audioFormat = audioInputStream.getFormat();
            dataLine_info = new DataLine.Info(SourceDataLine.class, audioFormat);
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLine_info);
            this.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() throws IOException, LineUnavailableException {
        byte[] b = new byte[1024];
        int len = 0;
        sourceDataLine.open(audioFormat, 1024);
        sourceDataLine.start();
        while ((len = audioInputStream.read(b)) > 0) {
            sourceDataLine.write(b, 0, len);
        }
        audioInputStream.close();
        sourceDataLine.drain();
        sourceDataLine.close();
    }


    public static void main(String[] args) {
        new MusicTest(null);
    }

}
