 /**
 * 程式資訊摘要：
 * 類別名稱：ControllerAspect.java
 * 程式內容說明：
 * 版本資訊：
 * 程式設計人員姓名：Sam,Chen
 * 程式修改記錄：2018年4月23日
 * 版權宣告：
 */
package com.esb.cbp.aspect;

import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import com.esb.common.logger.EsunLogger;

/**
 * @author sam.xps
 *
 */
public class ControllerAspect {

	private Logger log = EsunLogger.create(ControllerAspect.class);

    /**
     * Method被呼叫前.
     * 
     * @param jp JoinPoint
     */
    public void doBefore(JoinPoint jp) {
    	log.info("ControllerAspect doBefore " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
    }

    /**
     * Method被呼叫後.
     * 
     * @param jp JoinPoint
     */
    public void doAfter(JoinPoint jp) {
    	log.info("ControllerAspect doAfter " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
    }

    /**
     * Method被呼叫前後.
     * 
     * @param pjp ProceedingJoinPoint
     * @return Object
     * @throws Throwable Throwable
     */
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object retVal = pjp.proceed();
        log.info("ControllerAspect doAround " + pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName());
        return retVal;
    }
}
