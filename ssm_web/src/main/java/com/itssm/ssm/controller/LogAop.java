package com.itssm.ssm.controller;

import com.itssm.ssm.domain.SysLog;
import com.itssm.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 日志类描述：
 * 访问时间
 * 操作者用户名
 * 访问IP
 * 访问资源URL
 * 访问时长
 * 访问方法
 */
@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime;  //开始时间
    private Class clazz; //访问的类
    private Method method;

    /**
     * 前置通知   主要时获取开始时间，执行的类是哪一个，执行的是哪一个方法
     *JoinPoin常用方法：
     *  Object[] getArgs():获取连接点方法运行时的入参列表
     *  Signature getSignature():获取连接点的方法签名对象
     *  Object getTarget(); 获取连接点所在的目标对象
     *  Object getThis(): 获取代理对象本身
     *
     *
     * @param jp
     */
    @Before("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
            visitTime=new Date(); //当前时间，开始访问的时间
            clazz=jp.getTarget().getClass();  //具体要访问的类
            String methodName=jp.getSignature().getName(); //获取访问的方法的名称
            Object[] args = jp.getArgs();//获取访问方法的参数

            //获取具体执行的方法的method对象
        if (args == null || args.length==0){
            method = clazz.getMethod(methodName); //获取无参数的方法
        }else{
            Class[] classArgs=new Class[args.length];
            for (int i=0;i<args.length;i++) {
               classArgs[i]=args[i].getClass();  //把每个参数的class赋值到classArgs数组里面
            }
                method=clazz.getMethod(methodName,classArgs);
        }

    }

    /**
     * 最终通知(后置通知)
     * @param jp
     */
    @AfterReturning("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        long time=new Date().getTime() - visitTime.getTime();
        String url = null;
        //获取url
        if (clazz !=null && method!=null&&clazz != LogAop.class){

            //获取类上的@RequestMapping("/user")
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);//getAnnotation:返回此元素上，存在的注解
            if (classAnnotation!=null){
                //获取类上注解的value
                String[] classValue = classAnnotation.value();

                //获取方法上的@RequestMapping注解
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation!=null){
                    //获取requestMapping注解的value值
                    String[] methodValue = methodAnnotation.value();

                    url=classValue[0]+methodValue[0];
                }
            }
        }
        //获取IP
        String ip = request.getRemoteAddr();//返回发送请求者的IP地址。

        //获取当前操作用户
        SecurityContext context= SecurityContextHolder.getContext();//从上下文获取当前登陆的用户
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        //将日志相关信息封装到SysLog对象
        SysLog sysLog=new SysLog();
        sysLog.setExecutionTime(time); //执行时长
        sysLog.setUrl(url);//url
        sysLog.setIp(ip);//ip
       sysLog.setMethod("[类名]:"+clazz.getName()+"[方法名]:"+method.getName());//方法名
        sysLog.setUsername(username);//用户名
        sysLog.setVisitTime(visitTime);//访问时间

        //调用sevice完成操作。
        sysLogService.save(sysLog);
    }

    }

