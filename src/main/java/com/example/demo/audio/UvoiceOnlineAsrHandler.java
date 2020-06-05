package com.example.demo.audio;

import okhttp3.*;
import okhttp3.Request.Builder;
import okio.ByteString;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.sound.sampled.AudioFormat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class UvoiceOnlineAsrHandler {
    private OkHttpClient client = new OkHttpClient();
    private Request req;
    private boolean c = false;
    private WebSocket websocket;
    private int f = 16000;//8000
    private static String g;
    private static String h;
    private WebSocketListener listener = new WebSocketListener() {
        @Override
        public final void onOpen(WebSocket var1, Response var2) {
            super.onOpen(var1, var2);
        }

        @Override
        public final void onMessage(WebSocket var1, String var2) {
            super.onMessage(var1, var2);
            if (UvoiceOnlineAsrHandler.this.c) {
                UvoiceOnlineAsrHandler.this.websocket.close(1000, "结束识别");
                if (UvoiceOnlineAsrHandler.this.f == 8000) {
                    System.out.println(var2.replaceAll(" ", ""));
                    return;
                }

                try {
                    JSONObject var8;
                    if ((var8 = new JSONObject(var2)).has("_hypotheses")) {
                        JSONArray var9;
                        if ((var9 = var8.getJSONArray("_hypotheses")).length() <= 0) {
                            System.out.println("");
                            return;
                        }

                        JSONObject var10;
                        int var3 = (var10 = var9.getJSONObject(0)).getInt("_score");
                        int var6;
                        if (var9.length() > 1) {
                            for (int var4 = 1; var4 < var9.length(); ++var4) {
                                JSONObject var5;
                                if ((var6 = (var5 = var9.getJSONObject(var4)).getInt("_score")) > var3) {
                                    var3 = var6;
                                    var10 = var5;
                                }
                            }
                        }

                        JSONArray var11;
                        if ((var11 = var10.getJSONArray("_items")).length() <= 0) {
                            System.out.println("");
                            return;
                        }

                        StringBuffer var12 = new StringBuffer();

                        for (var6 = 0; var6 < var11.length(); ++var6) {
                            var12.append(var11.getJSONObject(var6).getString("_orthography"));
                        }

                        System.out.println(var12.toString().replaceAll("<[/]*s>", ""));
                        System.out.println("结束：" + System.currentTimeMillis() / 1000);
                        return;
                    }

                    System.out.println("");
                    return;
                } catch (JSONException var7) {
                    var7.printStackTrace();
                    System.out.println("JSON结果解析错误");
                }
            }

        }

        @Override
        public final void onMessage(WebSocket var1, ByteString var2) {
            super.onMessage(var1, var2);
            if (var2.toByteArray().length == 4) {
                UvoiceOnlineAsrHandler.this.c = true;
            }

        }

        @Override
        public final void onClosing(WebSocket var1, int var2, String var3) {
            super.onClosing(var1, var2, var3);
        }

        @Override
        public final void onClosed(WebSocket var1, int var2, String var3) {
            super.onClosed(var1, var2, var3);
            UvoiceOnlineAsrHandler.this.c = false;

        }

        @Override
        public final void onFailure(WebSocket var1, Throwable var2, Response var3) {
            super.onFailure(var1, var2, var3);
            UvoiceOnlineAsrHandler.this.c = false;
            System.out.println(var2.getMessage());
        }
    };

    public UvoiceOnlineAsrHandler() {
    }

    public int init(String var2) {
        g = "ws://" + var2 + "/asr2?cid=ANDROID_ONLINE_ASR_BIAOBEI&sid=10086&encode=0&format=0&samplerate=8000";
        h = "ws://" + var2 + "/asr2?cid=ANDROID_ONLINE_ASR_BIAOBEI&sid=10086&encode=0&format=1";
        return 0;
    }

    //8000
    public void setSampleRate(int rate) {
        this.f = rate;
        String url = rate == 8000 ? g : h;
        this.req = (new Builder()).url(url).build();
    }

    public int startAsrSession() {
        if (this.client != null && this.listener != null && this.req != null) {
            if (!this.c) {
                this.websocket = this.client.newWebSocket(this.req, this.listener);
                return 0;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    /**
     * 发送语音
     */
    public int resumeAsrSession(byte[] var1) {
        if (this.websocket == null) {
            return -1;
        } else {
            this.websocket.send(ByteString.of(var1, 0, var1.length));
            return 0;
        }
    }

    public int stopAsrSession() {
        if (this.websocket == null) {
            return -1;
        } else {
            byte[] var1 = new byte[0];
            this.websocket.send(ByteString.of(var1, 0, 0));
            return 0;
        }
    }


    public static String getVersionCode() {
        return "1.0.2";
    }


    public static void main(String[] args) throws Exception {
        UvoiceOnlineAsrHandler handler = new UvoiceOnlineAsrHandler();
        handler.init("wx.uvoicetech.com:8001");
        handler.setSampleRate(16000);
        handler.startAsrSession();
        System.out.println("开始时间："+System.currentTimeMillis() / 1000);
        InputStream os = new FileInputStream(new File("G:\\app\\1.pcm"));
        byte[] b = new byte[320];
        int flag = 0;
        while ((flag = os.read(b, 0, b.length)) > 0) {
            handler.resumeAsrSession(b);
        }
        handler.stopAsrSession();
    }

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

//    public static void main(String[] args) throws Exception {
//        UvoiceOnlineAsrHandler handler = new UvoiceOnlineAsrHandler();
//        handler.init("wx.uvoicetech.com:8001");
//        handler.setSampleRate(16000);
//
//        System.out.println("开始时间：" + System.currentTimeMillis() / 1000);
//        AudioFormat audioFormat = new AudioFormat(ENCODING, RATE, SAMPLE_SIZE, CHANNELS, (SAMPLE_SIZE / 8) * CHANNELS, RATE, BIG_ENDIAN);
//        TargetDataLine targetDataLine = AudioSystem.getTargetDataLine(audioFormat);
//        targetDataLine.open();
//        targetDataLine.start();
//
//        byte[] b = new byte[320];
//        int flag = 0;
//        int k = 0;
//        handler.startAsrSession();
//        while ((flag = targetDataLine.read(b, 0, b.length)) > 0) {//从声卡中采集数据
//            handler.resumeAsrSession(b);
//        }
//        handler.stopAsrSession();
//    }
}

