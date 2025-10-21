
pluginManagement {
    repositories {
        mavenCentral()
        maven("https://maven.minecraftforge.net/")
        maven("https://maven.fabricmc.net/")
        maven("https://maven.wagyourtail.xyz/releases")
        maven("https://maven.wagyourtail.xyz/snapshots")
        gradlePluginPortal()
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.7.5"
}

rootProject.name = extra["mod.name"].toString()

stonecutter {
    create(rootProject) {
        versions("1.7.10", "1.8.9", "1.12.2")
        vcsVersion = "1.7.10"
    }
}