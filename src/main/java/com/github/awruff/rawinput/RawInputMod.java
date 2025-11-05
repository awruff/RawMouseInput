package com.github.awruff.rawinput;

import com.github.awruff.rawinput.impl.RawMouseInput;
import com.github.awruff.rawinput.utils.LibraryChecker;
import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RawInputMod {
    public final static String ID = "rawinput";
    public final static String NAME = "Raw Input";
    public final static String VERSION = "1.0.0";

    private static final Logger LOGGER = LogManager.getLogger(NAME);

    public static String environment = "";

    public static void initialize() {
        LOGGER.debug("Initializing");
        boolean direct = LibraryChecker.isLibraryLoaded("jinput-dx8");
        boolean raw = LibraryChecker.isLibraryLoaded("jinput-raw");

        if (raw || direct) {
            if (raw) {
                environment = "DirectAndRawInputEnvironmentPlugin";
            } else {
                environment = "DirectInputEnvironmentPlugin";
            }

            LOGGER.debug("Environment set to {}", environment);

            Minecraft.getInstance().mouse = new RawMouseInput();
        }

        if (environment.isEmpty()) {
            LOGGER.warn("Environment is blank... {} {} {}", SystemUtils.OS_NAME, SystemUtils.OS_VERSION, SystemUtils.OS_ARCH);
        }
    }
}
