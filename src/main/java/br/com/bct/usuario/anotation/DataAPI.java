package br.com.bct.usuario.anotation;

import br.com.bct.usuario.anotation.deserializer.LocalDateTimeDeserializer;
import br.com.bct.usuario.anotation.serializer.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JsonSerialize(using = LocalDateTimeSerializer.class)
@JsonDeserialize(using = LocalDateTimeDeserializer.class)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface DataAPI {
}

