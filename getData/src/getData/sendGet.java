package getData;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;


/**
 * Created by cty on 2016/1/26.
 */
public class sendGet {
    private static String testAddress = "东城区龙潭东路";

    public static String getJson(String location) throws IOException {
        String ak = "e8y7kOI6kaaSUkbCX2uKNgMT";
        String url = "http://api.map.baidu.com/geocoder/v2/?ak="
                +ak
                +"&callback=renderReverse&location="
                +location
                +"&output=json&pois=1";
        String result = new String();

        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");

            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        String str = result.substring(29,result.length()-1);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(str);
//        System.out.println(str);
        String address = root.with("result").with("addressComponent").get("district").asText()
                + root.with("result").with("addressComponent").get("street").asText();
        return address;
    }


    public static void main(String[] args) throws IOException {
        String address = sendGet.getTestAddress();
        if(address.equals(sendGet.getTestAddress())){
            System.out.println("fuck");
        }


    }

    public static String getTestAddress() {
        return testAddress;
    }

    public static void setTestAddress(String testAddress) {
        sendGet.testAddress = testAddress;
    }
}
