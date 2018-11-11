package yuol.secondary.market.erhuo.bean;

import java.io.Serializable;
import java.util.List;

public class GoodsInfo {

    /**
     * code : 200
     * message : 数据获取成功
     * data : [{"name":"小王子","content":"重要的东西是用眼睛看不见的，用心才能看见。小王子是一个来自其他星球、有童心的孩子。他住在一颗和一间房子差不多大的小行星上。某天一粒玫瑰种子飘落到他的星球上，并且生根、发芽、成熟。要知道，小王子以前从来没见过玫瑰花!他对这朵有些虚荣玫瑰花很好奇，并且对她唯命是从。小王子简介。但小王子当时还太小，并不明白她虚荣背后的爱意。他并不明白那种爱，心却受了伤。于是，小王子决定离开她，离开这个星球。他先后访问了六个行星，各种见闻使他陷入忧伤，他感到大人们荒唐可笑、太不正常。只有在其中一个点灯人的星球上，小王子才觉得他可以与自己做朋友。但点灯人的天地又十分狭小，除了点灯人他自己，不能容下第二个人。而他拜访的第七个星球，便是地球。小王子简介。不巧的是，小王子降落的地方是撒哈","pic":"erhuo/upload/2018/11/02/75857.jpg","honest":"100","price":"15","hide":"0","way":"线下","gwhere":"武汉校区12教","lockum":"0","hownew":"9","promise":"1","sell":"0","manylike":"0","wechat":null,"phone":"15377107103","qqnum":null,"oldprice":"30","bargin":"0","pubtime":"2018-11-02 15:12:52"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * name : 小王子
         * content : 重要的东西是用眼睛看不见的，用心才能看见。小王子是一个来自其他星球、有童心的孩子。他住在一颗和一间房子差不多大的小行星上。某天一粒玫瑰种子飘落到他的星球上，并且生根、发芽、成熟。要知道，小王子以前从来没见过玫瑰花!他对这朵有些虚荣玫瑰花很好奇，并且对她唯命是从。小王子简介。但小王子当时还太小，并不明白她虚荣背后的爱意。他并不明白那种爱，心却受了伤。于是，小王子决定离开她，离开这个星球。他先后访问了六个行星，各种见闻使他陷入忧伤，他感到大人们荒唐可笑、太不正常。只有在其中一个点灯人的星球上，小王子才觉得他可以与自己做朋友。但点灯人的天地又十分狭小，除了点灯人他自己，不能容下第二个人。而他拜访的第七个星球，便是地球。小王子简介。不巧的是，小王子降落的地方是撒哈
         * pic : erhuo/upload/2018/11/02/75857.jpg
         * honest : 100
         * price : 15
         * hide : 0
         * way : 线下
         * gwhere : 武汉校区12教
         * lockum : 0
         * hownew : 9
         * promise : 1
         * sell : 0
         * manylike : 0
         * wechat : null
         * phone : 15377107103
         * qqnum : null
         * oldprice : 30
         * bargin : 0
         * pubtime : 2018-11-02 15:12:52
         */

        private String name;
        private String content;
        private String pic;
        private String honest;
        private String price;
        private String hide;
        private String way;
        private String gwhere;
        private String lockum;
        private String hownew;
        private String promise;
        private String sell;
        private String manylike;
        private Object wechat;
        private String phone;
        private Object qqnum;
        private String oldprice;
        private String bargin;
        private String pubtime;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getHonest() {
            return honest;
        }

        public void setHonest(String honest) {
            this.honest = honest;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getHide() {
            return hide;
        }

        public void setHide(String hide) {
            this.hide = hide;
        }

        public String getWay() {
            return way;
        }

        public void setWay(String way) {
            this.way = way;
        }

        public String getGwhere() {
            return gwhere;
        }

        public void setGwhere(String gwhere) {
            this.gwhere = gwhere;
        }

        public String getLockum() {
            return lockum;
        }

        public void setLockum(String lockum) {
            this.lockum = lockum;
        }

        public String getHownew() {
            return hownew;
        }

        public void setHownew(String hownew) {
            this.hownew = hownew;
        }

        public String getPromise() {
            return promise;
        }

        public void setPromise(String promise) {
            this.promise = promise;
        }

        public String getSell() {
            return sell;
        }

        public void setSell(String sell) {
            this.sell = sell;
        }

        public String getManylike() {
            return manylike;
        }

        public void setManylike(String manylike) {
            this.manylike = manylike;
        }

        public Object getWechat() {
            return wechat;
        }

        public void setWechat(Object wechat) {
            this.wechat = wechat;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getQqnum() {
            return qqnum;
        }

        public void setQqnum(Object qqnum) {
            this.qqnum = qqnum;
        }

        public String getOldprice() {
            return oldprice;
        }

        public void setOldprice(String oldprice) {
            this.oldprice = oldprice;
        }

        public String getBargin() {
            return bargin;
        }

        public void setBargin(String bargin) {
            this.bargin = bargin;
        }

        public String getPubtime() {
            return pubtime;
        }

        public void setPubtime(String pubtime) {
            this.pubtime = pubtime;
        }
    }
}
