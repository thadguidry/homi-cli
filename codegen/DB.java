package codegen;

public class DB {
    private String type;
    private String ip;
    private long port;
    private String username;
    private Object password;
    private String dbname;

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    public String getIp() { return ip; }
    public void setIp(String value) { this.ip = value; }

    public long getPort() { return port; }
    public void setPort(long value) { this.port = value; }

    public String getUsername() { return username; }
    public void setUsername(String value) { this.username = value; }

    public Object getPassword() { return password; }
    public void setPassword(Object value) { this.password = value; }

    public String getDbname() { return dbname; }
    public void setDbname(String value) { this.dbname = value; }
}
