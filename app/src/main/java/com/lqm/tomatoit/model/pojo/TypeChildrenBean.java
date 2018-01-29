package com.lqm.tomatoit.model.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * user：lqm
 * desc：Type Tag  二级标签
 */

public class TypeChildrenBean implements Serializable {
    /**
     * id : 60
     * name : Android Studio相关
     * courseId : 13
     * parentChapterId : 150
     * order : 1000
     * visible : 1
     * children : []
     */

    private int id;
    private String name;
    private int courseId;
    private int parentChapterId;
    private int order;
    private int visible;
    private List<?> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getParentChapterId() {
        return parentChapterId;
    }

    public void setParentChapterId(int parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public List<?> getChildren() {
        return children;
    }

    public void setChildren(List<?> children) {
        this.children = children;
    }
}
