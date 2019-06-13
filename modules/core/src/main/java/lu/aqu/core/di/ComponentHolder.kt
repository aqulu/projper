package lu.aqu.core.di

import kotlin.reflect.KClass

/**
 * helper class for using dependency injection in multi-module project
 */
interface ComponentHolder {

    fun <T : Component> getComponent(componentClass: KClass<T>): T
}
