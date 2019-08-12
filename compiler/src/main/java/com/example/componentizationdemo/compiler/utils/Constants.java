package com.example.componentizationdemo.compiler.utils;

public class Constants {

    //注解处理器支持的注解类型
    public static final String AROUTER_ANNOTATION_TYPES = "com.example.componentizationdemo.annotation.ARouter";
    //每个子模块的模块名
    public static final String MODULE = "moduleName";
    //用于存放APT生成的类文件
    public static final String APT_PACKAGE = "packageNameForAPT";
    //String 的全类名
    public static final String STRING = "java.lang.String";
    //Activity的全类名
    public static final String ACTIVITY = "android.app.Activity";
    //包名前缀封装
    public static final String BASE_PACKAGE = "com.example.componentizationdemo.api";
    //路由组Group加载接口
    public static final String AROUTE_GROUP = BASE_PACKAGE + ".core.ARouterLoadGroup";
    //路由组Group对应得详细Path加载接口
    public static final String AROUTE_Path = BASE_PACKAGE + ".core.ARouterLoadPath";
    //路由组Group对应得详细Path,方法名
    public static final String PATH_METHOD_NAME = "loadPath";
    //路由组Group对应得详细Path,对象名
    public static final String PATH_PARAMETER_NAME = "pathMap";
    //类文件名
    public static final String PATH_FILE_NAME = "ARouter$$Path$$";
}
