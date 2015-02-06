package com.level.concurency.chapter01;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface GuardedBy {
	String value();
}
