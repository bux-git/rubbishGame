package com.common.widget.gload.view;

/**
 * @description：
 * @author：bux on 2019/4/2 18:08
 * @email: 471025316@qq.com
 */
public class GLoadData {

    public GLoadData() {
    }

    public GLoadData(int emptyMsg, int emptyImg) {
        this.emptyMsg = emptyMsg;
        this.emptyImg = emptyImg;
    }

    public GLoadData(int emptyMsg, int emptyImg, int errorMsg, int errorImg, int loadMsg, int loadAnim) {
        this.emptyMsg = emptyMsg;
        this.emptyImg = emptyImg;
        this.errorMsg = errorMsg;
        this.errorImg = errorImg;
        this.loadMsg = loadMsg;
        this.loadAnim = loadAnim;
    }

    private int emptyMsg;
    private int emptyImg;

    private int errorMsg;
    private int errorImg;

    private int loadMsg;
    private int loadAnim;

    public int getEmptyMsg() {
        return emptyMsg;
    }

    public void setEmptyMsg(int emptyMsg) {
        this.emptyMsg = emptyMsg;
    }

    public int getEmptyImg() {
        return emptyImg;
    }

    public void setEmptyImg(int emptyImg) {
        this.emptyImg = emptyImg;
    }

    public int getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(int errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorImg() {
        return errorImg;
    }

    public void setErrorImg(int errorImg) {
        this.errorImg = errorImg;
    }

    public int getLoadMsg() {
        return loadMsg;
    }

    public void setLoadMsg(int loadMsg) {
        this.loadMsg = loadMsg;
    }

    public int getLoadAnim() {
        return loadAnim;
    }

    public void setLoadAnim(int loadAnim) {
        this.loadAnim = loadAnim;
    }
}
