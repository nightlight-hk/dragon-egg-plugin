# dragon-egg-plugin

A plugin for 1.21.11 paper / folia minecraft server to adjust the rarity of dragon egg

## version

- v1.0 SNAPSHOT: 10% dragon egg drop rate, ban dragon egg duping

## How to use it?

1. Choose your preferred version of the plugin in the releases section
2. Download the jar file
3. Put the jar file in your server's plugins folder
4. Restart your server

## I made changes to the plugin, how do I build it myself?

0. make sure you have Java and Gradle installed on your machine
1. clone the repo
2. cd into the directory you cloned
3. run `gradle build` in terminal
4. the plugin jar will be in `build/libs/`

## Some common changes you might want to make

1. Change the dragon egg drop rate: change the value of `EGG_CHANCE` in `DragonEggDropListener.java`
2. I do not want to remove the dragon egg entity, but instead some other actions: change line 37 of DragonEggPortalListener.java

Make sure to rebuild the plugin after making changes and put the new jar file in your server's plugins folder, replacing the old one.