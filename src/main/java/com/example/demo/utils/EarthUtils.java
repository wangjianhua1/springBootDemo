package com.example.demo.utils;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

public class EarthUtils {
    private static double EARTH_RADIUS = 6378137;

    /**
     * 计算两个经纬度之间的距离  结果单位：米
     */
    public static double getDistance(String lat1Str, String lng1Str, String lat2Str, String lng2Str) {
        Double lat1 = Double.parseDouble(lat1Str);
        Double lng1 = Double.parseDouble(lng1Str);
        Double lat2 = Double.parseDouble(lat2Str);
        Double lng2 = Double.parseDouble(lng2Str);

        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000.0;
        return s;
    }


    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }


    public static double getDistanceMeter(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo, Ellipsoid ellipsoid) {
        //创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);
        return geoCurve.getEllipsoidalDistance();
    }

    public static void main(String[] args) {
        double distance = getDistance("30.505937826133074", "114.43202560752869", "30.50491639041302", "114.43138724178314");
        System.out.println(distance);

//        GlobalCoordinates source = new GlobalCoordinates(29.490295, 106.486654);
//        GlobalCoordinates target = new GlobalCoordinates(29.615467, 106.581515);
        GlobalCoordinates source = new GlobalCoordinates(30.505937826133074, 114.43202560752869);
        GlobalCoordinates target = new GlobalCoordinates(30.50491639041302, 114.43138724178314);

        double meter1 = getDistanceMeter(source, target, Ellipsoid.Sphere);
        double meter2 = getDistanceMeter(source, target, Ellipsoid.WGS84);


        System.out.println("Sphere坐标系计算结果：" + meter1 + "米");
        System.out.println("WGS84坐标系计算结果：" + meter2 + "米");
    }
}
