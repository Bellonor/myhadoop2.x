package com.jamesfen.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZkNodeOperation {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		//String zkServer = "192.168.58.13:2181";

		ZooKeeper zk = new ZooKeeper("192.168.58.11:" + 2181, 3000,
				new Watcher() {
					// 监控所有被触发的事件
					public void process(WatchedEvent event) {
						System.out.println("已经触发了" + event.getType() + "事件！");
					}
				});
		// 创建一个目录节点
		zk.create("/panguoyuan-dir", "panguoyuan-data".getBytes(),
				Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		// 创建一个子目录节点
		zk.create("/panguoyuan-dir/childone", "childone".getBytes(),
				Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println(new String(zk
				.getData("/panguoyuan-dir", false, null)));
		// 取出子目录节点列表
		System.out.println(zk.getChildren("/panguoyuan-dir", true));
		// 修改子目录节点数据
		zk.setData("/panguoyuan-dir/childone", "modifyDataOne".getBytes(), -1);
		System.out.println("目录节点状态：[" + zk.exists("/panguoyuan-dir", true)
				+ "]");
		// 创建另外一个子目录节点
		zk.create("/panguoyuan-dir/childtwo", "childtwo".getBytes(),
				Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println(new String(zk.getData("/panguoyuan-dir/childtwo",
				true, null)));
		// 删除子目录节点
		zk.delete("/panguoyuan-dir/childtwo", -1);
		zk.delete("/panguoyuan-dir/childone", -1);
		// 删除父目录节点
		zk.delete("/panguoyuan-dir", -1);
		// 关闭zk连接
		zk.close();
	}

}
