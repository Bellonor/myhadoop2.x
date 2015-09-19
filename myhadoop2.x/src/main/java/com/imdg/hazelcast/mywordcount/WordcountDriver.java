package com.imdg.hazelcast.mywordcount;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;


import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.hazelcast.config.Config;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ICompletableFuture;
import com.hazelcast.core.IMap;
import com.hazelcast.mapreduce.Job;
import com.hazelcast.mapreduce.JobTracker;
import com.hazelcast.mapreduce.KeyValueSource;
import com.imdg.hazelcast.wordcount.ToStringPrettyfier;
import com.imdg.hazelcast.wordcount.TokenizerMapper;
import com.imdg.hazelcast.wordcount.WordcountReducerFactory;
import com.kenai.jffi.Array;



public class WordcountDriver {

	private static String path="/resources/word.txt";
	private static String path2="/resources/word2.txt";
	private static final String MAP_NAME = "articles";
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		try {
			HazelcastInstance hazelcastInstance= builderNodes(3);
			reader(hazelcastInstance, path);
			reader(hazelcastInstance, path2);
			//新建一个jobtracker
			JobTracker jobTracker=hazelcastInstance.getJobTracker("default");
			
			IMap<String,String> map=hazelcastInstance.getMap(MAP_NAME);
			KeyValueSource<String,String> source=KeyValueSource.fromMap(map);
			Job<String,String> job=jobTracker.newJob(source);
			ICompletableFuture<Map<String,Integer>> future=job
					.mapper(new WordCountMapper())
					.reducer(new WordCountReducerFactory())
					.submit();
			
			
			 System.out.println(ToStringPrettyfier.toString(future.get()));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//Hazelcast.shutdownAll();
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			//Hazelcast.shutdownAll();
			e.printStackTrace();
		}finally{
			//Hazelcast.shutdownAll();
		}
	}
    
	private static HazelcastInstance builderNodes(int numbers){
		
		
		Config config = new Config();
        NetworkConfig networkConfig = config.getNetworkConfig();
        networkConfig.getJoin().getMulticastConfig().setEnabled(false);
        networkConfig.getJoin().getTcpIpConfig().setEnabled(true);
        networkConfig.getJoin().getTcpIpConfig().setMembers(Arrays.asList(new String[]{"127.0.0.1"}));

        HazelcastInstance[] hazelcastInstances = new HazelcastInstance[numbers];
        for (int i = 0; i < numbers; i++) {
            hazelcastInstances[i] = Hazelcast.newHazelcastInstance(config);
        }
        return hazelcastInstances[0];
	}
	private static void reader(HazelcastInstance hazelcastinstance,String path){
		try { 
            //File csv = new File("C://writers.csv"); // CSV文件
			IMap<String,String> imap=hazelcastinstance.getMap(MAP_NAME);
			
			InputStream in = WordcountDriver.class.getResourceAsStream(  
	                path);// 读取流文件
			//File csv = new File(ss); // CSV文件
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            // 读取直到最后一行 
            String line = ""; 
            //br.readLine();//跳过表头
            StringBuilder sb=new StringBuilder();
            while ((line = br.readLine()) != null) { 
                // 把一行数据分割成多个字段 
                sb.append(line).append("\n");
            	
            }
            imap.put(path, sb.toString());
            br.close();
            
        } catch (FileNotFoundException e) { 
            // 捕获File对象生成时的异常 
             
        } catch (IOException e) { 
            // 捕获BufferedReader对象关闭时的异常 
             
        } 
	}
}
