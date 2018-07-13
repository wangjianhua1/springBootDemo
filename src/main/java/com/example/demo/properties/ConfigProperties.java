//package com.example.demo.properties;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//
///**
// * Created by wangjianhua3 on 2018/3/8.
// */
////@Profile("test")
//@Component
//@ConfigurationProperties(prefix = "index")
//public class ConfigProperties {
//    private Log log = LogFactory.getLog(ConfigProperties.class);
//    private String test1Name;
//    private String test2Name;
//    private String testName;
//    private String test3Name;
//
//
//    public String getTest1Name() {
//        return test1Name;
//    }
//
//    public void setTest1Name(String test1Name) {
//        log.info("=test=name=1=" + test1Name);
//        this.test1Name = test1Name;
//    }
//
//    public String getTest2Name() {
//        return test2Name;
//    }
//
//    public void setTest2Name(String test2Name) {
//        log.info("=test=name=2=" + test2Name);
//        this.test2Name = test2Name;
//    }
//
//    public String getTestName() {
//        return testName;
//    }
//
//    public void setTestName(String testName) {
//        log.info("=test=name=" + testName);
//        this.testName = testName;
//    }
//
//    public String getTest3Name() {
//        return test3Name;
//    }
//
//    public void setTest3Name(String test3Name) {
//        log.info("=test=name=3=" + test3Name);
//        this.test3Name = test3Name;
//    }
//
//    @Override
//    public String toString() {
//        return "ConfigProperties{" +
//                "test1Name='" + test1Name + '\'' +
//                ", test2Name='" + test2Name + '\'' +
//                ", testName='" + testName + '\'' +
//                ", test3Name='" + test3Name + '\'' +
//                '}';
//    }
//}
