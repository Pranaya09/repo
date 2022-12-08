import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.powerShell
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.triggers.vcs

version = "2022.10"

project {

    buildType(BuildRepo)
    buildType(BuildRepoNext)
    buildType(BuildRepo1)
    buildType(BuildRepo2)


    sequential {
        buildType(BuildRepo)
        parallel {
            buildType(BuildRepo1)
            buildType(BuildRepo2)
        }
        buildType(BuildRepoNext)
    }
}

object BuildRepo : BuildType({
    name = "Build_repository"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        powerShell {
            scriptMode = file {
                path = "script3.ps1"
            }
            noProfile = false
        }
        script {
            scriptContent = "echo Hello"
        }

    }


    features {
        perfmon {
        }
    }
})
object BuildRepo1 : BuildType({
    name = "Build_repository1"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {

        script {
            scriptContent = "echo Hello1"
        }

    }


    features {
        perfmon {
        }
    }
})

object BuildRepo2 : BuildType({
    name = "Build_repository2"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {

        script {
            scriptContent = "echo Hello2"
        }

    }


    features {
        perfmon {
        }
    }
})
object BuildRepoNext : BuildType({
    name = "Build_repositoryNext"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {

        script {
            scriptContent = "echo HelloooWorld"
        }

    }


    triggers {
        vcs {
        }

    }

    features {
        perfmon {
        }
    }
})





