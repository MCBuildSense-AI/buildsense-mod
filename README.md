# BuildSense AI

BuildSense AI is a Fabric Minecraft mod that helps players plan builds around terrain. The player selects an area, scans the terrain, enters a build idea, and receives a structured build plan that can later be rendered as in-world outlines or ghost markers.

The project is currently in early alpha development.

## Current Status

This repository currently contains the initial Fabric mod workspace.

Implemented so far:

* Fabric project scaffold
* Gradle/Loom setup
* Main mod entrypoint
* Basic startup log proving the mod loads

Not implemented yet:

* Commands
* Area selection
* Terrain scanning
* Fallback planner
* AI provider integration
* Overlay rendering
* HUD controls
* Public alpha release

## Product Direction

BuildSense AI is planned as a terrain-aware build planner, not an autonomous builder.

The intended core loop is:

1. Player selects an area.
2. Mod scans the terrain.
3. Player enters a build idea.
4. The fallback planner creates a safe basic plan.
5. Optional AI returns a structured JSON build plan.
6. The mod validates the plan.
7. The mod renders in-world outlines or ghost markers.
8. Player cycles, accepts, clears, or regenerates the plan.

## What BuildSense AI Is Not

BuildSense AI is not:

* An NPC chatbot mod
* An autonomous Minecraft bot
* A block-by-block auto-builder in v1
* A model training or fine-tuning project
* A Python sidecar project in v1
* A paid Minecraft mod

The alpha goal is to prove the build-planning and overlay idea first.

## Planned Alpha Features

Planned features for `v0.1.0-alpha`:

* Fabric-only mod
* Single-player/local world first
* `/buildsense` command tree
* Area selection with `pos1` and `pos2`
* 32x32 alpha scan limit
* Terrain summary generation
* Non-AI fallback planner
* Strict BuildPlan JSON model
* Optional Ollama/OpenAI-compatible AI provider
* Plan validation before rendering
* Wireframe/ghost overlay renderer
* HUD card showing plan title, fit score, and controls
* Clear setup documentation

## Development Setup

Recommended tools:

* IntelliJ IDEA Community Edition
* Java/JDK matching the selected Minecraft/Fabric version
* Gradle
* Fabric Loom
* Git and GitHub

To run the client from the project root:

```bash
./gradlew runClient
```

On Windows PowerShell:

```powershell
.\gradlew runClient
```

## Project Structure

Expected early structure:

```text
buildsense-mod/
  build.gradle
  settings.gradle
  gradle.properties
  src/main/java/com/buildsense/
    BuildSenseMod.java
  src/main/resources/
    fabric.mod.json
```

Future planned package structure:

```text
com.buildsense.command
com.buildsense.config
com.buildsense.selection
com.buildsense.scan
com.buildsense.planning
com.buildsense.ai
com.buildsense.render
com.buildsense.input
com.buildsense.util
```

## Current Proof of Load

The first milestone is complete when the Minecraft client launches and the console shows a log similar to:

```text
BuildSense AI loaded. First Fabric entrypoint is working.
```

## Development Workflow

The project should follow a simple Git workflow:

* `main` should always run.
* Work should happen on feature branches.
* Each feature should be reviewed before merge.
* Small commits are preferred.
* Visual features should include screenshots or short clips when possible.

Example branch names:

```text
feature/fabric-bootstrap
feature/buildsense-command
feature/area-selection
feature/terrain-scan
feature/fallback-planner
feature/overlay-renderer
```

## Definition of Done

A task is only done when:

* Code compiles.
* Minecraft launches.
* The feature works through a command or visible UI.
* Bad input does not crash the game.
* Logs are readable.
* The teammate reviewed the change.
* README or dev notes are updated if behaviour changed.

## Roadmap

Early roadmap:

1. Bootstrap Fabric workspace
2. Add `/buildsense hello` and `/buildsense version`
3. Add config file loading
4. Add `pos1` / `pos2` area selection
5. Add basic terrain scanning
6. Add TerrainSummary JSON
7. Add fallback planning
8. Add plan validation
9. Add overlay rendering
10. Add optional AI provider integration
11. Package public alpha

## License

License to be confirmed before public release.
