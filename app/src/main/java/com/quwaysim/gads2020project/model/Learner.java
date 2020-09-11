package com.quwaysim.gads2020project.model;

import com.google.gson.annotations.SerializedName;

public class Learner {

    private String name, country, badgeUrl;

    @SerializedName(value="achievement", alternate={"hours", "score"})
    private int achievement;

    public Learner(String name, int achievement, String country, String badgeUrl) {
        this.name = name;
        this.achievement = achievement;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAchievement() {
        return String.valueOf(achievement);
    }

    public void setAchievement(int achievement) {
        this.achievement = achievement;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

}
