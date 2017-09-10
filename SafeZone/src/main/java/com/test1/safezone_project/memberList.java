package com.test1.safezone_project;

/**
 * Created by user on 2016-11-27.
 */
public class memberList {

    private String[] mData;

    public memberList(String[] data ){
        mData = data;
    }

    public memberList(String user_id, String nick_name, String name, String birth, String sex, String tel, String email, String pw){

        mData = new String[7];

        mData[0] = user_id;

        mData[1] = nick_name;

        mData[2] = name;

        mData[3] = birth;

        mData[4] = sex;

        mData[5] = tel;

        mData[6] = email;

        mData[7] = pw;



    }



    public String[] getData(){

        return mData;

    }



    public String getData(int index){

        return mData[index];

    }



    public void setData(String[] data){

        mData = data;

    }
}
