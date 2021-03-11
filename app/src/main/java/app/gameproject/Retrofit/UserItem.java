package app.gameproject.Retrofit;

//회원가입시 Request 되는 UserInfo
public class UserItem {
    private String name;  // 이름
    private String user_id; // 아이디
    private String pwd; // 비밀번호
    private String repeat_pwd; // 비밀번호 확인

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRepeat_pwd() {
        return repeat_pwd;
    }

    public void setRepeat_pwd(String repeat_pwd) {
        this.repeat_pwd = repeat_pwd;
    }
}
