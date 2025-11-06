package com.github.awruff.rawinput

import com.github.awruff.rawinput.impl.RawMouseInput
import com.github.awruff.rawinput.utils.LibraryChecker
import net.minecraft.client.Minecraft
import org.apache.commons.lang3.SystemUtils
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object RawInputMod {
    const val ID = "rawinput"
    const val NAME = "Raw Input"
    const val VERSION = "1.1.0"

    private val LOGGER: Logger = LogManager.getLogger(NAME)

    private const val RAW_PLUGIN = "DirectAndRawInputEnvironmentPlugin"
    private const val DX8_PLUGIN = "DirectInputEnvironmentPlugin"

    val environment: String by lazy {
        when {
            LibraryChecker.isLibraryPresent("jinput-raw") -> RAW_PLUGIN
            LibraryChecker.isLibraryPresent("jinput-dx8") -> DX8_PLUGIN
            else -> ""
        }
    }

    fun initialize() {
        LOGGER.info("Initializing {} v{} (id={})", NAME, VERSION, ID)

        if (environment.isNotEmpty()) {
            LOGGER.info("Using input environment: {}", environment)
            Minecraft.getInstance().mouse = RawMouseInput
        } else {
            LOGGER.warn(
                "No suitable input environment found. Checked libraries: [jinput-raw, jinput-dx8]. " +
                        "System details: OS={} Version={} Arch={}",
                SystemUtils.OS_NAME,
                SystemUtils.OS_VERSION,
                SystemUtils.OS_ARCH
            )
        }
    }
}