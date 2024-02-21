plugins {
    val kotlinVersion = "1.5.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.12.0"
}

group = "xyz.irosoralive"
version = "0.1B"

dependencies {
    api("com.google.code.gson:gson:2.9.0")
}

repositories {
    maven("https://maven.aliyun.com/repository/public")
    mavenCentral()
}
