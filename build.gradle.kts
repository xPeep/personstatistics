import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.5"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.32"
	kotlin("plugin.spring") version "1.4.32"
	kotlin("plugin.jpa") version "1.4.32"
	kotlin("plugin.allopen") version "1.4.32"
}

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.Embeddable")
	annotation("javax.persistence.MappedSuperclass")
}

group = "cz.upce.nnpia"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_15

repositories {
	mavenCentral()
}

val jsonwebtokenVersion = "0.11.2"
val jaxbLibs = "2.3.2"

dependencies {
	//jwt
	implementation("io.jsonwebtoken:jjwt-api:$jsonwebtokenVersion")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:$jsonwebtokenVersion")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jsonwebtokenVersion")

	implementation("jakarta.xml.bind:jakarta.xml.bind-api:$jaxbLibs")
	implementation("org.glassfish.jaxb:jaxb-runtime:$jaxbLibs")

	//guava
	implementation("com.google.guava:guava:29.0-jre")

	//spring boot
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-mustache")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	//class kotlin
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.junit.jupiter:junit-jupiter:5.7.0")

	// database
	runtimeOnly("com.h2database:h2")
	runtimeOnly("org.postgresql:postgresql")

	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
