import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/*
 * Creation : 2016年8月3日
 */

public class JedisTest {

    @Test
    public void testJedis() {
        Jedis jedis = new Jedis("192.168.18.131", 6379);
        Set<String> values = jedis.keys("REDIS_USER_SESSION*");
        jedis.close();
        System.out.println(values.size());
        for (String s : values) {
            System.out.println(s);
        }
    }
}
