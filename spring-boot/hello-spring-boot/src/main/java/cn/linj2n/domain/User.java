package cn.linj2n.domain;

import java.util.Date;
import java.io.Serializable;

public class User implements Serializable {
    private int userId;
    private String userName;
    private String password;
    private String lastIp;
    private Date lastVisit;

    public Date getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    @Override
    public String toString() {
        return new String("[ " + " userName= "+userName + ", userId= " + userId +", userPwd= " + this.getPassword() + ", LastVisit= " + lastVisit + ", LastIp= " + lastIp + " ]");
    }
}
