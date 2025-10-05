package com.github.awruff.rawinput;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(
        modid = RawInputMod.ID,
        name = RawInputMod.NAME,
        version = RawInputMod.VERSION
)
public class RawInputForge {
    @Mod.EventHandler
    public void onFMLInitialization(FMLInitializationEvent event) {
        RawInputMod.initialize();
    }
}
