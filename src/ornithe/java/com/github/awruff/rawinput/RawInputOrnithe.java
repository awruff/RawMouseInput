package com.github.awruff.rawinput;

import net.fabricmc.api.ClientModInitializer;


public class RawInputOrnithe implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        RawInputMod.initialize();
    }
}