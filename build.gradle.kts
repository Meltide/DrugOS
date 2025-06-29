plugins {
    id("java")
    id("application")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "team.mpga"
version = "1.0"

repositories {
    maven {
        url = uri("https://maven.aliyun.com/repository/public/")
    }
    mavenLocal()
    mavenCentral()
}

application {
    mainClass.set("Main")
    applicationDefaultJvmArgs = listOf(
        "--enable-native-access=ALL-UNNAMED",
        "--add-exports=java.base/sun.nio.ch=ALL-UNNAMED"
    )
}

dependencies {
    implementation("net.objecthunter:exp4j:0.4.8")
    implementation("org.fusesource.jansi:jansi:2.4.2")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.withType<JavaExec> {
    jvmArgs = listOf(
        "--enable-native-access=ALL-UNNAMED",
        "--add-exports=java.base/sun.nio.ch=ALL-UNNAMED"
    )
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

tasks.test {
    useJUnitPlatform()
}