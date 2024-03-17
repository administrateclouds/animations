
# Animations

[![License](https://img.shields.io/github/license/administrateclouds/animations?style=flat-square)](LICENSE)
[![Stars](https://img.shields.io/github/stars/administrateclouds/animations?style=flat-square)](#)

Animations is a fully-featured free and open-source animation system for Minecraft Servers utilizing Resource Packs. This project is licensed under a **AGPL** license.

![alt text](https://us-east-1.tixte.net/uploads/i.itspolar.dev/javaw_IQ7XaVDNBB.gif)
<br/>
*Animation by CloudX, Skin by DisneyArchitect*

## Features
- **Universal Support**. Create animations without restrictions, using either [Blender](https://www.blender.org/) or [Blockbench](https://www.blockbench.net/) using the  [AnimatedJava](https://github.com/Animated-Java/animated-java) plugin by SnaveSutit with their JSON exporter.
- **Squash & Stretch Support** with Display Entities, allowing you to use typical animation standards for your Minecraft animated rigs!
- **Runs at the Game Framerate**. Thanks to Display Entities, we are also able to make animations smooth regardless of your servers' TPS with their build-in interpolation!
- **Free and open-source**. The code is free and open for anyone to audit and contribute to.

## Compatibility

You can use this plugin on any Minecraft server running the latest version of the game, at this point, that is **1.20.4**.

Please note that this is currently in very early snapshot releases, so you will need to build this project yourself until we are ready to begin creating public releases!
## FAQ

#### How can I use AnimatedJava?

You're likely better off reading the AnimatedJava Docs located [here](https://animated-java.dev/docs/home), you need to utilise the **JSON Exporter** in order to work with this plugin.

#### How can I export my Blender Animations?


You can use our custom plugin `mcanim.py` which will export your animations as a `.mcanim` file.

#### How does it look so... smooth?

We utilise the Display Entities' built in interpolation duration to allow for rigs to run at the game's framerate.

#### Am I allowed to use this on my projects?
Of course! This would not be a public resource if we didn't want you to use it. We believe that animation systems should **not** be gate-kept by big projects which have the money to pay developers, however we ask that you please follow our license and provide attribution to this project where ever you can and please leave a star :)

#### Does it have an API?

Not currently, as this is the rewritten version of our Animation System. The API is very early, however there will be events to check when a player clicks a rig when this is out of snapshot releases.
## Authors

- [@polargh](https://www.github.com/polargh)
- [@56738](https://www.github.com/56738)
- [@Reasonlesss](https://www.github.com/Reasonlesss)


## Contributing

Contributions are always welcome!

See `contributing.md` for ways to get started.

