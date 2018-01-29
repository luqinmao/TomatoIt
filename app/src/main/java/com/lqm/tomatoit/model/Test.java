package com.lqm.tomatoit.model;

import java.util.List;

/**
 * user：lqm
 * desc：
 */

public class Test {


    /**
     * errorCode : 0
     * errorMsg : null
     * data : {"datas":[{"id":182,"title":"你必须弄懂的Intent Filter匹配规则","chapterId":19,"chapterName":"Intent","envelopePic":null,"link":"http://blog.csdn.net/mynameishuangshuai/article/details/51673273","author":"猴子搬来的救兵WooYun","origin":"CSDN","publishTime":1466009335000,"zan":0,"desc":"","visible":1,"niceDate":"2016-06-16","courseId":13,"projectLink":null,"collect":false},{"id":52,"title":"基础总结篇之九：Intent应用详解","chapterId":19,"chapterName":"Intent","envelopePic":null,"link":"http://blog.csdn.net/liuhe688/article/details/7162988","author":"liuhe688","origin":"CSDN","publishTime":1465185295000,"zan":0,"desc":null,"visible":1,"niceDate":"2016-06-06","courseId":13,"projectLink":null,"collect":false}],"offset":0,"size":20,"total":2,"pageCount":1,"curPage":1,"over":true}
     */

    private int errorCode;
    private Object errorMsg;
    private DataBean data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Object getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Object errorMsg) {
        this.errorMsg = errorMsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * datas : [{"id":182,"title":"你必须弄懂的Intent Filter匹配规则","chapterId":19,"chapterName":"Intent","envelopePic":null,"link":"http://blog.csdn.net/mynameishuangshuai/article/details/51673273","author":"猴子搬来的救兵WooYun","origin":"CSDN","publishTime":1466009335000,"zan":0,"desc":"","visible":1,"niceDate":"2016-06-16","courseId":13,"projectLink":null,"collect":false},{"id":52,"title":"基础总结篇之九：Intent应用详解","chapterId":19,"chapterName":"Intent","envelopePic":null,"link":"http://blog.csdn.net/liuhe688/article/details/7162988","author":"liuhe688","origin":"CSDN","publishTime":1465185295000,"zan":0,"desc":null,"visible":1,"niceDate":"2016-06-06","courseId":13,"projectLink":null,"collect":false}]
         * offset : 0
         * size : 20
         * total : 2
         * pageCount : 1
         * curPage : 1
         * over : true
         */

        private int offset;
        private int size;
        private int total;
        private int pageCount;
        private int curPage;
        private boolean over;
        private List<DatasBean> datas;

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * id : 182
             * title : 你必须弄懂的Intent Filter匹配规则
             * chapterId : 19
             * chapterName : Intent
             * envelopePic : null
             * link : http://blog.csdn.net/mynameishuangshuai/article/details/51673273
             * author : 猴子搬来的救兵WooYun
             * origin : CSDN
             * publishTime : 1466009335000
             * zan : 0
             * desc :
             * visible : 1
             * niceDate : 2016-06-16
             * courseId : 13
             * projectLink : null
             * collect : false
             */

            private int id;
            private String title;
            private int chapterId;
            private String chapterName;
            private Object envelopePic;
            private String link;
            private String author;
            private String origin;
            private long publishTime;
            private int zan;
            private String desc;
            private int visible;
            private String niceDate;
            private int courseId;
            private Object projectLink;
            private boolean collect;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getChapterId() {
                return chapterId;
            }

            public void setChapterId(int chapterId) {
                this.chapterId = chapterId;
            }

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public Object getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(Object envelopePic) {
                this.envelopePic = envelopePic;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public int getZan() {
                return zan;
            }

            public void setZan(int zan) {
                this.zan = zan;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public int getVisible() {
                return visible;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public Object getProjectLink() {
                return projectLink;
            }

            public void setProjectLink(Object projectLink) {
                this.projectLink = projectLink;
            }

            public boolean isCollect() {
                return collect;
            }

            public void setCollect(boolean collect) {
                this.collect = collect;
            }
        }
    }
}
