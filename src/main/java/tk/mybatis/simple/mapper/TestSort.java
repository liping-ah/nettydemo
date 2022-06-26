package tk.mybatis.simple.mapper;

import com.sun.deploy.util.StringUtils;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class TestSort {
    public static void main(String[] args) {
        SortedMap<String, Object> params = new TreeMap<>();

        params.put("ZKK", "15");
        params.put("AT", "1525524700740");
        params.put("NONCESTR", "1522115166482");
        params.put("PASSWORD", "123456");
        params.put("PHONEID", "android");
        params.put("PHONEIP", "PHONEIP");
        params.put("USERNAME", "13800000001");
        params.put("APPID", "A80C1A90A90C4260B52CB8FE559F70BD");
        String temp=params.toString();
        System.out.println(temp.replaceAll(", ","&"));

    }
    public static void parse(String s, Map<String, String> map) {}

}
