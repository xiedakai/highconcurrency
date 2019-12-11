package highconcurrency.distribute;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class DistributeLimiter {

    private static String limitRate;
    private static String multiReturn;
    private static Jedis jedis;


    static {
        try {
             limitRate = Files.toString(new File(DistributeLimiter.class.getResource("/").getPath() + "limitRate.lua"), Charset.defaultCharset());
             multiReturn = Files.toString(new File(DistributeLimiter.class.getResource("/").getPath() + "multiReturn.lua"), Charset.defaultCharset());
             jedis = new Jedis("192.168.5.243", 6379);
            jedis.auth("Dashu0701");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
            //testLimitRate();
        test();

    }

    public static void  test(){
        try {
            Object obj = jedis.eval(multiReturn);
            System.out.println("multi returns:"+obj);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void  testLimitRate(){
        try {
            System.out.println("acquired 1:" + acquire());
            System.out.println("acquired 2:" + acquire());
            System.out.println("acquired 3:" + acquire());
            System.out.println("acquired 4:" + acquire());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static boolean acquire() throws Exception {
        String key = "ip:" + System.currentTimeMillis() / 1000; //此处将当前时间戳取秒数
        String limit = "3"; //限流大小
        Object obj = jedis.eval(limitRate, Lists.newArrayList(key), Lists.newArrayList(limit));
        return (Long) obj == 1;
    }

}
