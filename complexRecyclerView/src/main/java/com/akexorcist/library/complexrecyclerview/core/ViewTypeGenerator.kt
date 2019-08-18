package com.akexorcist.library.complexrecyclerview.core

import kotlin.random.Random

object ViewTypeGenerator {
    @Suppress("MemberVisibilityCanBePrivate")
    const val UNKNOWN = -1

    private val map: MutableMap<String, Int> = mutableMapOf()

    fun getValue(data: Class<out Any>, factory: ComplexAdapter.Factory<*>): Int =
        getValue(data, factory.javaClass)

    @Suppress("MemberVisibilityCanBePrivate")
    fun getValue(data: Class<out Any>, factory: Class<*>): Int {
        val name: String? =
            getKeyName(
                data,
                factory
            )
        return if (name != null && map.containsKey(name)) {
            map[name] ?: UNKNOWN
        } else {
            add(data, factory)
        }
    }

    fun getKey(type: Int): String? {
        return (map.filter { map -> map.value == type }).entries.elementAtOrNull(0)?.key
    }

    fun validate(type: Int, data: Class<out Any>, factory: ComplexAdapter.Factory<*>): Boolean =
        validate(type, data, factory.javaClass)

    @Suppress("MemberVisibilityCanBePrivate")
    fun validate(type: Int, data: Class<out Any>, factory: Class<*>): Boolean {
        if (data.canonicalName == null || factory.canonicalName == null) {
            return false
        }
        val key = getKey(type)
        return key == "${factory.canonicalName}/${data.canonicalName}"
    }

    private fun add(data: Class<out Any>, factory: Class<out Any>): Int {
        val name: String = getKeyName(
            data,
            factory
        ) ?: return UNKNOWN
        if (map.containsKey(name)) {
            return map[name] ?: UNKNOWN
        }
        var random: Int
        do {
            random = Random.nextInt()
        } while (random == UNKNOWN && map.containsValue(random))
        map[name] = random
        return random
    }

    private fun getKeyName(data: Class<out Any>, factory: Class<out Any>): String? {
        if (data.canonicalName == null || factory.canonicalName == null) {
            return null
        }
        return "${factory.canonicalName}/${data.canonicalName}"
    }
}
