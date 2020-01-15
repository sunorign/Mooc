package 面向对象

//输入设备
interface InputDevice {
    fun input(i: Any)
}

//USB 输入设备
interface USBInputDevice : InputDevice

//USB 接口鼠标
abstract class USBMouse(val name: String) : USBInputDevice {
    override fun input(i: Any) {
    }

    override fun toString(): String {
        return name
    }
}

//罗技 USB 接口鼠标
class LogitechMouse : USBMouse("罗技鼠标") {

}

class Computer {
    fun addUSBInputDevice(inputDevice: USBInputDevice) {
        //插入输入设备
        println("add usb input device：$inputDevice");
    }

    fun addInputDevice(inputDevice: InputDevice) {
        when (inputDevice) {
            is USBInputDevice -> {
                addUSBInputDevice(inputDevice)
            }
            else -> {
                throw IllegalArgumentException("输入设备不支持")
            }
        }
    }
}

fun main(args: Array<String>) {
    val computer = Computer()
    val mouse = LogitechMouse()
    computer.addUSBInputDevice(mouse)
}