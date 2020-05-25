package com.grinningwalrus.login;

public class User implements Comparable{

    private String name;
    private String password;
    private String rank;
    private Integer score;

    public User()
    {

    }

    public User(String name, String password, String rank) {
        this.name = name;
        this.password = password;
        this.rank = rank;
        this.score = 0;
    }

    @Override
    public int compareTo(Object o) {
        int comparescore=((User)o).getScore();
        /* For Ascending order*/
        return this.score - comparescore;
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

    @Override
    public String toString()
    {
        return "Username:" + this.getName() + "Rank:" + this.getRank() + "Score:" + this.getScore();
    }

}
