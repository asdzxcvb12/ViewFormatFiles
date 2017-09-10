package com.test1.safezone_project;

/**
 * Created by user on 2016-10-12.
 */
public class MyInfo {
    String nickname = ""; // 곡 title
    int img; // 앨범 이미지
    String email = ""; // artist
    public MyInfo(String nickname, int img, String email) {
        super();
        this.nickname = nickname;
        this.img = img;
        this.email = email;
    }
    public MyInfo() {}
}
