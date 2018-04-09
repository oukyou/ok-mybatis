/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/09.
 */
package com.oukyou.mybatis.plugins;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 拦截器代理类
 */
public final class InterceptorProxy implements InvocationHandler {

	/**
	 * 调用元对象类
	 */
	private final Object target;
	/**
	 * 拦截器
	 */
	private final Interceptor interceptor;
	/**
	 * 拦截类方法Map<br>
	 * key=被拦截类,value=被拦截方法
	 */
	private final Map<Class<?>, Set<Method>> signatureMap;

	/**
	 * 构造方法
	 * 
	 * @param target 调用元对象类
	 * @param interceptor 拦截器
	 * @param signatureMap 拦截类方法Map
	 */
	public InterceptorProxy(Object target, Interceptor interceptor, Map<Class<?>, Set<Method>> signatureMap) {
		this.target = target;
		this.interceptor = interceptor;
		this.signatureMap = signatureMap;
	}

	/**
	 * 动态生成实例
	 * 
	 * @param target 调用元对象类
	 * @param interceptor 拦截器
	 * @return 拦截器的代理实现类实例
	 * @throws Exception 处理例外
	 */
	public static Object newInstance(Object target, Interceptor interceptor) throws Exception {
		// 配置拦截签名的类方法Map
		Map<Class<?>, Set<Method>> signatureMap = getSignatureMap(interceptor);
		Class<?> calzz = target.getClass();
		// 获取所有接口
		Class<?>[] interfaces = getAllInterfaces(calzz, signatureMap);
		if (interfaces.length > 0) {
			// 动态代理生成拦截器实现类
			return Proxy.newProxyInstance(calzz.getClassLoader(), interfaces,
					new InterceptorProxy(target, interceptor, signatureMap));
		}
		return target;
	}

	/**
	 * 获取所有接口
	 * 
	 * @param calzz 对象类
	 * @param signatureMap 配置拦截签名的类方法Map
	 * @return 所有接口
	 */
	private static Class<?>[] getAllInterfaces(Class<?> calzz, Map<Class<?>, Set<Method>> signatureMap) {
		Set<Class<?>> interfaces = new HashSet<Class<?>>();
		while (calzz != null) {
			for (Class<?> c : calzz.getInterfaces()) {
				if (signatureMap.containsKey(c)) {
					interfaces.add(c);
				}
			}
			calzz = calzz.getSuperclass();
		}
		return interfaces.toArray(new Class<?>[interfaces.size()]);
	}

	/**
	 * 获取配置拦截签名的类方法Map
	 * 
	 * @param interceptor 拦截器
	 * @return 配置拦截签名的类方法Map
	 * @throws Exception 处理例外
	 */
	private static Map<Class<?>, Set<Method>> getSignatureMap(Interceptor interceptor) throws Exception {
		// 获取配置拦截信息
		Intercepts intercepts = (Intercepts) interceptor.getClass().getAnnotation(Intercepts.class);
		if (intercepts == null) {
			throw new Exception("没有定义拦截器" + interceptor.getClass().getName());
		}

		Signature[] signatures = intercepts.value();
		Map<Class<?>, Set<Method>> methodMap = new HashMap<Class<?>, Set<Method>>();
		// 生成被拦截方法的Set
		for (Signature signature : signatures) {
			Set<Method> methods = methodMap.get(signature.type());
			if (methods == null) {
				methods = new HashSet<Method>();
				methodMap.put(signature.type(), methods);
			}

			Method method = signature.type().getMethod(signature.method(), signature.args());
			methods.add(method);
		}

		return methodMap;
	}

	/**
	 * @see InvocationHandler#invoke(Object, Method, Object[])
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Set<Method> methods = signatureMap.get(method.getDeclaringClass());
		// 需要被拦截的方法时执行拦截器的方法
		if (methods != null && methods.contains(method)) {
			return interceptor.intercept(new Invocation(target, method, args));
		}

		// 执行原方法
		return method.invoke(target, args);
	}

}
