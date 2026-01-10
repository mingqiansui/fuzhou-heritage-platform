package com.heritage.auth.aop;

import com.heritage.auth.mapper.AuditMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuditAspect {

    @Autowired
    AuditMapper auditMapper;

    /* 拦截所有 @GetMapping / @PostMapping */
    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String user = req.getParameter("username");        // 简单取参数
        if (user == null) user = "anonymous";

        String action = pjp.getSignature().getName();      // 方法名当动作
        String uri    = req.getRequestURI();
        Long assetId  = null;
        try { assetId = Long.valueOf(req.getParameter("assetId")); } catch (Exception ignore) {}

        // 写日志
        auditMapper.insert(user, action, assetId, req.getRemoteAddr(), req.getHeader("User-Agent"));

        return pjp.proceed();
    }
}
