plugins {
	java
	id("org.springframework.boot") version "3.4.0"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")

	implementation("ch.qos.logback:logback-classic")

	// Validation
	implementation("org.springframework.boot:spring-boot-starter-validation:3.4.2")

	// Spring JDBC
	implementation("org.springframework:spring-jdbc:6.2.3")
	// spring-boot-starter-jdbc
	implementation("org.springframework.boot:spring-boot-starter-jdbc:3.4.4")
	// starter-data-jdbc
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc:3.4.1")

	// H2 Database
	runtimeOnly("com.h2database:h2")

	//Lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
