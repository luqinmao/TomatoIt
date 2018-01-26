package com.lqm.tomatoit.model.pojo;

import java.io.Serializable;

/**
 * user：lqm
 * desc：首页轮播图
 */

public class BannerBean implements Serializable {

    /**
     * id : 6
     * url : http://www.wanandroid.com/navi
     * imagePath : http://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png
     * title : 我们新增了一个常用导航Tab~
     * desc :
     * isVisible : 1
     * order : 1
     * type : 0
     */

    private int id;
    private String url;
    private String imagePath;
    private String title;
    private String desc;
    private int isVisible;
    private int order;
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
