package com.zjkjgf.pojo;

import java.util.Date;

public class User {
    private Long id;

    private String username;

    private String password;

    private String nickname;

    private Integer role;

    private Integer votingpower;

    private String votingproject;

    private String votingrecoder;

    private Date createtime;

    private Date modifytime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getVotingpower() {
        return votingpower;
    }

    public void setVotingpower(Integer votingpower) {
        this.votingpower = votingpower;
    }

    public String getVotingproject() {
        return votingproject;
    }

    public void setVotingproject(String votingproject) {
        this.votingproject = votingproject == null ? null : votingproject.trim();
    }

    public String getVotingrecoder() {
        return votingrecoder;
    }

    public void setVotingrecoder(String votingrecoder) {
        this.votingrecoder = votingrecoder == null ? null : votingrecoder.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }
}