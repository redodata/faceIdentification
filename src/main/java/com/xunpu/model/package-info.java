package com.xunpu.model;
/**
 * 该包中只有一个类
 * 该类主要用来描述向前端组织的响应信息模板（单例对象）
 * 其中包括3个属性（status响应状态码、响应信息、响应数据（图片）
 * 方法：
 *      1）私有的构造方法
 *      2）基于构造方法，返回正常响应码的Response对象。（public static）
 *      3）基于构造方法，返回异常响应码的Response对象。（public static）
 *      4）三个属性的getXxx()
 *      5）覆写toString()，返回Response对象的JSON字符串。
 */