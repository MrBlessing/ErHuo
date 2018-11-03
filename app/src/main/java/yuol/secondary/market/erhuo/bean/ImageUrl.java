package yuol.secondary.market.erhuo.bean;

import java.util.List;

public class ImageUrl {


    /**
     * info : 图片地址查询成功
     * start : http://192.168.137.1/taoke/启动页面.png
     * banner : [{"banner":"http://192.168.137.1/taoke/1.jpg"},{"banner":"http://192.168.137.1/taoke/2.jpg"},{"banner":"http://192.168.137.1/taoke/3.png"},{"banner":"http://192.168.137.1/taoke/4.png"},{"banner":"http://192.168.137.1/taoke/5.jpg"}]
     * homeBackground : http://192.168.137.1/taoke/homeBackground.png
     */

    private String info;
    private String start;
    private String homeBackground;
    private List<BannerBean> banner;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getHomeBackground() {
        return homeBackground;
    }

    public void setHomeBackground(String homeBackground) {
        this.homeBackground = homeBackground;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public static class BannerBean {
        /**
         * banner : http://192.168.137.1/taoke/1.jpg
         */

        private String banner;

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }
    }
}
