package com.jamesfen.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jamesfen.hbase.HBaseDao;

public class GenHiveTable {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		MysqlJDBC sqljdbc=MysqlJDBC.getHiveJDBC();
		String sql="select t.eName,t.type, count(distinct t.eName) from t_tag_meta t  group by t.eName";
		ResultSet rs=sqljdbc.executeQuery(sql);
		int i=1;
		StringBuilder sb1=new StringBuilder();
		StringBuilder sb2=new StringBuilder();
		String head="CREATE EXTERNAL TABLE bigtable_bd ("
				+ "\nKEY string,\n";
		String middle="\n) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES (" 
				+"\n \"hbase.columns.mapping\" = \":key,\n";
		String tail="\n) TBLPROPERTIES ("
                    +"\n \"hbase.table.name\" = \"bigtable\");";
		//为hbase增加不存在的字段
		Map<String, Double> mapd=new HashMap<String, Double>();
		Map<String, Integer> mapi=new HashMap<String, Integer>();;
		Map<String, String> maps=new HashMap<String, String>();
		while(rs.next()){
			//返回最后一个主键
			String name = rs.getString(1).trim();
			String datatype = rs.getString(2).trim();
			i++;
			//System.out.println("第"+i+"行---"+name+":"+datatype);
			Pattern pattern = Pattern.compile("(\\d+)");
	        Matcher matcher = pattern.matcher(datatype);
	        int precision = 0;
	        if (matcher.find()) {
	            precision = Integer.valueOf(matcher.group(1));
	        }
			
			try {
				if(datatype.contains("VARCHAR2")){
					sb1.append(name+" STRING,");
					sb2.append("bd:"+name+",");
					maps.put(name, "none");
				}else if(datatype.contains("DATE")){
					sb1.append(name+" STRING,");
					sb2.append("bd:"+name+",");
					maps.put(name, "none");
				}
				else if(datatype.contains("NUMBER")){
					if(datatype.contains(",")){
						sb1.append(name+" DOUBLE,");
						sb2.append("bd:"+name+"#b,");
						mapd.put(name, 0.00);
					}else {
						if (precision >= 10) {
							sb1.append(name+" DOUBLE,");
							sb2.append("bd:"+name+"#b,");
							mapd.put(name, 0.00);
	                    } else {
	                    	sb1.append(name+" INT,");
							sb2.append("bd:"+name+"#b,");
							mapi.put(name, 0);
	                    }
					}
				}
				sb1.append("\n");
				sb2.append("\n");
			} catch (Exception e) {
				
				//e.printStackTrace();
			}
		}
		String tablename="bigtable";
		String rowKey="00009";
		String family="bd";
		//HBaseDao.writeRow(tablename, rowKey, family, maps);
		//HBaseDao.writeRowForInt(tablename, rowKey, family, mapi);
		//HBaseDao.writeRowForDouble(tablename, rowKey, family, mapd);
		String str1="";
		String str2="";
		if(sb1.toString().endsWith(",\n")){
			 str1=sb1.toString().substring(0,sb1.toString().length()-2);
		}
		if(sb2.toString().endsWith(",\n")){
			 str2=sb2.toString().substring(0,sb2.toString().length()-2)+"\"";
		}
		String tablesql=head+str1+middle+str2+tail;
		System.out.println(tablesql);
		
	}

}
