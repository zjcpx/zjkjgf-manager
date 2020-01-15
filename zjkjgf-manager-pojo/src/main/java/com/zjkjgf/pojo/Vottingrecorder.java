package com.zjkjgf.pojo;

import java.util.Date;

public class Vottingrecorder {
    private Long id;

    private String username;

    private String votingproject;

    private String scoreslist;

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

    public String getVotingproject() {
        return votingproject;
    }

    public void setVotingproject(String votingproject) {
        this.votingproject = votingproject == null ? null : votingproject.trim();
    }

    public String getScoreslist() {
        return scoreslist;
    }

    public void setScoreslist(String scoreslist) {
        this.scoreslist = scoreslist == null ? null : scoreslist.trim();
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