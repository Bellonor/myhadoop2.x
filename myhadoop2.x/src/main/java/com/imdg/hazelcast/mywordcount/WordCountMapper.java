package com.imdg.hazelcast.mywordcount;

import java.util.StringTokenizer;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

public class WordCountMapper implements Mapper<String,String,String,Integer>{

	private static final Integer one=Integer.valueOf(1);
	@Override
	public void map(String key, String value, Context<String,Integer> context) {
		// TODO Auto-generated method stub
		StringTokenizer word=new StringTokenizer(value.toLowerCase());
		while(word.hasMoreTokens()){
			String str=word.nextToken();
			context.emit(str.toLowerCase(), one);
		}
	}

}
