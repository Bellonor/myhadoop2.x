package com.jamesfen.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisSQL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jedis  redis = new Jedis ("192.168.58.101",6379);//连接redis 
		hashops(redis);
		
	}
	//模糊查询
	public static void likequery(Jedis redis){
		
	}
	
	// Hash 操作
	public static void hashops(Jedis redis){
		//HSET key field value将哈希表key中的域field的值设为value。   
        redis.hset("website", "google", "www.google.cn");   
        redis.hset("website", "baidu", "www.baidu.com");   
        redis.hset("website.org", "sina", "www.sina.com");  
        redis.hset("website", "sina", "www.sina.com1"); 
        redis.hset("websitg.cn", "sina", "www.sina.com"); 
        //redis.hscan("", "", "");
        Set<String> keys = redis.keys("websit*.*");
        //查询多个key
        List<String> lis= redis.hmget("website", "sina","goog");
        //HMSET key field value [field value ...] 同时将多个field - value(域-值)对设置到哈希表key中。   
        Map map = new HashMap();   
        map.put("cardid", "123456");   
        map.put("username", "jzkangta");   
        redis.hmset("hash", map);   

        //HGET key field返回哈希表key中给定域field的值。   
        System.out.println(redis.hget("hash", "username"));   

        //HMGET key field [field ...]返回哈希表key中，一个或多个给定域的值。   
        List list = redis.hmget("website","google","baidu","sina");   
        for(int i=0;i<list.size();i++){   
            System.out.println(list.get(i));   
        }   

        //HGETALL key返回哈希表key中，所有的域和值。   
        Map<String,String> map1 = redis.hgetAll("hash");   
        for(Map.Entry entry: map1.entrySet()) {   
             System.out.print(entry.getKey() + ":" + entry.getValue() + "\t");   
        }   

        //HDEL key field [field ...]删除哈希表key中的一个或多个指定域。   
        //HLEN key 返回哈希表key中域的数量。   
        //HEXISTS key field查看哈希表key中，给定域field是否存在。   
        //HINCRBY key field increment为哈希表key中的域field的值加上增量increment。   
        //HKEYS key返回哈希表key中的所有域。   
        //HVALS key返回哈希表key中的所有值。  
	}

}
