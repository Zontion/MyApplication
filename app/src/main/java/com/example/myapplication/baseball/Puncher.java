package com.example.myapplication.baseball;

public class Puncher {
    private CardColor color = CardColor.GREY;   //卡片顏色
    private int year = 0;   //年份
    private String name = "";   //名字
    private DefencePlace defencePlace = DefencePlace.C;   //守備位子 如果有雙守備位子就建立兩個物件
    private int str = 0;   //力量
    private int punch = 0;   //打擊
    private int speed = 0;   //速度
    private int pass = 0;   //傳球
    private int defence = 0;   //守備
    private LevelType levelType = LevelType.SS;   //升級類型

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DefencePlace getDefencePlace() {
        return defencePlace;
    }

    public void setDefencePlace(DefencePlace defencePlace) {
        this.defencePlace = defencePlace;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getPunch() {
        return punch;
    }

    public void setPunch(int punch) {
        this.punch = punch;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public LevelType getLevelType() {
        return levelType;
    }

    public void setLevelType(LevelType levelType) {
        this.levelType = levelType;
    }

    public int getTotal() {
        return (this.str + this.punch + this.speed + this.pass + this.defence);
    }

    //卡片顏色
    private enum CardColor {
        GREY, RED, PURPLE, BLACK
    }

    //守備位子
    private enum DefencePlace {
        NONE, C, ONEB, TWOB, THREEB, SS, RF, CF, LF ,DH
    }

    //升級類型
    private enum LevelType {
        SS, SP, SD, PS, PP, PD  //力速, 力傳, 力守, 打速, 打傳, 打守
    }

    public Puncher(CardColor color, int year, String name , DefencePlace defencePlace, int str, int punch,
                   int speed, int pass, int defence, LevelType levelType) {
        this.color = color;
        this.year = year;
        this.name = name;
        this.defencePlace = defencePlace;
        this.str = str;
        this.punch = punch;
        this.speed = speed;
        this.pass = pass;
        this.defence = defence;
        this.levelType = levelType;
    }
}
