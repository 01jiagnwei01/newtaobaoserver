package com.gxkj.taobaoservice.jmxs;

import java.util.List;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;
@Component
@ManagedResource (objectName = "bean:name=testJmxBean4", description = "My Managed Bean", log = true, logFile = "jmx.log", currencyTimeLimit = 15, persistPolicy = "OnUpdate", persistPeriod = 200, persistLocation = "foo", persistName = "bar")
public class AnnotationTestBean {
	private String name;
	private int age;
	private List<String> values;

	@ManagedAttribute(defaultValue = "foo", persistPeriod = 300)
	public String getName() {
		return name;
	}

	@ManagedAttribute(description = "The Name Attribute", currencyTimeLimit = 20, defaultValue = "bar", persistPolicy = "OnUpdate")
	public void setName(String name) {
		this.name = name;
	}

	// 把getter或setter标记为JMX的属性
	@ManagedAttribute(description = "The Age Attribute", currencyTimeLimit = 1)
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@ManagedAttribute(description = "The values Attribute", currencyTimeLimit = 1)
	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	// 把方法标记为JMX的操作
	@ManagedOperation(description = "Add two numbers")
	@ManagedOperationParameters({
			@ManagedOperationParameter(name = "x", description = "The first number"),
			@ManagedOperationParameter(name = "y", description = "The second number") })
	public int add(int x, int y) {
		return x + y;
	}

	public void dontExposeMe() {
		throw new RuntimeException();
	}

}
