package com.grinningwalrus.login;

public class User implements Comparable{

    private String name;
    private String password;
    private String rank;
    private String hiker_rank;
    private Integer score;

    public User()
    {

    }

    public User(String name, String password, String rank) {
        this.name = name;
        this.password = password;
        this.rank = rank;
        this.hiker_rank = "Amateur";
        this.score = 0;
    }

    @Override
    public int compareTo(Object o) {
        int comparescore=((User)o).getScore();
        /* For Ascending order*/
        return comparescore - this.score;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getHiker_rank() {
        return hiker_rank;
    }

    public void setHiker_rank(String hiker_rank) {
        this.hiker_rank = hiker_rank;
    }

    @Override
    public String toString()
    {
        return "Name: " + this.getName() + "       Rank: " + this.getRank() + "      Score: " + this.getScore();
    }

}
