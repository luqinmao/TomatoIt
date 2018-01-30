package com.lqm.tomatoit.model.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * user：lqm
 * desc：登录注册成功
 */

public class UserBean implements Serializable {

    /**
     * id : 1847
     * username : 猥琐的豆腐6
     * password : 123456
     * icon : null
     * type : 0
     * collectIds : [2239]
     */

    private int id;
    private String username;
    private String password;
    private Object icon;
    private int type;
    private List<Integer> collectIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getIcon() {
        return icon;
    }

    public void setIcon(Object icon) {
        this.icon = icon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Integer> getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(List<Integer> collectIds) {
        this.collectIds = collectIds;
    }
}
