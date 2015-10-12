package com.example.yuanzheng.preparedemo.utils.params;

/**
 * Created by yaohu on 15/6/12.
 */
public class ParamsUtils {

    public static int topLevel = 0;//一级分类
    public static String secondLevelId = "";//二级分类ID
    public static int secondLevel = 0;//二级分类
    public static boolean isSelectedToTopLevel = false;
    public static boolean isSelectedToSecondLevel = false;
    public static int topLevelCount;
    //这是用来存储动画结束位置的X、Y坐标
    public static int[] end_location = new int[2];

    public static boolean isCreateOrderNotLogin=false;

    //分类商品列表是否处于滚动状态
    public static boolean isProductListViewScrolled=false;
    //购物车列表是否处于滚动状态
    public static boolean isShopcarListViewScrolled=false;
    //商品详情界面返回后是否刷新
    public static boolean isBackRefresh=false;
    //详情界面未完成的操作是否是关注（不是关注就是加入购物车）
    public static boolean isRequireFinishAttention;
    //分类界面未完成的操作是否是加入购物车
    public static boolean isRequireFinishAddToShopcart;
    //用来判定详情操作返回后哪个界面需要刷新
    public static int lastDetailBackLoadId=0;
}
