package Pojo;

//POJO -> Plain Old Java Object
	//Can not extend,inheritance anything
	//private data fields(variables)
	//public getter/setter
	//Encapsulation
	//public constructors
public class Credentials {
	
	private String username;
	private String password;
	public Credentials(String username, String password) {
			this.username = username;
			this.password = password;
	}
	
	//getters & setters
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

	

}
