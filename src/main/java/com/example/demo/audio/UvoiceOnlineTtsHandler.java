package com.example.demo.audio;

import okhttp3.*;
import okhttp3.Request.Builder;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class UvoiceOnlineTtsHandler {
    public static Callback callback = new Callback() {
        @Override
        public void onFailure(Call var1, IOException var2) {
            System.out.println(var2.getMessage());

        }

        @Override
        public void onResponse(Call var1, Response resp) throws IOException {
            String var5;
            if (!(var5 = resp.message()).equals("OK")) {
                System.out.println(resp.body());
            } else {
                InputStream inputStream = resp.body().byteStream();
//                new MusicTest(inputStream);
                playAudio(inputStream);
                inputStream.close();

            }
        }
    };

    /**
     * 播放pcm音频
     * @param inputStream
     */
    public static void playAudio(InputStream inputStream) {
        try {
            AudioFormat.Encoding encoding =  new AudioFormat.Encoding("PCM_SIGNED");
            AudioFormat format = new AudioFormat(encoding,8000, 16, 1, 2, 8000 ,false);//编码格式，采样率，每个样本的位数，声道，帧长（字节），帧数，是否按big-endian字节顺序存储
            SourceDataLine auline = null;
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            try {
                auline = (SourceDataLine) AudioSystem.getLine(info);
                auline.open(format);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            auline.start();
            byte[] b = new byte[256];
            try {
                while(inputStream.read(b)>0) {
                    auline.write(b, 0, b.length);
                    System.out.println(1);
                }
                auline.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        String text="今天天气很好,你app播放出声音了吗,注：PCM编码格式的文件（音频流）称为裸流，即纯粹的音频数据，将这些数据直接丢进混响器即可直接播放。";
        Request request = (new Builder()).addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                .url("https://tts.uvoicetech.com:7700/tts")
                .post((new okhttp3.FormBody.Builder())
                        .add("cid", "ANDROID_ONLINE_TTS_BIAOBEI")
                        .add("texttype", String.valueOf(0))
                        .add("rqformat", String.valueOf(0))
                        .add("sampleRate", String.valueOf(16000))
                        .add("speed", String.valueOf(0))
                        .add("pitch", String.valueOf(0))
                        .add("volume", String.valueOf(0))
                        .add("person", String.valueOf("xiao_mei"))
                        .add("text", text).build()).build();
        OkHttpClient client=new OkHttpClient();
               client.newCall(request).enqueue(UvoiceOnlineTtsHandler.callback);
        Response response = client.newCall(request).execute();
        response.body();
    }


}
