plugins {
    val kotlinVersion = "1.8.0"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.12.0"
}

group = "xyz.irosoralive"
version = "0.1B"

dependencies {
    api("com.google.code.gson:gson:2.9.0")
    compileOnly("xyz.cssxsh.mirai:mirai-hibernate-plugin:2.8.0")
}

mirai {
    jvmTarget = JavaVersion.VERSION_11
}

repositories {
    maven("https://maven.aliyun.com/repository/public")
    mavenCentral()
}
