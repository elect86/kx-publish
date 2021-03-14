
package kx

//plugins {
//    java
//}

//val gitDescribe: String
//    get() {
//        val stdout = java.io.ByteArrayOutputStream()
//        rootProject.exec {
//            commandLine("git", "describe", "--tags")
//            standardOutput = stdout
//        }
//        return stdout.toString().trim().replace(Regex("-g([a-z0-9]+)$"), "-$1")
//    }
//
//tasks {
//    register("commit&push") {
//        group = "kx"
//        doLast {
//            rootProject.exec { commandLine("git", "add", ".") }
//            var message = gitDescribe.substringBeforeLast('-')
//            val commits = message.substringAfterLast('-').toInt() + 1
//            message = message.substringBeforeLast('-') + "-$commits"
//            rootProject.exec { commandLine("git", "commit", "-m", message) }
//            rootProject.exec { commandLine("git", "push") }
//        }
//    }
//    register("publishSnapshot") {
//        group = "kx"
//        doLast {
//            subprojects { version = gitDescribe }
//            println("publish")
//        }
//        dependsOn("commit&push")
//        finalizedBy(
//            getTasksByName("publish", true),
//            "commit&pushMary")
//    }
//    register("commit&pushMary") {
//        group = "kx"
//        doLast {
//            val maryDir = file("$rootDir/../mary")
//            rootProject.exec {
//                workingDir = maryDir
//                commandLine("git", "add", ".")
//            }
//            val message = """
//                |$project :arrow_up:
//                |snapshot $gitDescribe
//            """.trimMargin()
//            rootProject.exec {
//                workingDir = maryDir
//                commandLine("git", "commit", "-m", message)
//            }
//            rootProject.exec {
//                workingDir = maryDir
//                commandLine("git", "push")
//            }
//        }
//    }
//}