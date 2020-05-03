package com.grinningwalrus.login;

public class User {

    private String name;
    private String password;
    private String rank;

    public User()
    {

    }

    public User(String name, String password, String rank) {
        this.name = name;
        this.password = password;
        this.rank = rank;
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
        return "Username:" + this.getName() + "\nPassword:" + this.getPassword() + "\nRank:" + this.getRank() + "\n";
    }

}
