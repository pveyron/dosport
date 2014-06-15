package com.dosport.system.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * 地理坐标工具类.
 * 
 * @author pwl
 * 
 */
public class CoordinateUtils {

	private final static double R = 6371229; // 地球的半径

	public static final String BAIDU_KEY = "648c96528fc5113e273667418dfc47b1";
	public static final String BAIDU_URL = "http://api.map.baidu.com/geocoder?";

	/**
	 * 具体地址到金纬度的转换.
	 * 
	 * @param address
	 * @return
	 */
	public static Map<String, Double> addrToCoordinate(String address) {

		Map<String, Double> map = new HashMap<String, Double>();
		if (StringUtils.isNotBlank(address)) {
			BufferedReader in = null;
			try {
				// 将地址转换成utf-8的16进制
				address = URLEncoder.encode(address, "UTF-8");
				// 如果有代理，要设置代理，没代理可注释
				// System.setProperty("http.proxyHost","192.168.1.188");
				// System.setProperty("http.proxyPort","3128");
				URL url = new URL(BAIDU_URL + "address=" + address + "&output=json&key=" + BAIDU_KEY);
				in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
				String res = null;
				StringBuilder sb = new StringBuilder("");
				while ((res = in.readLine()) != null) {
					sb.append(res.trim());
				}
				String str = sb.toString();
				if (StringUtils.isNotEmpty(str)) {
					int lngStart = str.indexOf("lng\":");
					int lngEnd = str.indexOf(",\"lat");
					int latEnd = str.indexOf("},\"precise");
					if (lngStart > 0 && lngEnd > 0 && latEnd > 0) {
						String longitude = str.substring(lngStart + 5, lngEnd);
						String latitude = str.substring(lngEnd + 7, latEnd);
						map.put("longitude", NumberUtils.createDouble(longitude));
						map.put("latitude", NumberUtils.createDouble(latitude));
					}
				}
			} catch (Exception e) {
				LogUtils.error(e, "地址=“{}”装换成坐标出现异常", address);
				e.printStackTrace();
			} finally {
				try {
					if (in != null) {
						in.close();
					}
				} catch (IOException e) {
					LogUtils.error(e, "地址=“{}”装换成坐标关闭BufferedReader出现异常", address);
					e.printStackTrace();
				}
			}
		}
		return map;
	}

	/**
	 * 计算两个坐标的距离.
	 * 
	 * @param longtitude1
	 * @param latitude1
	 * @param longtitude2
	 * @param latitude2
	 * @return
	 */
	public static double getDistance(Double longtitude1, Double latitude1, Double longtitude2, Double latitude2) {

		double distance = 0;
		if (longtitude1 != null && latitude1 != null && longtitude2 != null && latitude2 != null) {
			double x, y;
			x = (longtitude2 - longtitude1) * Math.PI * R * Math.cos(((latitude1 + latitude2) / 2) * Math.PI / 180)
					/ 180;
			y = (latitude2 - latitude1) * Math.PI * R / 180;
			distance = Math.hypot(x, y) / 1000;
		}
		return distance;
	}
}
