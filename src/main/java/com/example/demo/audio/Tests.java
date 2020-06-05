package com.example.demo.audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.TargetDataLine;
import java.io.FileOutputStream;

/**
 * 录制声音
 */
public class Tests {
    private static FileOutputStream os;
    //采样率
    private static float RATE = 8000f;
    //编码格式PCM
    private static AudioFormat.Encoding ENCODING = AudioFormat.Encoding.PCM_SIGNED;
    //帧大小 16
    private static int SAMPLE_SIZE = 16;
    //是否大端
    private static boolean BIG_ENDIAN = true;
    //通道数
    private static int CHANNELS = 1;



    /**
     * 读取的文件写入"F:/audio/123.pcm"
     *
     * @param args
     * @throws Exception
     */


    /**
     * 读取语音文件
     *
     * @throws Exception
     */
    public static void read() throws Exception {
        AudioFormat audioFormat = new AudioFormat(ENCODING, RATE, SAMPLE_SIZE, CHANNELS, (SAMPLE_SIZE / 8) * CHANNELS,
                RATE, BIG_ENDIAN);
        TargetDataLine targetDataLine = AudioSystem.getTargetDataLine(audioFormat);
        targetDataLine.open();
        targetDataLine.start();
        byte[] b = new byte[320];
        int flag = 0;
        /**
         * 从声卡中采集数据
         */
        while ((flag = targetDataLine.read(b, 0, b.length)) > 0) {
//            handler.resumeAsrSession(b);
            System.out.println(flag);
            flag = 0;
        }
    }
}
