package br.recomende.model.recommender.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.recomende.infra.util.SpringScope;

@Service
@Scope(SpringScope.PROTOTYPE)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Searcher {

}
