package com.zx.rubbishgame.banner;

import java.io.Serializable;

/**
 * description：
 * author：bux on 2019/5/23 10:47
 * email: 471025316@qq.com
 */
public class BannerBean  implements Serializable {

   public static final int IMG = 1;
   public static final int VIDEO = 2;
    /**
     * type : 1
     * source : https://cssjsname.oss-cn-shenzhen.aliyuncs.com/a.jpg
     * pic : https://cssjsname.oss-cn-shenzhen.aliyuncs.com/a.jpg
     * version : 1.0
     */

    private int type;
    private String source;
    private Object pic;
    private int version;
    private int rawId;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public static int getIMG() {
        return IMG;
    }

    public static int getVIDEO() {
        return VIDEO;
    }

    public int getRawId() {
        return rawId;
    }

    public void setRawId(int rawId) {
        this.rawId = rawId;
    }

    public Object getPic() {
        return pic;
    }

    public void setPic(Object pic) {
        this.pic = pic;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isVideo() {
        return type == VIDEO;
    }

    /**
     * 是否是同一个资源
     *
     * @return
     */
    public boolean isSameRes(BannerBean bean) {
        return source.equals(bean.getSource())
                && pic.equals(bean.getPic());
    }

    /**
     * 资源是否修改
     *
     * @return
     */
    public boolean isUpdate(BannerBean bean) {
        return version == bean.getVersion();
    }
}
