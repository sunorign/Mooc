package 领域特定语言

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class MapDelegate(val map: MutableMap<String, String>) : ReadWriteProperty<Any, String> {
    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        map[property.name] = value
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return map[property.name] ?: ""
    }

}