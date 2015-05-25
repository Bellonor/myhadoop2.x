package com.jamesfen.reflect;

public class Person {
	public Person() {

	}

	public Person(String name) {
		this.name = name;
	}

	public Person(int age) {
		this.age = age;
	}

	public Person(String name, int age) {
		this.age = age;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "[" + this.name + "  " + this.age + "]";
	}

	private String name;
	private int age;
	public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public void sayChina(){
        System.out.println("hello ,china");
    }
    
    public void sayHello(String name, int age){
        System.out.println(name+"  "+age);
    }
    private String sex;
}
