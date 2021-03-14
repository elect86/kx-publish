package kx

import java.io.ByteArrayOutputStream

plugins {
    java
}

val gitDescribe: String
    get() = ByteArrayOutputStream().also {
        rootProject.exec {
            commandLine("git", "describe", "--tags")
            standardOutput = it
        }
    }.toString().trim().replace(Regex("-g([a-z0-9]+)$"), "-$1")

tasks {
    register("commit&push") {
        group = "kx"
        doLast {
            rootProject.exec { commandLine("git", "add", ".") }
            var message = gitDescribe.substringBeforeLast('-')
            val commits = message.substringAfterLast('-').toInt() + 1
            message = message.substringBeforeLast('-') + "-$commits"
            rootProject.exec { commandLine("git", "commit", "-m", message) }
            rootProject.exec { commandLine("git", "push") }
        }
    }
    register("publishSnapshot") {
        group = "kx"
        dependsOn("commit&push")
        finalizedBy(
            subprojects.map { it.tasks.matching { task -> task.name == "publish" } },
            "commit&pushMary") // dependencies
    }
    register("commit&pushMary") {
        group = "kx"
        doLast {
            val maryDir = file("$rootDir/../mary")
            rootProject.exec {
                workingDir = maryDir
                commandLine("git", "add", ".")
            }
            val message = """
                |$project :arrow_up:
                |snapshot $gitDescribe
            """.trimMargin()
            rootProject.exec {
                workingDir = maryDir
                commandLine("git", "commit", "-m", message)
            }
            rootProject.exec {
                workingDir = maryDir
                commandLine("git", "push")
            }
        }
        mustRunAfter("publishSnapshot") // order
    }
}