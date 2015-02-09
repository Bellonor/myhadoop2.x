package com.jamesfen.thrift;
import org.apache.thrift.TException;
public class AdditionServiceHandler implements AdditionService.Iface{
	
	@Override
	public int add(int n1, int n2) throws TException {
	  return n1 + n2;
	
	}
}
