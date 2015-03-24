package com.jamesfen.hive;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jamesfen.jdbc.MysqlJDBC;


public class HiveJDBC{
	private static final Logger LOG = LoggerFactory.getLogger(HiveJDBC.class);
	//获得驱动
	private static String DRIVER = "org.apache.hive.jdbc.HiveDriver";
	//获得url
	private static String URL = "jdbc:hive2://192.168.58.101:10000/default";
	//获得连接数据库的用户名
	private static String USER = "root";
	//获得连接数据库的密码
	private static String PASS ="#softstone";
	//连接对象
	private Connection connection;
	//维护一个本类型的对象
	private static HiveJDBC HiveJDBC;
	//Statement对象,可以执行SQL语句并返回结果
	private Statement stmt;
	
	//私有构造器
	private HiveJDBC() {
		getconnection();
	}
	private void getconnection() {
		try {
/*			DRIVER=Config.getIni().getSectionProperty("hivejdbc", "hive.jdbc.driver2");
			URL=Config.getIni().getSectionProperty("hivejdbc", "hive.jdbc.url2");
			USER=Config.getIni().getSectionProperty("hivejdbc", "hive.jdbc.username");
			PASS=Config.getIni().getSectionProperty("hivejdbc", "hive.jdbc.password");
			//初始化JDBC驱动并让驱动加载到jvm中
*/			Class.forName(DRIVER);
			//创建数据库连接
			connection = DriverManager.getConnection(URL, USER, PASS);
			//创建Statement对象
			stmt = connection.createStatement();
		} catch (Exception e) {
			LOG.error("请检查hive jdbc端口是否开通.hive jdbc connection happen error:" + e.toString());
		}
	}
	//提供一个静态方法返回本类的实例
	public static HiveJDBC getHiveJDBC() {
		//如果本类所维护HiveJDBC属性为空,则调用私有的构造器获得实例
		if (HiveJDBC == null) {
			HiveJDBC = new HiveJDBC();
		}
		return HiveJDBC;
	}
	
	/*
	 * 执行一句查询的sql
	 */
	public ResultSet executeQuery(String sql) {
		try {
			//利用Statement对象执行参数的sql
			if(null==connection||null==stmt||stmt.isClosed()){
				getconnection();
			}
			
			ResultSet result = stmt.executeQuery(sql);
			return result;
		} catch (Exception e) {
			LOG.error("hive sql  happen error:" + e.toString());
			return null;
		}
	}
	
	//执行单句INSERT、UPDATE 或 DELETE 语句, 如果执行INSERT时, 返回主键
	public int executeUpdate(String sql) throws SQLException {
		if(null==connection||null==stmt||stmt.isClosed()){
			getconnection();
		}
		int result = -1;
		try {
			//执行SQL语句
			stmt.executeUpdate(sql);
			//获得主键
			ResultSet rs = stmt.getGeneratedKeys();
			while(rs.next()) {
				//返回最后一个主键
				result = rs.getInt(1);
				
			}
			rs.close();
			return result;
		} catch (Exception e) {
			LOG.error(" insert hive sql  happen error:" + e.toString());
			return -1;
		}
	}
	//method2：关闭数据库的方法
    public void closeConn(){
        
        if(stmt!=null){
            try {
            	stmt.close();
            } catch (SQLException e) {
            	LOG.error(" close hive jdbc  happen error:" + e.toString());
            }
        }
        if(connection!=null){
            try {
            	connection.close();
            } catch (SQLException e) {
            	LOG.error(" close hive jdbc  happen error:" + e.toString());
            }
        }
    }
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		HiveJDBC hivejdbc=getHiveJDBC();
		String sql="select key,disc_name from bigtable_bd";
		ResultSet rs=hivejdbc.executeQuery(sql);
		int i=0;
		while(rs.next()){
			//返回最后一个主键
			String name = rs.getString(1).trim();
			String datatype = rs.getString(2).trim();
			i++;
			System.out.println("第"+i+"行---"+name+":"+datatype);
		}
	}
}
