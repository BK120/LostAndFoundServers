package com.lostandfound.beans;

/**
 * 用户类
 * 
 * @author lee
 *
 */
public class User {
	private String phone;// 用户电话即帐号
	private String password;// 用户密码
	private String name;// 用户姓名
	private String sex;// 性别
	private String address;// 常驻地址

	public User() {
	}

	public User(String phone, String password, String name, String sex,
			String address) {
		super();
		this.phone = phone;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [phone=" + phone + ", password=" + password + ", name="
				+ name + ", sex=" + sex + ", address=" + address + "]";
	}

}
