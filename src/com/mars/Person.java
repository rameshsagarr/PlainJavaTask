package com.mars;

public class Person {
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	@Override
	public String toString() {
		return "Person [pid=" + pid + ", firstname=" + firstname + ", surname=" + surname + "]";
	}
	public Person(int pid, String firstname, String surname) {
		super();
		this.pid = pid;
		this.firstname = firstname;
		this.surname = surname;
	}
	public Person() {
		
		
	}
	private int pid;
	private String firstname;
	private String surname;

}
