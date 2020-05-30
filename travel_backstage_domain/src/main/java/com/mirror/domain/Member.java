package com.mirror.domain;

/**
 * @Author Mirror
 * @CreateDate 2020/3/2.
 *
 * 会员信息类
 */
public class Member {
    private String id;  //会员ID
    private String name;    //会员姓名
    private String nickname;    //会员昵称
    private String phoneNum;    //会员电话号码
    private String email;       //会员邮箱

    public Member() {
    }

    public Member(String id, String name, String nickname, String phoneNum, String email) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
