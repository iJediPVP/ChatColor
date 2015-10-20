# ChatColor
**This plugin allows players to change:**
* Name color & style (bold, underline, etc..)
  * If **"nameTagEnabled"** is set to true in the config.yml, the name above the player's head will also change. This uses the scoreboard system so you may want to keep this set to false.
* Chat color & style (bold, underline, etc..)

**Commands**
* /chat
  * Open up the chat editor menu.
  * Usage: **/chat**
* /msg
  * Message a player.
  * Usage: **/msg \<player\> \<message\>**
* /reply
  * Reply to the last person you messaged or received a message from.
  * Usage: **/reply \<message\>**

**Permissions**
* chatColor.*
  * Give access to all of the commands & magic style.
* chatColor.command.*
  * Give access to all commands
* chatColor.command.chat
  * Give access to /chat command.
* chatColor.command.msg
  * Give access to /msg and /tell commands.
* chatColor.command.reply
  * Give access to /reply command.
* chatColor.style.magic
  * Give access to magic style.
