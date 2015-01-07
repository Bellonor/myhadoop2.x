package com.jamesfen.redis;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class HelloRedis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jedis  redis = new Jedis ("192.168.58.101",6379);//连接redis  
		redis.set("name", "wangjun1"); 
        redis.set("id", "123456"); 
        redis.set("address", "guangzhou"); 
        
        Set keys = redis.keys("*");//列出所有的key，查找特定的key如：redis.keys("foo") 
        Iterator t1=keys.iterator(); 
        while(t1.hasNext()){ 
            Object obj1=t1.next(); 
            System.out.println(obj1+"   "+redis.get((String) obj1)); 
        }
        
        
	}

}
