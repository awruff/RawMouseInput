plugins {
    kotlin("jvm") version ("2.2.21")
    id("xyz.wagyourtail.unimined") version ("1.4.1")
}

group = "com.github.awruff.rawinput"
version = "1.1.0"

unimined.useGlobalCache = false

val main = sourceSets.main.get()
val fabric by sourceSets.creating
val ornithe by sourceSets.creating

unimined.minecraft {
    version("1.7.2") // 1.7.2 is the oldest version with Log4j
    side("client")

    mappings {
        calamus()
        feather(28)
    }

    defaultRemapJar = false
}

unimined.minecraft(fabric) {
    combineWith(main)

    legacyFabric {
        loader("0.17.3")
    }

    defaultRemapJar = true
}

unimined.minecraft(ornithe) {
    combineWith(main)

    ornitheFabric {
        loader("0.17.3")
    }

    defaultRemapJar = true
}

dependencies {
    "modImplementation"("net.fabricmc:fabric-language-kotlin:1.13.7+kotlin.2.2.21")
}

kotlin {
    jvmToolchain(8)
}