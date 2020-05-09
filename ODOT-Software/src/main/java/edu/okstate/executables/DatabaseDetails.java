package edu.okstate.executables;

public class DatabaseDetails {
	String driver;
	String url;
	String port;
	String username;
	String password;
	String name;
	String pool_size;
	String dialect;
	String show_sql;
	String current_session;
	
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
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
	public String getPool_size() {
		return pool_size;
	}
	public void setPool_size(String pool_size) {
		this.pool_size = pool_size;
	}
	public String getDialect() {
		return dialect;
	}
	public void setDialect(String dialect) {
		this.dialect = dialect;
	}
	public String getShow_sql() {
		return show_sql;
	}
	public void setShow_sql(String show_sql) {
		this.show_sql = show_sql;
	}
	public String getCurrent_session() {
		return current_session;
	}
	public void setCurrent_session(String current_session) {
		this.current_session = current_session;
	}
	@Override
	public String toString() {
		return "DatabaseDetails [driver=" + driver + ", url=" + url + ", port=" + port + ", username=" + username
				+ ", password=" + password + ", name=" + name + ", pool_size=" + pool_size + ", dialect=" + dialect
				+ ", show_sql=" + show_sql + ", current_session=" + current_session + "]";
	}
}
