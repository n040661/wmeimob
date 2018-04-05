package com.wmeimob.util;

import java.io.Serializable;
import java.util.Date;

public class JwtUser implements Serializable {

    private static final long serialVersionUID = -955272199747731688L;

    private String id;
    private String password;
    private String username;
    private Date lastPasswordResetDate;

    public JwtUser() {

    }
    
    public JwtUser(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public JwtUser(String id, String username, String password, Date lastPasswordResetDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

}