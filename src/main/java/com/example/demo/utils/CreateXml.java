package com.example.demo.utils;

import com.alibaba.fastjson.JSON;
import com.example.demo.bean.Book;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.CompactWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

/**
 * XStream 的输出方式有多种： toXML 方式， ObjectOutputStream 方式， marshal
 * <p>
 * toXML() 方法，本身 toXML 的方法就有 2 种：
 * 第一种 :java.lang.String toXML(java.lang.Object obj)
 * 将对象序列化为 XML 格式并保存到一个 String 对象中。
 * 第二种 :void toXML(java.lang.Object obj, java.io.Writer out)
 * 将对象序列化为 XML 格式后以 Writer 输出到某个地方存储
 *
 * @link {http://xiaoxuejie.iteye.com/blog/1183048}
 */
public class CreateXml<T> {
    private static XStream xstream = new XStream();

    public static void main(String[] args) throws Exception {
//        TestCreateXml xml = new TestCreateXml();
//        xml.setAge("25");
//        xml.setUsername("灵魂");
        String str = "D:/TestCreateXml.xml";
//        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(str));// 创建一个对象输出流。
//        XMLEncoder xmle=new XMLEncoder(out);// 创建一个 XMLEncoder 对象。
//        Book book=new Book();
//        book.setAuthor("wjh");
//        book.setDescription("test");
//        book.setId(12L);
//        xmle.writeObject(book);// 使用 XMLEncode 的 writeObject 方法输出 pu
////        xmle.flush();// 刷新
//        xmle.close();// 关闭输出流

        CreateXml createXml = new CreateXml();
//        createXml.toXmlFile(xml, str);

        System.out.println((JSON.toJSONString(createXml.readXmlDecoder(str))));
    }

    /**
     * 获取对象的xml格式
     *
     * @param obj
     * @return
     */
    public String writeToXmlString(T obj) {
        return xstream.toXML(obj);
    }

    /**
     * CompactWriter 此对象生成连续输出，无格式的xml文件
     * PrettyPrintWriter 有格式的xml文件
     *
     * @param obj
     * @param writer
     */
    public void writeToXmlFile(T obj, HierarchicalStreamWriter writer) {
        xstream.marshal(obj, writer);
    }

    /**
     * 以对象流的形式输出
     *
     * @param obj
     * @param ppw
     * @throws IOException
     */
    public void writeToXmlFile(T obj, PrettyPrintWriter ppw) throws IOException {
        ObjectOutputStream obj_out = xstream.createObjectOutputStream(ppw);
        obj_out.writeObject(obj);
        obj_out.close();
    }

    /**
     * @param obj
     * @param filepath 输出到文件对象
     * @throws FileNotFoundException
     */
    public void writeToXmlFile(T obj, String filepath) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(filepath);//输出流
        xstream.toXML(obj, pw);
    }

    /**
     * 读取xml的内容
     *
     * @param filepath
     * @return
     * @throws Exception
     * @see com.thoughtworks.xstream.io.xml.AbstractXppDriver#createReader
     */
    public T readXml(String filepath) throws Exception {
        FileReader fr = new FileReader(filepath);
        BufferedReader br = new BufferedReader(fr);
        ObjectInputStream obj_input = xstream.createObjectInputStream(br);
        T obj = (T) obj_input.readObject();
        return obj;
    }

    /**
     * @param filepath
     * @return
     * @throws Exception
     */
    public T readFromXML(String filepath) throws Exception {
        FileReader fr = new FileReader(filepath);
        BufferedReader br = new BufferedReader(fr);
        T obj = (T) xstream.fromXML(br);
        return obj;
    }

    /**
     * Java 自带的 XML 解析方式
     *
     * @param filepath
     * @return
     * @throws Exception
     */
    public T readXmlDecoder(String filepath) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filepath));
        XMLDecoder xmlDecoder = new XMLDecoder(in);// 创建一个 XMLDecoder 对象。
        return (T) xmlDecoder.readObject();
    }

    /**
     * Java 自带的 XML 编码方式
     *
     * @param obj 只能是public对象,否则抛异常
     * @return
     * @throws Exception
     */
    public void writeXmlEncoder(T obj, String filepath) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filepath));// 创建一个对象输出流。
        XMLEncoder xmlEncoder = new XMLEncoder(out);// 创建一个 XMLEncoder 对象。
        xmlEncoder.writeObject(obj);
        Object owner = xmlEncoder.getOwner();
        xmlEncoder.flush();
        xmlEncoder.close();
    }
}

/**
 * 同级类
 * 私有类不能被java.bean里的xml方式解析
 */
class TestCreateXml implements Serializable {
    private String age;
    private String username;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "TestCreateXml{" +
                "age=" + age +
                ", username='" + username + '\'' +
                '}';
    }
}
