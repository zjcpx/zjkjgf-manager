package com.zjkjgf.pojo;

import java.util.Date;

public class Scores {
    private Long id;

    private String vottingperson;

    private String matchproject;

    private Integer hotscores;

    private Integer specialistscores;

    private Date createtime;

    private Date modifytime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVottingperson() {
        return vottingperson;
    }

    public void setVottingperson(String vottingperson) {
        this.vottingperson = vottingperson == null ? null : vottingperson.trim();
    }

    public String getMatchproject() {
        return matchproject;
    }

    public void setMatchproject(String matchproject) {
        this.matchproject = matchproject == null ? null : matchproject.trim();
    }

    public Integer getHotscores() {
        return hotscores;
    }

    public void setHotscores(Integer hotscores) {
        this.hotscores = hotscores;
    }

    public Integer getSpecialistscores() {
        return specialistscores;
    }

    public void setSpecialistscores(Integer specialistscores) {
        this.specialistscores = specialistscores;
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