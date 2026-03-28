import gg.meza.stonecraft.mod

plugins {
    id("gg.meza.stonecraft")
}

modSettings {
    clientOptions {
        fov = 110
        guiScale = 3
        narrator = false
        darkBackground = true
        musicVolume = 0.0
    }
}

repositories {
    maven {
        name = "Terraformers"
        url = uri("https://maven.terraformersmc.com/")
    }
    maven {
        name = "ParchmentMC"
        url = uri("https://maven.parchmentmc.org")
    }
}

dependencies {
    if (project.name.endsWith("-fabric")) {
        modImplementation("com.terraformersmc:modmenu:${project.property("modmenu_version")}")
    }
}

sourceSets {
    main {
        java {
            // Check if we are currently building/evaluating a NeoForge version
            if (project.name.contains("forge")) {
                // Exclude the Fabric-specific ModMenu implementation
                exclude("**/impl/ModMenuImpl.java")

                // If you have a Fabric-only mixin config, exclude that too:
                // exclude("architectury.common.json")
            }
        }
    }
}

publishMods {
    modrinth {
        if (mod.isFabric) requires("fabric-api")
        projectId = "oUH7cO2R"
        accessToken = providers.environmentVariable("MODRINTH_TOKEN")
        dryRun = false

        val versionsString = project.property("minecraft_version") as String
        minecraftVersions.set(versionsString.split(",").map { it.trim() })
    }
}
