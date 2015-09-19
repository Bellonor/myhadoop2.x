package com.jamesfen.jvm;

import java.util.ArrayList;
import java.util.List;

public class MemoryLeakDemo {
	public static void main(String[] args) {  
		  
        List<List> list = new ArrayList<List>();  
        List<String> list2 = new ArrayList<String>();
        while (true) {
        	List<Integer> li=new ArrayList<Integer>();
        	li.add(1000000);
        	list.add(li);
            list2.add("OutOfMemoryError soon");  
        }  
    }  
}
