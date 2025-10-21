plugins {
    id("java")
    id("xyz.wagyourtail.unimined") version ("1.4.1")
}

group = "com.github.awruff.rawinput"
version = "1.0.0"

unimined.useGlobalCache = false

val forge by sourceSets.creating
val fabric by sourceSets.creating
val ornithe by sourceSets.creating

unimined.minecraft {
    version(property("mc.version").toString())

    mappings {
        searge()
        mcp("stable", property("mapping.version").toString())
    }

    defaultRemapJar = false
}

unimined.minecraft(forge) {
    combineWith(sourceSets.main.get())

    minecraftForge {
        loader(property("forge.version").toString())
    }

    defaultRemapJar = true
}

unimined.minecraft(fabric) {
    combineWith(sourceSets.main.get())

    legacyFabric {
        loader(property("fabric.version").toString())
    }

    defaultRemapJar = true
}

unimined.minecraft(ornithe) {
    combineWith(sourceSets.main.get())

    ornitheFabric {
        loader(property("fabric.version").toString())
    }

    defaultRemapJar = true
}

stonecutter {
    replacements.string {
        direction = eval(current.version, ">1.7.10")
        replace("cpw.mods.fml", "net.minecraftforge.fml")
    }
}

tasks.processResources {
    val minecraft = stonecutter.current.version

    filesMatching("fabric.mod.json") { expand(mapOf(
        "minecraft" to minecraft
    )) }

    filesMatching("mcmod.info") { expand(mapOf(
        "mcversion" to minecraft
    )) }
}