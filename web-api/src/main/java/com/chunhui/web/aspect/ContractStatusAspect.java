package com.chunhui.web.aspect;

import com.chunhui.web.task.SyncContractInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Aspect
@Component
public class ContractStatusAspect {

    @Resource
    private SyncContractInfo syncContractInfo;
    /**
     * 用于SpEL表达式解析.
     */
    private SpelExpressionParser parser = new SpelExpressionParser();
    /**
     * 用于获取方法参数定义名字.
     */
    private DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();

//    @Pointcut("execution(* com.chunhui.web.service.jljs.*.*(..))")
//    public void pointcut() {
//    }

    @Around("@annotation(com.chunhui.web.aspect.SyncContractStatus)")
    public Object syncStatus(ProceedingJoinPoint joinPoint) throws Throwable {
        // 执行方法
        Object result = joinPoint.proceed();
        // 获取方法上的注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SyncContractStatus cacheable = method.getAnnotation(SyncContractStatus.class);
        String spel = cacheable.spel();
        // 解析spEL
        String contractId = generateKeyBySpEL(spel, joinPoint);
        // 更新合同状态
        syncContractInfo.sync(contractId);
        return result;
    }

    private String generateKeyBySpEL(String spELString, ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = nameDiscoverer.getParameterNames(methodSignature.getMethod());
        Expression expression = parser.parseExpression(spELString);
        EvaluationContext context = new StandardEvaluationContext();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            context.setVariable(paramNames[i], args[i]);
        }
        return expression.getValue(context).toString();
    }

}
