package com.jamesfen.zookeeper;
import java.net.InetAddress;
import org.I0Itec.zkclient.ZkClient;
public class ServiceAProvider {

	 private String serviceName = "service-A";
	   
	    //向zookeeper注册服务
	    public void init() throws Exception{
	        String serverList = "192.168.58.11:2181";
	        String PATH ="/configcenter";//根节点路径
	        ZkClient zkClient = new ZkClient(serverList);
	        boolean rootExists = zkClient.exists(PATH);
	        if(!rootExists){
	            zkClient.createPersistent(PATH);
	        }
	      //判断是否存在，不存在则创建服务节点
	        boolean serviceExists = zkClient.exists(PATH + "/" + serviceName);
	        if(!serviceExists){
	            zkClient.createPersistent(PATH + "/" + serviceName);
	        }
	        
	        //註冊當前服務
	        InetAddress addr =InetAddress.getLocalHost();
	        //String ip= addr.getHostAddress().toString();
	        String ip = "192.168.58.130";
	        
	        //創建當前服務器節點
	        zkClient.createEphemeral(PATH + "/" + serviceName + "/" + ip);
	        
	        System.out.println("提供的服务节点名称为："+PATH + "/" + serviceName + "/" + ip);
	    }
	    //提供服务
	    public void provide(){
	        
	    }
	    public static void main(String[]args) throws Exception {
	        ServiceAProvider service = new ServiceAProvider();
	        service.init();
	        
	        Thread.sleep(1000*60*60*24);
	    }

}
