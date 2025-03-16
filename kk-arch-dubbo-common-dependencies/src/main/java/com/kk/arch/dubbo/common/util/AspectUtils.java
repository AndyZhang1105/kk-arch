package com.kk.arch.dubbo.common.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Arrays;

/**
 * @author 敖癸
 * @since 2024/3/4
 */
public class AspectUtils {

    private static final ExpressionParser PARSER = new SpelExpressionParser();

    /**
     * 解析切点的上下文对象
     *
     * @param joinPoint aop切点
     * @return org.springframework.expression.EvaluationContext
     * @author 敖癸
     * @since 2024/3/5 - 17:05
     */
    public static EvaluationContext buildEvaluationContext(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取切点的方法参数名称列表
        String[] argNames = signature.getParameterNames();
        EvaluationContext context = new StandardEvaluationContext();
        // 获取切点的参数值列表
        Object[] methodArgs = joinPoint.getArgs();
        for (int i = 0; i < methodArgs.length; i++) {
            // 将参数名称和参数值一一对应
            context.setVariable(argNames[i], methodArgs[i]);
        }
        return context;
    }

    /**
     * SpEL模板解析
     *
     * @param joinPoint   aop切点
     * @param template    字符串模板
     * @param expressions SpEL参数
     * @return java.lang.String
     * @author 敖癸
     * @since 2024/3/5 - 17:03
     */
    public static String buildTemplate(ProceedingJoinPoint joinPoint, String template, String[] expressions) {
        return buildTemplate(template, expressions, buildEvaluationContext(joinPoint));
    }

    /**
     * SpEL模板解析
     *
     * @param context     切点的上下文对象
     * @param template    字符串模板
     * @param expressions SpEL参数
     * @return java.lang.String
     * @author 敖癸
     * @since 2024/3/5 - 17:03
     */
    public static String buildTemplate(String template, String[] expressions, EvaluationContext context) {
        if (StringUtils.isNotBlank(template) && expressions != null) {
            Object[] args = Arrays.stream(expressions).map(expression -> {
                try {
                    return PARSER.parseExpression(expression).getValue(context);
                } catch (Exception e) {
                    return expression;
                }
            }).toArray();
            template = String.format(template, args);
        }
        return template;
    }

}

