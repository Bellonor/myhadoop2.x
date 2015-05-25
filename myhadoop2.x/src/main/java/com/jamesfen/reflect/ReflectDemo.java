package com.jamesfen.reflect;

class Demo {
	// other codes...
	int t = 0;

	public void dd() {

	}
}

public class ReflectDemo {

	/**
	 * 通过一个对象获得完整的包名和类名
	 * */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo demo = new Demo();
		System.out.println(demo.getClass().getName());
		Class<?> demo1=null;
        Class<?> demo2=null;
        Class<?> demo3=null;
        try{
            //一般尽量采用这种形式
            demo1=Class.forName("com.jamesfen.reflect.Demo");
            
        }catch(Exception e){
            e.printStackTrace();
        }
        demo2=new Demo().getClass();
        demo3=Demo.class;
         
        System.out.println("类名称   "+demo1.getName());
        System.out.println("类名称   "+demo2.getName());
        System.out.println("类名称   "+demo3.getName());
		
	}

}
