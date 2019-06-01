package lu.aqu.core.di

import kotlin.reflect.KClass

interface ComponentHolder {

    fun <T : Injector> getComponent(componentClass: KClass<T>): T
}
