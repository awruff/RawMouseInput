plugins {
    id("java")
    id("xyz.wagyourtail.unimined") version ("1.4.1")
}

group = "com.github.awruff.rawinput"
version = "1.0.1"

unimined.useGlobalCache = false

val main = sourceSets.main.get()
val forge by sourceSets.creating
val fabric by sourceSets.creating
val ornithe by sourceSets.creating

unimined.minecraft {
    version("1.8.9")
    side("client")

    mappings {
        calamus()
        feather(28)

        stubs("searge", "feather") {
            c("net/minecraft/entity/item/EntityMinecart") {
                m("func_174898_m;()D", "getMaxSpeedVanilla")
            }
            c(null, "net/minecraft/util/registry/IdRegistry") {
                m("func_148757_b;(Ljava/lang/Object;)I", "getByValue")
            }
        }
    }

    defaultRemapJar = false
}

unimined.minecraft(forge) {
    combineWith(main)

    minecraftForge {
        loader("11.15.1.2318-1.8.9")
    }

    defaultRemapJar = true
}

unimined.minecraft(fabric) {
    combineWith(main)

    legacyFabric {
        loader("0.17.3")
    }

    defaultRemapJar = true
}

unimined.minecraft(ornithe) {
    combineWith(fabric)

    ornitheFabric {
        loader("0.17.3")
    }

    defaultRemapJar = true
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}