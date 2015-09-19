package com.imdg.hazelcast.mywordcount;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

public class WordCountReducerFactory implements ReducerFactory<String, Integer, Integer>{

	@Override
	public Reducer<Integer, Integer> newReducer(String key) {
		// TODO Auto-generated method stub
		return new WordCountReduce();
	}
	private static class WordCountReduce extends Reducer<Integer,Integer>{

		private volatile int count;
		@Override
		public void reduce(Integer value) {
			// TODO Auto-generated method stub
			count=count+value;
		}

		@Override
		public Integer finalizeReduce() {
			// TODO Auto-generated method stub
			return count==0?null:count;
		}
		
	}
	

}
