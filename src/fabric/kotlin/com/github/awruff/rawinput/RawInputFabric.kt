package com.github.awruff.rawinput

import net.fabricmc.api.ClientModInitializer

object RawInputFabric : ClientModInitializer {
    override fun onInitializeClient() {
        RawInputMod.initialize()
    }
}