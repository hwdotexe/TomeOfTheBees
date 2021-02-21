## Overview
A Minecraft plugin that adds a JAR of BEES to the game. Open the jar and unleash a swarm of the game's cutest deadly mob.

## Usage
Use the `/bees` command to get a jar of bees, or to inflict statuses on other players. Statuses can affect how bees interact with that player, making for even more fun with our flying friends.

Status types:  
`ALLERGY` - Any damage done by bees is doubled  
`HONEY_SMELL` - Bees think you stole their honey, so they will target you  
`QUEEN_BEE` - Bees can no longer target you

### Commands
/bees

### Permissions
bees.use - Use the command  
bees.jar - Get a new Jar of Bees  
bees.status - Manage status effects

## Installation (Server)
To use the plugin, simply download a `.jar` binary from the [Releases](https://github.com/hwdotexe/TomeOfTheBees/releases) page for your server's version, and restart.

## Installation (Development)
If you'd like to install the codebase and make changes, simply follow these instructions:

### Step 1: Clone
In a terminal, use `git clone https://github.com/hwdotexe/TomeOfTheBees.git`

### Step 2: Import as a Maven Project
This plugin uses Maven as its build system. As such, you'll need to import it using Maven as well. This process varies by IDE, so please seek out their instructions if needed.

### Step 3: Building
When you're ready to compile the plugin locally, you can use `mvn clean package` to generate your `.jar` file. 

## Contributing
If you like this plugin and want to contribute, please do! I welcome any and all Pull Requests from eager developers who want to help out. 
