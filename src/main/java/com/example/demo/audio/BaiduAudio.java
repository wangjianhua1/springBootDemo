//package com.example.demo.audio;
//
//import com.baidu.aip.speech.AipSpeech;
//import org.json.JSONObject;
//
//public class BaiduAudio {
//    //设置APPID/AK/SK
//    public static final String APP_ID = "14467450";
//    public static final String API_KEY = "RXBVbG9KiqjRhnmU6WVuwLDi";
//    public static final String SECRET_KEY = "ZB28YZNaH5y2MmgZ00QlrbQZewbTC7TZ";
//
//    public static void main(String[] args) {
//        // 初始化一个AipSpeech
//        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
//
//        // 可选：设置网络连接参数
//        client.setConnectionTimeoutInMillis(2000);
//        client.setSocketTimeoutInMillis(60000);
//
//        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
////        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
////        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
//
//        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
//        // 也可以直接通过jvm启动参数设置此环境变量
////        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
//
//        // 调用接口
//        System.out.println("开始时间："+System.currentTimeMillis()/10000);
//        JSONObject res = client.asr("G:\\app\\1.pcm", "pcm", 16000, null);
//        System.out.println("结束时间："+System.currentTimeMillis()/10000);
//        System.out.println(res.toString(2));
//
//    }
//}
