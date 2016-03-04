package com.matroskeen.beans;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class User {
	
	private int id;
	private String nickName;
	private String email;
	private String password;
	private String avatar;
	private byte role;
	private byte status;
	private long registered;
	
	public User(int id, String nickName, String email, String password,
            String avatar, byte role, byte status, long registered) {
	    super();
	    this.id = id;
	    this.nickName = nickName;
	    this.email = email;
	    this.password = password;
	    this.avatar = avatar;
	    this.role = role;
	    this.status = status;
	    this.registered = registered;
    }

	public User() { }

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public byte getRole() {
		return role;
	}
	public void setRole(byte role) {
		this.role = role;
	}

	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}

	public long getRegistered() {
		return registered;
	}
	public void setRegistered(long registered) {
		this.registered = registered;
	}

	@Override
    public String toString() {
	    return "User [id=" + id + ", nickName=" + nickName + ", email=" + email
	            + ", password=" + password + ", avatar=" + avatar + ", role="
	            + role + ", status=" + status + ", registered=" + registered
	            + "]";
    }
	
	public boolean passwordIsValid(String plainPassword) {
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		return passwordEncryptor.checkPassword(plainPassword, this.password);
	}
	
}
