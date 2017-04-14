//package com.boz.common.utils;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//
//
///**
// * Spring 工具类
// *
// * @creator zhangqiang
// * @create-time May 11, 2012   2:56:39 PM
// */
//@Component("springUtil")
//public class SpringUtil implements ApplicationContextAware, DisposableBean {
//
//    private static ApplicationContext applicationContext = null;
//
//    /**
//     * 获取applicationContext
//     *
//     * @return applicationContext
//     */
//    public static ApplicationContext getApplicationContext() {
//        return applicationContext;
//    }
//
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        SpringUtil.applicationContext = applicationContext;
//    }
//
//    /**
//     * 根据Bean名称获取实例
//     *
//     * @param name Bean注册名称
//     * @return bean实例
//     * @throws BeansException
//     */
//    public static Object getBean(String name) throws BeansException {
//        return applicationContext.getBean(name);
//    }
//
//    public void destroy() throws Exception {
//        applicationContext = null;
//    }
//
//}