plugins {
    id("java")
    kotlin("jvm") version ("2.2.21")
    id("xyz.wagyourtail.unimined") version ("1.4.1")
}

group = "com.github.awruff.rawinput"
version = "1.1.0"

unimined.useGlobalCache = false

unimined.minecraft {
    version("1.7.2")
    side("client")

    mappings {
        calamus()
        feather(28)
    }

    ornitheFabric {
        loader("0.17.3")
    }
}

dependencies {
    "modImplementation"("net.fabricmc:fabric-language-kotlin:1.13.7+kotlin.2.2.21")
}