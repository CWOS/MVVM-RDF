package com.cw.rdf.core.ext

import com.google.gson.reflect.TypeToken
import okhttp3.Interceptor
import org.koin.core.definition.BeanDefinition
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import retrofit2.Converter
import java.lang.reflect.Type

/**
 *
 * @Description:扩展 koin
 * @Author: chengminghui
 * @CreateDate：2020/8/22
 *
 */
fun Module.interceptor(order: Int, override: Boolean = false, definition: Scope.() -> Interceptor): BeanDefinition<InterceptorMap> {
    return factory(named(order.toString()), override) {
        InterceptorMap(order, definition.invoke(this))
    }
}


fun Scope.getInterceptor(): Map<Int, Interceptor> {

    val list = getAll<InterceptorMap>()

    val map = HashMap<Int, Interceptor>()
    list.forEach {
        map[it.order] = it.interceptor
    }

    return map
}

fun Module.converterFactory(order: Int, override: Boolean = false, definition: Scope.() -> Converter.Factory): BeanDefinition<ConverterFactoryMap> {
    return factory(named(order.toString()), override) {
        ConverterFactoryMap(order, definition.invoke(this))
    }
}


fun Scope.getConverterFactory(): Map<Int, Converter.Factory> {

    val list = getAll<ConverterFactoryMap>()

    val map = HashMap<Int, Converter.Factory>()
    list.forEach {
        map[it.order] = it.factory
    }

    return map
}


inline fun <reified T> typed() = TypeTokenQualifier(object : TypeToken<T>() {}.type)
data class TypeTokenQualifier(val type: Type) : Qualifier {
    override val value: QualifierValue = type.toString()
    override fun toString(): String {
        return "q:'$value'"
    }
}

data class InterceptorMap(val order:Int, val interceptor : Interceptor)
data class ConverterFactoryMap(val order:Int, val factory : Converter.Factory)
