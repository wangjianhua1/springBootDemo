package com.example.demo.audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.TargetDataLine;
import java.io.File;
import java.io.FileOutputStream;

public class Audio {
    private static FileOutputStream os;
    //采样率
    private static float RATE = 16000f;
    //编码格式PCM
    private static AudioFormat.Encoding ENCODING = AudioFormat.Encoding.PCM_SIGNED;
    //帧大小 16
    private static int SAMPLE_SIZE = 16;
    //是否大端
    private static boolean BIG_ENDIAN = false;
    //通道数
    private static int CHANNELS = 1;

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            save("G:\\app\\1.pcm");
        } else {
            save(args[0]);
        }
    }

    public static void save(String path) throws Exception {
        File file = new File(path);
        if (file.isDirectory()) {
            if (!file.exists()) {
                file.mkdirs();
            }
            file.createNewFile();
        }

        AudioFormat audioFormat = new AudioFormat(ENCODING, RATE, SAMPLE_SIZE, CHANNELS, (SAMPLE_SIZE / 8) * CHANNELS, RATE, BIG_ENDIAN);
        TargetDataLine targetDataLine = AudioSystem.getTargetDataLine(audioFormat);
        targetDataLine.open();
        targetDataLine.start();
        byte[] b = new byte[320];
        int flag = 0;
        os = new FileOutputStream(file);
        while ((flag = targetDataLine.read(b, 0, b.length)) > 0) {//从声卡中采集数据
            os.write(b);
            os.flush();
            System.out.println(flag);
        }
    }
}
