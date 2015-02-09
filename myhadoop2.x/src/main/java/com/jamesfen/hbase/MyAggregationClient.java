package com.jamesfen.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.coprocessor.AggregationClient;
import org.apache.hadoop.hbase.client.coprocessor.LongColumnInterpreter;
import org.apache.hadoop.hbase.coprocessor.ColumnInterpreter;
import org.apache.hadoop.hbase.util.Bytes;

public class MyAggregationClient {

	private static final byte[] TABLE_NAME = Bytes.toBytes("bigtable1w");
	private static final byte[] CF = Bytes.toBytes("bd");
	public static void main(String[] args) throws Throwable {
	Configuration customConf = new Configuration();
	customConf.set("hbase.zookeeper.quorum",
	"192.168.58.101");
	//提高RPC通信时长
	customConf.setLong("hbase.rpc.timeout", 600000);
	//设置Scan缓存
	customConf.setLong("hbase.client.scanner.caching", 1000);
	Configuration configuration = HBaseConfiguration.create(customConf);
	AggregationClient aggregationClient = new AggregationClient(
	configuration);
	Scan scan = new Scan();
	//指定扫描列族，唯一值
	scan.addFamily(CF);
	//long rowCount = aggregationClient.rowCount(TABLE_NAME, null, scan);
	long rowCount = aggregationClient.rowCount(TableName.valueOf("bigtable1w"), new LongColumnInterpreter(), scan);
	System.out.println("row count is " + rowCount);

	}
	


}
