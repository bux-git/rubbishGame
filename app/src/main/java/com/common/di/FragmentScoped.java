package com.common.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @description：
 * @author：bux on 2018/4/2 23:10
 * @email: 471025316@qq.com
 */

@Scope
@Documented
@Retention(RUNTIME)
public @interface FragmentScoped {
}
