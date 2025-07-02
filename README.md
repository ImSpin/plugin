# Fallen God Testament Plugin

A comprehensive Minecraft plugin for Paper 1.21.5 that tells the epic story of a fallen god through scattered testament fragments that players must collect and reunite at ancient forges in the Nether.

## Features

### Testament Fragments
- **7 Unique Fragments**: Each with its own lore and significance
  - Fragment of Creation
  - Fragment of Power
  - Fragment of Wisdom
  - Fragment of Destruction
  - Fragment of Redemption
  - Fragment of Sacrifice
  - Fragment of Eternity

### Ancient Nether Forges
- **Procedural Generation**: Massive forge structures generate naturally in the Nether
- **Sacred Altars**: Central altars where testament reunification takes place
- **Immersive Design**: Detailed structures with atmospheric lighting and decorations

### Fragment Discovery
- **Loot Chests**: Fragments can be found in various loot chests
- **Powerful Mobs**: Defeating boss-level creatures may reward fragments
- **Configurable Rates**: Adjustable spawn chances for balanced gameplay

### Testament Reunification
- **Ritual System**: Players must gather all fragments and perform the reunification at an altar
- **Epic Completion**: Spectacular effects and server-wide announcements
- **Rewards**: Customizable rewards for completing the testament

## Installation

1. Download the latest JAR file
2. Place it in your server's `plugins` folder
3. Start or restart your server
4. Configure the plugin in `plugins/FallenGodTestament/config.yml`

## Commands

### Player Commands
- `/testament help` - Show help information
- `/testament status` - View your fragment collection progress
- `/testament reunite` - Attempt testament reunification at an altar

### Admin Commands
- `/testament give <player> <fragment>` - Give a specific fragment to a player
- `/testament forge generate` - Generate a forge at your current location
- `/testament reload` - Reload the plugin configuration

## Permissions

- `fallengod.testament.use` - Basic testament commands
- `fallengod.admin.give` - Give fragments to players
- `fallengod.admin.forge` - Generate forges manually
- `fallengod.admin.reload` - Reload configuration

## Configuration

The plugin includes extensive configuration options:

```yaml
# Fragment spawn rates
testament:
  chest_spawn_chance: 0.15
  mob_drop_chance: 0.05

# Forge generation settings
forge:
  auto_generate: true
  generation_chance: 0.001
  min_distance: 5000
```

## The Story

Long ago, a powerful deity known as the Fallen God watched over the realms. Through hubris and cosmic tragedy, this divine being was cast down, their testament of wisdom and power scattered across the dimensions. 

Players who discover these fragments will uncover pieces of an ancient story - tales of creation, power, sacrifice, and the hope for redemption. Only by gathering all fragments and reuniting them at the sacred forges can the complete testament be restored.

## Building from Source

```bash
# Clone the repository
git clone <repository-url>

# Build with Maven
mvn clean package

# The JAR file will be in target/
```

## Requirements

- Paper 1.21.5 or newer
- Java 21+
- Maven (for building from source)

## Support

For issues, feature requests, or questions, please open an issue on the project repository.

---

*"In the depths of the Nether, where souls burn eternal, the forges await those brave enough to restore what was lost..."*