package com.github.awruff.rawinput;

import com.github.awruff.rawinput.impl.RawMouseInput;
import com.github.awruff.rawinput.utils.LibraryChecker;
import net.minecraft.client.Minecraft;

public class RawInputMod {
    public final static String ID = "rawinput";
    public final static String NAME = "Raw Input";
    public final static String VERSION = "1.0.0";

    public static String environment = "";

    public static void initialize() {
        boolean direct = LibraryChecker.isLibraryLoaded("jinput-dx8");
        boolean raw = LibraryChecker.isLibraryLoaded("jinput-raw");

        if (raw || direct) {
            if (raw) {
                environment = "DirectAndRawInputEnvironmentPlugin";
            } else {
                environment = "DirectInputEnvironmentPlugin";
            }

            Minecraft.getMinecraft().mouseHelper = new RawMouseInput();
        }
    }
}
