package com.mars;

public class Person {
	
	private int pid;
	public int getpid() {
		return pid;
	}
	public void setpid(int pid) {
		this.pid = pid;
	}
	@Override
	public String toString() {
		return "Student [pid=" + pid + ", pname=" + pname + "]";
	}
	public String getpname() {
		return pname;
	}
	public Person(int pid, String pname) {
		super();
		this.pid = pid;
		this.pname = pname;
	}
	public void setpname(String pname) {
		this.pname = pname;
	}
	private String pname;

}
