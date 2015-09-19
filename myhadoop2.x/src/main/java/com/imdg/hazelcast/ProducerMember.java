package com.imdg.hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;

public class ProducerMember {
	public static void main(String[] args) throws Exception {
		HazelcastInstance hz = Hazelcast.newHazelcastInstance();
		IQueue<Integer> queue = hz.getQueue("queue");
		for (int k = 1; k < 100; k++) {
			queue.put(k);
			System.out.println("Producing: " + k);
			Thread.sleep(1000);
		}
		queue.put(-1);
		System.out.println("Producer Finished!");
	}
}
