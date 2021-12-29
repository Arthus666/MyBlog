package dream.pojo;

public class UserPojo {

    private int id;
    private String username;
    private String password;
    private String email;
    private byte is_admin;

    public UserPojo() {
    }

    public UserPojo(int id, String username, String password, String email, byte is_admin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.is_admin = is_admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(byte is_admin) {
        this.is_admin = is_admin;
    }

    @Override
    public String toString() {
        return "UserPojo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", is_admin=" + is_admin +
                '}';
    }
}
