package com.mirror.aop;

import com.mirror.domain.SysLog;
import com.mirror.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Author Mirror
 * @CreateDate 2020/3/5.
 * 日志切面类
 */
@Component
@Aspect
public class LogAop {

    private Date startTime; // 访问时间
    private Class executionClass;// 访问的类
    private Method method; // 访问的方法

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    /**
     * 使用前置通知获取访问数据记录
     *
     * @param joinPoint
     * @throws NoSuchMethodException
     */
    @Before("execution(* com.mirror.controller.*.*(..)) && !execution(* com.mirror.controller.SysLogController.findAll(..))")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        System.out.println("LogAop--Before-- 开始记录本次访问日志");
        //获取当前时间
        startTime = new Date();
        //获取此次访问类
        executionClass = joinPoint.getTarget().getClass();
        //获取此次访问方法
        String methodName = joinPoint.getSignature().getName();

        //获取此次访问方法的参数
        Object[] args = joinPoint.getArgs();

        //判断该方法是否具有参数 获取该方法的Method
        if (args == null || args.length == 0) {
            //根据方法名获取该方法    注：只能获取无参的方法
            method = executionClass.getMethod(methodName);
        } else {
            //定义Class数组存当前方法参数
            Class[] clazzArgs = new Class[args.length];
            //将args存储的方法参数存到clazzArgs中
            for (int i = 0; i < args.length; i++) {
                clazzArgs[i] = args[i].getClass();
            }
            //根据方法名和参数获取该方法
            method = executionClass.getMethod(methodName, clazzArgs);

            System.out.println(method.getName()+"--------" + method.getClass()+"===="+executionClass.getName());
        }

    }

    /**
     * 后置通知获取访问记录And存储到数据库
     *
     * @param joinPoint
     */
    @After("execution(* com.mirror.controller.*.*(..)) && !execution(* com.mirror.controller.SysLogController.findAll(..))")
    public void doAfter(JoinPoint joinPoint) throws Exception {
        //获取访问时长
        Long timeLength = new Date().getTime() - startTime.getTime();

        //获取当前访问的IP
        String ip = request.getRemoteAddr();

        //可以通过securityContext获取，也可以从request.getSession中获取当前操作用户
        //request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        //获取此次访问的URL
        if (executionClass != null && method != null && executionClass != LogAop.class) {
            //获取访问类RequestMapping
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);

            if (classAnnotation != null) {
                //获取该注解的值
                String[] classValue = classAnnotation.value();
                //获取访问方法RequestMapping GetMapping PostMapping注解
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                PostMapping postMapping = method.getAnnotation(PostMapping.class);
                GetMapping getMapping = method.getAnnotation(GetMapping.class);
                String url = "";
                if (methodAnnotation != null) {
                    url = classValue[0] + methodAnnotation.value()[0];
                }
                if (postMapping != null) {
                    url = classValue[0] + postMapping.value()[0];
                }
                if (getMapping != null) {
                    url = classValue[0] + getMapping.value()[0];
                }
                SysLog sysLog = new SysLog(startTime, username, ip, url, timeLength, "[类：] " + executionClass.getName() + " [方法：]" + method.getName());
                System.out.println("日志记录结果："+sysLog.toString());
                //调用SysLogService
                sysLogService.saveSysLog(sysLog);
            }
        }
    }
}
