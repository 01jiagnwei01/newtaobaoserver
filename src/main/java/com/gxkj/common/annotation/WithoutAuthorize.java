package com.gxkj.common.annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @Description 不需要验证用户信息
 * @ClassName WithoutAuthorize
 * @Created 2011 2011-9-7 下午01:51:55
 */
@Target(value={ElementType.TYPE})
@Retention(value=RetentionPolicy.RUNTIME)
@Documented
public  @interface WithoutAuthorize{

}
