package com.zx.rubbishgame.utils;

import android.util.SparseArray;

import com.zx.rubbishgame.bean.RbItem;
import com.zx.rubbishgame.bean.RbType;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * description：
 * author：bux on 2019/7/17 10:31
 * email: 471025316@qq.com
 */
public class DataCons {

    //每等级10题
    public static final int LV_COUNT = 10;


    public static final RbType HS_TYPE = new RbType(1, "可回收物");
    public static final RbType YH_TYPE = new RbType(2, "有害垃圾");
    public static final RbType G_TYPE = new RbType(3, "湿垃圾");
    public static final RbType S_TYPE = new RbType(4, "干垃圾");

    public static final SparseArray<List<RbItem>> RB_LV_MAP = new SparseArray<>();
    //及格分数
    public static final int PASS_SOURCE = 6;
    public static final long MAX_GO_MAIN = 5 * 1000;

    static {
        //lv1
        List<RbItem> lv1 = new ArrayList<>();
        //可回收垃圾
        setItem(lv1, "报纸", 0, HS_TYPE);
        setItem(lv1, "纸箱", 0, HS_TYPE);
        setItem(lv1, "书本", 0, HS_TYPE);
        setItem(lv1, "纸袋", 0, HS_TYPE);
        //有害垃圾
        setItem(lv1, "充电电池", 0, YH_TYPE);
        setItem(lv1, "铅酸电池", 0, YH_TYPE);
        setItem(lv1, "纽扣电池", 0, YH_TYPE);
        setItem(lv1, "蓄电池", 0, YH_TYPE);
        //湿垃圾
        setItem(lv1, "剩饭剩菜", 0, S_TYPE);
        setItem(lv1, "鸡肉", 0, S_TYPE);
        setItem(lv1, "面包", 0, S_TYPE);
        setItem(lv1, "动物内脏", 0, S_TYPE);
        //干垃圾
        setItem(lv1, "餐巾纸", 0, G_TYPE);
        setItem(lv1, "卫生间用纸", 0, G_TYPE);
        setItem(lv1, "胶带", 0, G_TYPE);
        setItem(lv1, "创口贴", 0, G_TYPE);

        RB_LV_MAP.put(0, lv1);


        //lv1
        List<RbItem> lv2 = new ArrayList<>();
        //可回收垃圾
        setItem(lv2, "报纸", 0, HS_TYPE);
        setItem(lv2, "纸箱", 0, HS_TYPE);
        setItem(lv2, "书本", 0, HS_TYPE);
        setItem(lv2, "纸袋", 0, HS_TYPE);
        //有害垃圾
        setItem(lv2, "充电电池", 0, YH_TYPE);
        setItem(lv2, "铅酸电池", 0, YH_TYPE);
        setItem(lv2, "纽扣电池", 0, YH_TYPE);
        setItem(lv2, "蓄电池", 0, YH_TYPE);
        //湿垃圾
        setItem(lv2, "剩饭剩菜", 0, S_TYPE);
        setItem(lv2, "鸡肉", 0, S_TYPE);
        setItem(lv2, "面包", 0, S_TYPE);
        setItem(lv2, "动物内脏", 0, S_TYPE);
        //干垃圾
        setItem(lv2, "餐巾纸", 0, G_TYPE);
        setItem(lv2, "卫生间用纸", 0, G_TYPE);
        setItem(lv2, "胶带", 0, G_TYPE);
        setItem(lv2, "创口贴", 0, G_TYPE);

        RB_LV_MAP.put(1, lv2);

        //lv1
        List<RbItem> lv3 = new ArrayList<>();
        //可回收垃圾
        setItem(lv3, "报纸", 0, HS_TYPE);
        setItem(lv3, "纸箱", 0, HS_TYPE);
        setItem(lv3, "书本", 0, HS_TYPE);
        setItem(lv3, "纸袋", 0, HS_TYPE);
        //有害垃圾
        setItem(lv3, "充电电池", 0, YH_TYPE);
        setItem(lv3, "铅酸电池", 0, YH_TYPE);
        setItem(lv3, "纽扣电池", 0, YH_TYPE);
        setItem(lv3, "蓄电池", 0, YH_TYPE);
        //湿垃圾
        setItem(lv3, "剩饭剩菜", 0, S_TYPE);
        setItem(lv3, "鸡肉", 0, S_TYPE);
        setItem(lv3, "面包", 0, S_TYPE);
        setItem(lv3, "动物内脏", 0, S_TYPE);
        //干垃圾
        setItem(lv3, "餐巾纸", 0, G_TYPE);
        setItem(lv3, "卫生间用纸", 0, G_TYPE);
        setItem(lv3, "胶带", 0, G_TYPE);
        setItem(lv3, "创口贴", 0, G_TYPE);

        RB_LV_MAP.put(2, lv3);

    }

    @NotNull
    private static void setItem(List<RbItem> rbItems, String name, int img, RbType hsType) {
        rbItems.add(new RbItem(name, img, hsType));
    }

    /**
     * 总等级
     *
     * @return
     */
    public static int getTotalLv() {
        return RB_LV_MAP.size();
    }

    /**
     * 最后一关
     * @param lv
     * @return
     */
    public static boolean isLastLv(int lv) {
        return lv == (RB_LV_MAP.size() - 1);
    }

    /**
     * 获取等级数据
     *
     * @param lv 等级
     * @return
     */
    public static List<RbItem> getLvData(int lv) {
        if (lv >= RB_LV_MAP.size()) {
            return null;
        }
        //获取对应等级零时集合数据
        List<RbItem> rbItems = new ArrayList<>(RB_LV_MAP.valueAt(lv));
        //返回结果
        List<RbItem> result = new ArrayList<>();
        //随机数
        Random random = new Random();

        //随机获取对应等级中的数据
        while (result.size() <= LV_COUNT - 1) {
            //产生随机数
            int i = random.nextInt(rbItems.size());
            result.add(rbItems.get(i));
            //删除已经获取的数据 避免重复
            rbItems.remove(i);
        }
        return result;
    }


}
