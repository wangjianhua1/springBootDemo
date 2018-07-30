package com.example.demo.bean;

import java.awt.Color;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 双胞兄弟
 */
public class Twins {
    /**
     * 衣服颜色
     */
    private Color color;

    public Twins(Color color) {
        this.color = color;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (o == this)
//            return true;
//        if (!(o instanceof Twins)) {
//            return false;
//        }
//        Twins user = (Twins) o;
//        return color.equals(user.color);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = 17;
//        result = 31 * result + color.hashCode();
//        return result;
//    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static void main(String[] args) {
        Map<Twins, String> hashMap = new HashMap<Twins, String>();
        Map<Twins, String> identityMap = new IdentityHashMap<Twins, String>();
        // 兄弟
        Twins brother = new Twins(Color.green);
        // 哥哥
        Twins eldBrother = new Twins(Color.green);
        hashMap.put(brother, "弟弟");
        hashMap.put(eldBrother, "哥哥");
        System.out.println(hashMap);//{com.scc.Twins@ff01010f=哥哥} 结果却只有哥哥

        identityMap.put(brother, "绿色衣服的弟弟");

        //第二天弟弟换了一身蓝衣服
        brother.setColor(Color.BLUE);

        identityMap.put(brother, "蓝色衣服的弟弟");
        identityMap.put(eldBrother,"绿色衣服的哥哥");
        System.out.println(identityMap);//{com.scc.Twins@ff00030e=蓝色衣服的弟弟} 结果弟弟还是弟弟,只是颜色不同罢了
        AtomicInteger atomicInteger=new AtomicInteger();
        int i = atomicInteger.incrementAndGet();
        System.out.println(i);
        System.out.println(-3 >>> 1 );
        System.out.println(Integer.toBinaryString(-3) );
        System.out.println(Integer.toBinaryString(3) );
        System.out.println(Integer.toBinaryString((-3 >>> 1)));
    }
}