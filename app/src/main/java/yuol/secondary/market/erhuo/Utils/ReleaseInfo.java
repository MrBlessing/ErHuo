package yuol.secondary.market.erhuo.Utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReleaseInfo {
    private String name;//商品名称
    private String content;//商品详情
    private String price;//现价
    private String oldPrice;//原价
    private String hide;//是否隐藏，1为匿名，0为不匿名
    private String bargin;//是否议价
    private String promise;//售后承诺,可以不填
    private String payway="underline";//支付方式
    private String hownew;//几成新
    private String school;//校区
    private String detailWhere;//详细交易地址
    private String contact;//联系方式
    private String chatway;//联系人内容
    private String catName;//栏目名称
    private List<String> pic = new ArrayList<>();

    public List<String> getPic() {
        return pic;
    }

    public void addPic(String pic) {
       this.pic.add(pic);
    }

    public void removePic(String value){
        pic.remove(value);
    }

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getHide() {
        return hide;
    }

    public void setHide(String hide) {
        this.hide = hide;
    }

    public String getBargin() {
        return bargin;
    }

    public void setBargin(String bargin) {
        this.bargin = bargin;
    }

    public String getPromise() {
        return promise;
    }

    public void setPromise(String promise) {
        this.promise = promise;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public String getHownew() {
        return hownew;
    }

    public void setHownew(String hownew) {
        this.hownew = hownew;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDetailWhere() {
        return detailWhere;
    }

    public void setDetailWhere(String detailWhere) {
        this.detailWhere = detailWhere;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getChatway() {
        return chatway;
    }

    public void setChatway(String chatway) {
        this.chatway = chatway;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public boolean isNull(){
        if(TextUtils.isEmpty(name)
                ||TextUtils.isEmpty(content)
                ||TextUtils.isEmpty(price)
                ||TextUtils.isEmpty(oldPrice)
                ||TextUtils.isEmpty(hide)
                ||TextUtils.isEmpty(bargin)
                ||TextUtils.isEmpty(payway)
                ||TextUtils.isEmpty(hownew)
                ||TextUtils.isEmpty(school)
                ||TextUtils.isEmpty(detailWhere)
                ||TextUtils.isEmpty(contact)
                ||TextUtils.isEmpty(chatway)
                ||TextUtils.isEmpty(catName)
                ||pic.size()==0){
            return true;
        }else
            return false;
    }

}
