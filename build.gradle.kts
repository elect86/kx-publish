
plugins {
    `kotlin-dsl`
//    java
    `maven-publish`
}

repositories {
    mavenCentral()
}

group = "kotlin.graphics.build-logic"
version = "0.1.1"

publishing {
    //    publications.create<MavenPublication>("maven") {
    //        from(components["java"])
    //    }
    repositories.maven {
        url = uri("$rootDir/../mary")
        //            url = uri("../mary")
    }
}