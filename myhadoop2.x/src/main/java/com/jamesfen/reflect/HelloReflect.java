package com.jamesfen.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class HelloReflect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Class<?> demo = null;
		try {
			demo = Class.forName("com.jamesfen.reflect.Person");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Constructor<?> cons[] = demo.getConstructors();
		Person per = null;

		try {
			per = (Person) demo.newInstance();
			try {
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		per.setAge(33);
		per.setName("ddd");
		System.out.print(per);
		
		
		// 取得本类的全部属性
        Field[] field = demo.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            // 权限修饰符
            int mo = field[i].getModifiers();
            String priv = Modifier.toString(mo);
            // 属性类型
            Class<?> type = field[i].getType();
            System.out.println(priv + " " + type.getName() + " "
                    + field[i].getName() + ";");
        }
        //利用反射调用Person类中的sayChina方法
        try {
        	//调用sayHello方法，无参
			Method method=demo.getMethod("sayChina");
			method.invoke(demo.newInstance());
			//调用 sayHello
			method=demo.getMethod("sayHello", String.class,int.class);
			method.invoke(demo.newInstance(), "king kong",20);
			
		} catch (Exception  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
