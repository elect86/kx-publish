package kx

//import java.io.ByteArrayOutputStream
//
//val gitDescribe: String
//    get() {
//        val stdout = ByteArrayOutputStream()
//        rootProject.exec {
//            commandLine("git", "describe", "--tags")
//            standardOutput = stdout
//        }
//        return stdout.toString().trim().replace(Regex("-g([a-z0-9]+)$"), "-$1")
//    }