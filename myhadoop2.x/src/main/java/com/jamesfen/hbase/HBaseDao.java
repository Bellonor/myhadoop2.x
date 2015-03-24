package com.jamesfen.hbase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
public class HBaseDao {
	private static Configuration conf = null;
    private static final long  writeBuffer=1024*1024*48;
    private static final boolean  wal=false;
    private static final boolean  autoFlush=true;
	// 初始化配置
	static {
		conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "10.17.36.121");
		conf.set("dfs.socket.timeout", "1800000000");
	}

	// 1、建表
	public static void createTable(String tablename, String[] cfs)
			throws IOException {
		HBaseAdmin admin = new HBaseAdmin(conf);
		if (admin.tableExists(tablename)) {
			System.out.println("表已经存在！");
		} else {
			HTableDescriptor tableDesc = new HTableDescriptor(tablename);
			for (int i = 0; i < cfs.length; i++) {
				// 表建好后，列簇不能动态增加，而列是可以动态增加的，这是hbase伸缩性的一个体现。
				tableDesc.addFamily(new HColumnDescriptor(cfs[i]));
			}
			admin.createTable(tableDesc);
			System.out.println("表创建成功！");
		}
	}

	/** ----- 插入数据---------------------------------------------- */
	/**
	 * 2、插入多条数据,指定表，指定rowkey,指定列族，指定列，值
	 * 
	 * @param tablename
	 * @param cfs
	 */
	public static void writeRow(String tablename, String rowKey, String family,
			Map<String, String> map) {
		if (null == map || map.isEmpty()) {
			return;
		}
		try {
			HTable table = new HTable(conf, tablename);
			table.setAutoFlush(autoFlush);
			table.setWriteBufferSize(writeBuffer);
			Put put = new Put(Bytes.toBytes(rowKey));// 指定rowkey
			Iterator<Entry<String, String>> iter = map.entrySet().iterator();
			Entry<String, String> entry;
			while (iter.hasNext()) {
				entry = iter.next();
				put.add(Bytes.toBytes(family), // 指定列族
						Bytes.toBytes(entry.getKey()), // 指定列
						Bytes.toBytes(entry.getValue())); // 指定列的值

			}
			put.setWriteToWAL(wal);
			table.put(put);
			System.out.println("插入数据成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeRowForInt(String tablename, String rowKey,
			String family, Map<String, Integer> map) {
		try {
			HTable table = new HTable(conf, tablename);
			Put put = new Put(Bytes.toBytes(rowKey));// 指定rowkey
			Iterator<Entry<String, Integer>> iter = map.entrySet().iterator();
			Entry<String, Integer> entry;
			while (iter.hasNext()) {
				entry = iter.next();
				put.add(Bytes.toBytes(family), // 指定列族
						Bytes.toBytes(entry.getKey()), // 指定列
						Bytes.toBytes(entry.getValue())); // 指定列的值
				table.put(put);
				System.out.println("插入数据成功");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeRowForDouble(String tablename, String rowKey,
			String family, Map<String, Double> map) {
		try {
			HTable table = new HTable(conf, tablename);
			Put put = new Put(Bytes.toBytes(rowKey));// 指定rowkey
			Iterator<Entry<String, Double>> iter = map.entrySet().iterator();
			Entry<String, Double> entry;
			while (iter.hasNext()) {
				entry = iter.next();
				put.add(Bytes.toBytes(family), // 指定列族
						Bytes.toBytes(entry.getKey()), // 指定列
						Bytes.toBytes(entry.getValue())); // 指定列的值
				table.put(put);
				System.out.println("插入数据成功");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// add a data
	/**
	 * 插入一条
	 * 
	 * @param tableName
	 * @param rowKey
	 * @param family
	 *            列族
	 * @param qualifier
	 *            列
	 * @param value
	 *            值
	 */
	public static void insertData(String tableName, String rowKey,
			String family, String qualifier, String value) {
		try {
			HTable table = new HTable(conf, tableName);
			Put put = new Put(Bytes.toBytes(rowKey));
			put.add(Bytes.toBytes(family), Bytes.toBytes(qualifier),
					Bytes.toBytes(value));
			table.put(put);
			System.out.println("insert a data successful!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
// copy 一行数据
	public static void copyOneRow(String tablename, String rowKey) throws IOException
	{
		HTable table = new HTable(conf, tablename);
		Get g = new Get(rowKey.getBytes());
        Result result = table.get(g);
        Put put = new Put(Bytes.toBytes("ALL_201411"));

        NavigableMap<byte[], NavigableMap<byte[], byte[]>> familyQualifierMap = result.getNoVersionMap();
        for (byte[] familyBytes : familyQualifierMap.keySet()) {
            NavigableMap<byte[], byte[]> qualifierMap = familyQualifierMap.get(familyBytes);

            for (byte[] qualifier : qualifierMap.keySet()) {
                put.add(familyBytes, qualifier, qualifierMap.get(qualifier));
            }            
        }            
        table.put(put);
        table.flushCommits();
        table.close();
		
	}
	/** ----- 查询---------------------------------------------- */
	// 4、查找一行数据
	public static void selectRow(String tablename, String rowKey)
			throws IOException {
		HTable table = new HTable(conf, tablename);
		Get g = new Get(rowKey.getBytes());

		Result rs = table.get(g);
		for (KeyValue kv : rs.raw()) {
			System.out.print(new String(kv.getRow()) + "  ");// 行号
			System.out.print(new String(kv.getFamily()) + ":");// 列簇名
			System.out.print(new String(kv.getQualifier()) + "  ");// 列名
			System.out.print(kv.getTimestamp() + "  ");// 时间戳
			System.out.println(new String(kv.getValue()));// 单元格的值
		}
	}

	/**
	 * 根据rowkey,一行中的某一列簇查询一条数据
	 * 
	 * @throws IOException
	 */
	public static void getFamily(String tableName, String rowkey, String family)
			throws IOException {
		System.out.println("根据rowkey和列簇查询HBase表");
		HTable table = new HTable(conf, tableName);
		try {
			Get get = new Get(rowkey.getBytes());
			get.addFamily(family.getBytes()); // 根据主键查询某列簇
			Result r = table.get(get);
			for (KeyValue kv : r.raw()) {
				System.out.println("row:" + new String(kv.getRow()));
				System.out.println("family:"
						+ new String(kv.getFamily(), "utf-8"));
				System.out.println("value:"
						+ new String(kv.getValue(), "utf-8"));
				System.out.println("qualifier:" + new String(kv.getQualifier())
						+ "\n");
				String timestampFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:MM:ss").format(new Date(kv
						.getTimestamp()));
				System.out.println("Timestamp:" + timestampFormat);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询某列数据的多个版本
	 * 
	 * @tableName 表名
	 * 
	 * @rowKey rowKey
	 * 
	 * @familyName 列族名
	 * 
	 * @columnName 列名
	 */
	public static void getResultByVersion(String tableName, String rowKey,
			String familyName, String columnName) throws IOException {
		HTable table = new HTable(conf, tableName);
		Get get = new Get(Bytes.toBytes(rowKey));
		get.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName));
		get.setMaxVersions(5);
		Result result = table.get(get);
		for (KeyValue kv : result.list()) {
			System.out.println("family:" + Bytes.toString(kv.getFamily()));
			System.out
					.println("qualifier:" + Bytes.toString(kv.getQualifier()));
			System.out.println("value:" + Bytes.toString(kv.getValue()));
			System.out.println("Timestamp:" + kv.getTimestamp());
			System.out.println("-------------------------------------------");
		}
		/*
		 * List<?> results = table.get(get).list(); Iterator<?> it =
		 * results.iterator(); while (it.hasNext()) {
		 * System.out.println(it.next().toString()); }
		 */
	}

	/**
	 * 查询一张表的数据
	 * 
	 * @param tableName
	 */
	public static void queryAll(String tableName) {
		try {
			HTable table = new HTable(conf, tableName);
			Scan scan = new Scan();
			ResultScanner scanner = table.getScanner(scan);

			for (Result row : scanner) {
				System.out.println("\nRowkey: " + new String(row.getRow()));
				for (KeyValue kv : row.raw()) {
					System.out.print(new String(kv.getRow()) + " "); // same as
																		// above
					System.out.print(new String(kv.getFamily()) + ":");
					String field=new String(kv.getQualifier());
					System.out.print( field+ " = ");
					/*String val = DataType.bytetoString(
							new String(kv.getQualifier()), kv.getValue());*/
					System.out.print(new String(kv.getValue()));
					/*if("MONTH_ID".equals(field)){
						System.out.print(Bytes.toInt(kv.getValue()));
					}else{
						
					}*/
					
					
					String timestampFormat = new SimpleDateFormat(
							"yyyy-MM-dd HH:MM:ss").format(new Date(kv
							.getTimestamp()));
					System.out.print(" timestamp = " + timestampFormat + "\n");
				}
			}
			table.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询rowkey的数据
	 */
	public static void queryByRowKey(String tableName, String rowKey) {
		try {
			HTable table = new HTable(conf, tableName);
			Get get = new Get(rowKey.getBytes());
			Result row = table.get(get);
			for (KeyValue kv : row.raw()) {
				System.out.print(new String(kv.getRow()) + " ");
				System.out.print(new String(kv.getFamily()) + ":");
				System.out.print(new String(kv.getQualifier()) + " = ");
				System.out.print(new String(kv.getValue()));
				System.out.print(" timestamp = " + kv.getTimestamp() + "\n");
			}
			table.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** ----- -----------修改---------------------------------------------- */
	/**
	 * 更新表中的某一列
	 * 
	 * @tableName 表名
	 * 
	 * @rowKey rowKey
	 * 
	 * @familyName 列族名
	 * 
	 * @columnName 列名
	 * 
	 * @value 更新后的值
	 */
	public static void updateTable(String tableName, String rowKey,
			String familyName, String columnName, String value)
			throws IOException {
		HTable table = new HTable(conf, tableName);
		Put put = new Put(Bytes.toBytes(rowKey));
		put.add(Bytes.toBytes(familyName), Bytes.toBytes(columnName),
				Bytes.toBytes(value));
		table.put(put);
		System.out.println("update table Success!");
	}

	/** ----- 删除---------------------------------------------- */

	/**
	 * 删除表
	 * 
	 * @param tablename
	 *            表名
	 * @throws IOException
	 */
	public static void deleteTable(String tablename) throws IOException {
		try {
			HBaseAdmin admin = new HBaseAdmin(conf);
			admin.disableTable(tablename);
			admin.deleteTable(tablename);
			System.out.println("表删除成功！");
		} catch (MasterNotRunningException e) {
			e.printStackTrace();
		} catch (ZooKeeperConnectionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除一行数据
	 * 
	 * @param tablename
	 * @param rowkey
	 * @throws IOException
	 */
	public static void deleteRow(String tablename, String rowkey)
			throws IOException {
		HTable table = new HTable(conf, tablename);
		List list = new ArrayList();
		Delete d1 = new Delete(rowkey.getBytes());
		list.add(d1);
		table.delete(list);
		System.out.println("删除行成功！");
	}

	/**
	 * 删除指定的列
	 * 
	 * @tableName 表名
	 * 
	 * @rowKey rowKey
	 * 
	 * @familyName 列族名
	 * 
	 * @columnName 列名
	 */
	public static void deleteColumn(String tableName, String rowKey,
			String falilyName, String columnName) throws IOException {
		HTable table = new HTable(conf, tableName);
		Delete deleteColumn = new Delete(Bytes.toBytes(rowKey));
		deleteColumn.deleteColumns(Bytes.toBytes(falilyName),
				Bytes.toBytes(columnName));
		table.delete(deleteColumn);
		System.out.println(falilyName + ":" + columnName + "is deleted!");
	}

	public static void main(String[] args) throws IOException {

		HBaseDao.queryByRowKey("bigtable","00009");
		//HBaseDao.queryAll("bigtable");
		// queryByRowKey("mytest","1001");
		//copyOneRow("areatag","ALL_201408");
    }
	
/*	 Map<String,Integer> map=new HashMap<String,Integer>();
     map.put("VARIETY_CNT", 120);
     map.put("AMUSE_CNT", 145);
     map.put("EDU_CNT", 67);
     map.put("CATOON_CNT", 299);
      writeRowForInt("bigtable", "1026618", "bd", map);
       
     map.clear();
     map.put("WEIXIN_CNT", 140);
     map.put("YIXIN_CNT", 150);
     map.put("CYBER_BANK_CNT", 88);
     map.put("THIRD_PART_PAY_CNT", 149);
     map.put("OTHER_SELLER_CNT", 150);
     
     writeRowForInt("bigtable", "1028308", "bd", map);
     map.clear();
     map.put("WEIXIN_CNT", 160);
     map.put("YIXIN_CNT", 175);
     map.put("CYBER_BANK_CNT", 151);
     map.put("THIRD_PART_PAY_CNT", 242);
     map.put("OTHER_SELLER_CNT", 66);
     writeRowForInt("bigtable", "1028767", "bd", map);*/
}
