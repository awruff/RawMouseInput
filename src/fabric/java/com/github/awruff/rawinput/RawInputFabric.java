package com.github.awruff.rawinput;

import net.fabricmc.api.ClientModInitializer;

public class RawInputFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        RawInputMod.initialize();
    }
}