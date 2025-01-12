package com.acc.model;

public class Student {

	int stuid;
	String studName;
	int Studage;
	String deptName;
	double marks;

	public Student() {

		stuid = 0;
		studName = "";
		Studage = 0;
		deptName = "";
		marks = 0;
	}

	@Override
	public String toString() {
		return "Student [stuid=" + stuid + ", studName=" + studName + ", Studage=" + Studage + ", deptName=" + deptName
				+ ", marks=" + marks + "]";
	}

	public Student(int stuid, String studName, int studage, String deptName, double marks) {
		super();
		this.stuid = stuid;
		this.studName = studName;
		Studage = studage;
		this.deptName = deptName;
		this.marks = marks;
	}

	public int getStuid() {
		return stuid;
	}

	public void setStuid(int stuid) {
		this.stuid = stuid;
	}

	public String getStudName() {
		return studName;
	}

	public void setStudName(String studName) {
		this.studName = studName;
	}

	public int getStudage() {
		return Studage;
	}

	public void setStudage(int studage) {
		Studage = studage;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public double getMarks() {
		return marks;
	}

	public void setMarks(double marks) {
		this.marks = marks;
	}

}
