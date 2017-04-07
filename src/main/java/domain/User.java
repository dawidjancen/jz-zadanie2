package domain;

public class User {
	private String login;
	private String password;
	private String type;

	public User(String _login, String _password, String _type) {
		setLogin(_login);
		setPassword(_password);
		setType(_type);
	}
	
	public User(String _login, String _password) {
		setLogin(_login);
		setPassword(_password);
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
