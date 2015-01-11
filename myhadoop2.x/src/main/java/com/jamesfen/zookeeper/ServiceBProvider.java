package com.jamesfen.zookeeper;
import java.net.InetAddress;
import org.I0Itec.zkclient.ZkClient;
public class ServiceBProvider {
   //服务名仍然为 A,这样是为了，一个服务名有两个台机器在服务，才能做负载均衡.
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
        
        boolean serviceExists = zkClient.exists(PATH + "/" + serviceName);
        if(!serviceExists){
            zkClient.createPersistent(PATH + "/" + serviceName);//創建服務節點
        }
        
        //註冊當前服務
        InetAddress addr =InetAddress.getLocalHost();
        //String ip= addr.getHostAddress().toString();
        String ip = "192.168.58.131";
        
        //創建當前服務器節點
        zkClient.createEphemeral(PATH + "/" + serviceName + "/" + ip);
        
        System.out.println("提供的服务节点名称为："+PATH + "/" + serviceName + "/" + ip);
    }
    //提供服务
    public void provide(){
        
    }
    public static void main(String[]args) throws Exception {
        ServiceBProvider service = new ServiceBProvider();
        service.init();
        
        Thread.sleep(1000*60*60*24);
    }

}
