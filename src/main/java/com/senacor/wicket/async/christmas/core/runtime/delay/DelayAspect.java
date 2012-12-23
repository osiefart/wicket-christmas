package com.senacor.wicket.async.christmas.core.runtime.delay;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class DelayAspect {

  @Pointcut("execution(@com.senacor.wicket.async.christmas.data.delay.Delay * com.senacor.wicket..*.*(..))")
  public void methodAnnotatedWithDelay() {
  }

  @AfterReturning(value = "methodAnnotatedWithDelay() && @annotation(delay)", argNames = "delay")
  public void afterReturningFromMethodInSpringBean(Delay delay) {
    try {
      Thread.sleep(delay.value());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}