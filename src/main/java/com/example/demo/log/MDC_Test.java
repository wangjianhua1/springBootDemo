package com.example.demo.log;

import org.junit.Test;
import org.slf4j.MDC;

/**
 * org.slf4j.MDC是为日志打印类线程的一个变量(ThreadLocal)，打印日志时根据pattern来取出需要打印的参数
 */
public class MDC_Test {
    public static void main(String[] args) {
        MDC.put("key_1","test1");
    }

    @Test
    public void TestMDC(){
        MDC.clear();
        MDC.put("sessionId" , "f9e287fad9e84cff8b2c2f2ed92adbe6");
        MDC.put("cityId" , "1");
        MDC.put("siteName" , "北京");
        MDC.put("userName" , "userwyh");
        TraceLogger.info("测试MDC打印一");

        MDC.put("mobile" , "110");
        TraceLogger. info("测试MDC打印二");

        MDC.put("mchId" , "12");
        MDC.put("mchName", "商户名称");
        TraceLogger. info("测试MDC打印三");
    }

}
