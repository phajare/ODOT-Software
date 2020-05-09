package edu.okstate.executables;

public class DatabaseDetails {
	String driver;
	String url;
	String username;
	String password;
	String pool_size;
	String dialect;
	String show_sql;
	String current_session;
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
		return "DatabaseDetails [driver=" + driver + ", url=" + url + ", username=" + username + ", password="
				+ password + ", pool_size=" + pool_size + ", dialect=" + dialect + ", show_sql=" + show_sql
				+ ", current_session=" + current_session + "]";
	}
}
