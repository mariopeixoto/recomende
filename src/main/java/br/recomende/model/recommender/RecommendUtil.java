package br.recomende.model.recommender;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.recomende.infra.util.SpringScope;
import br.recomende.model.recommender.api.annotation.BeginMethod;

@Component
@Scope(SpringScope.APPLICATION)
public class RecommendUtil {
	
	private ApplicationContext applicationContext;
	
	@Autowired
	public RecommendUtil(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T invokeComponentMethod(Class<? extends Annotation> scannotation, Class<T> returnType, Object... params) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Map<String, Object> searchers = applicationContext.getBeansWithAnnotation(scannotation);
		for(Object obj : searchers.values()) {
			List<Method> methods = this.findBeginMethod(obj);
			for (Method method : methods) {
				if (this.match(method, returnType, params)) {
					return (T) method.invoke(obj, params);
				}
			}
		}
		return null;
	}

	private List<Method> findBeginMethod(Object obj) {
		List<Method> methods = new ArrayList<Method>();
		Class<?> objClass = obj.getClass();
		for (Method method : objClass.getDeclaredMethods()) {
			if (method.isAnnotationPresent(BeginMethod.class)) {
				methods.add(method);
			}
		}
		return methods;
	}
	
	private boolean match(Method method, Class<?> returnClass, Object...  params) {
		if (method != null) {
			Class<?>[] parametersTypes = method.getParameterTypes();
			if (parametersTypes.length == params.length) {
				for (int i = 0; i < parametersTypes.length; i++) {
					Class<?> parameterType = parametersTypes[i];
					if (!parameterType.isInstance(params[i])) {
						return false;
					}
				}
				if(returnClass.isAssignableFrom(method.getReturnType())) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
	
}
