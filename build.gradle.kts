import org.jetbrains.kotlin.gradle.tasks.KotlinCompile




plugins {
    kotlin("jvm") version "1.7.10"
    id ("com.google.devtools.ksp") version "1.6.10-1.0.2"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {



    implementation("io.ktor:ktor-server-core:1.6.8")
    implementation("io.ktor:ktor-server-netty:1.6.8")
    implementation ("ch.qos.logback:logback-classic:1.2.11")

    implementation("io.ktor:ktor-client-core:1.6.8")
    implementation("io.ktor:ktor-client-core:1.6.8")
    implementation("io.ktor:ktor-client-serialization:1.6.8")
    implementation("io.ktor:ktor-client-logging:1.6.8")

/////
    implementation ("io.ktor:ktor-jackson:1.6.8")   // Google it
//    implementation ("io.ktor:ktor-server-content-negotiation:1.6.8")
//    implementation ("io.ktor:ktor-client-content-negotiation:1.6.8")
/////

    // KOIN ask @EOD
    implementation ("io.insert-koin:koin-core:3.0.1")
    implementation ("io.insert-koin:koin-ktor:3.0.1")

    implementation("org.jetbrains.exposed:exposed-core:0.38.2")
    implementation("org.jetbrains.exposed:exposed-dao:0.38.2")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.38.2")

    implementation("org.postgresql:postgresql:42.3.6")

    implementation("com.zaxxer:HikariCP:2.7.8")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.6.8")
    ksp ("io.insert-koin:koin-ksp-compiler:1.6.10-1.0.2")

    testImplementation(kotlin("test"))

    testImplementation("org.junit.jupiter:junit-jupiter:5.4.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.4.2")
    testImplementation("org.mockito:mockito-junit-jupiter:4.6.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}