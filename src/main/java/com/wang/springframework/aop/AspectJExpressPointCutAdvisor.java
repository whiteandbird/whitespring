package com.wang.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  17:15
 */
public class AspectJExpressPointCutAdvisor implements PointcutAdvisor {
    // 判断匹配 用来判断是否匹配切面
    private AspectJExpressPointCut pointCut;

    // 具体的切面代码
    private Advice advice;

    private String express;

    public void setExpress(String express) {
        this.express = express;
    }


    @Override
    public PointCut getPointCut() {
        if(null == pointCut){
            pointCut =  new AspectJExpressPointCut(express);
        }
        return pointCut;

    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
