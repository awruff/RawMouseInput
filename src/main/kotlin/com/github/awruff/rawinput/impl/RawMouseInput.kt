package com.github.awruff.rawinput.impl

import com.github.awruff.rawinput.RawInputMod
import net.java.games.input.ControllerEnvironment
import net.java.games.input.Mouse
import net.minecraft.client.input.MouseInput
import kotlin.math.abs
import org.lwjgl.input.Mouse as LWJGLMouse

object RawMouseInput : MouseInput() {
    private const val DEADZONE = 5
    private const val MAX_FAILURES = 5

    private var pollFailures = 0
    private val mice = mutableListOf<Mouse>()

    init {
        reloadMice()
    }

    override fun tick() {
        dx = 0
        dy = 0

        if (mice.isEmpty()) return

        if (mice.all { it.poll() }.not()) reloadMice()

        dx = mice.sumOf { it.x.pollData.toInt() }
        dy = -mice.sumOf { it.y.pollData.toInt() }

        when {
            !isInDeadzone() && !hasMovement() -> {
                if (++pollFailures > MAX_FAILURES) reloadMice()
            }
            hasMovement() -> pollFailures = 0
        }
    }

    private val dX get() = LWJGLMouse.getDX()
    private val dY get() = LWJGLMouse.getDY()

    private fun isInDeadzone() =
        abs(dX) <= DEADZONE && abs(dY) <= DEADZONE

    private fun hasMovement() =
        dx != 0 || dy != 0

    private fun reloadMice() {
        runCatching {
            val clazz = Class.forName("net.java.games.input.${RawInputMod.environment}")
            val env = clazz.getDeclaredConstructor()
                .apply { isAccessible = true }
                .newInstance() as ControllerEnvironment

            mice.apply {
                clear()
                addAll(env.controllers.filterIsInstance<Mouse>())
            }
        }
    }
}
