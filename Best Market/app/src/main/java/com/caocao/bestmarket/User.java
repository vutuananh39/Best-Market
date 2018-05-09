package com.caocao.bestmarket;

public class User {
    String Id;
    String TenTk;
    String PassWord;
    String HoTen;
    String Sdt;
    String Email;
    String Dc;


    public User() {
    }

    public User(String id, String tenTk, String passWord, String hoTen, String sdt, String email, String dc) {
        Id = id;
        TenTk = tenTk;
        PassWord = passWord;
        HoTen = hoTen;
        Sdt = sdt;
        Email = email;
        Dc = dc;
    }
}
