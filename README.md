![Aria logo](repo_images/banner.webp)

Nature’s silent symphony.
---

**Aria** is not just an ambient sound app — it's a tribute, a space to breathe, a gentle moment wrapped in sound and minimal design.

Built from scratch using [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform), **Aria** is designed to flow across platforms — from Linux to Windows, from macOS to Web — while honoring the quiet beauty of the original [Blanket app](https://github.com/rafaelmardojai/blanket), built by Rafael Mardojai for the GNOME desktop.

---

## 🌺 Goal of this project:

**Aria’s mission is simple:**  
To bring peace, calm, and ambient beauty — to more people, on more platforms.

This isn’t a fork or a reimplementation. It’s a handcrafted tribute.

A quiet attempt to capture the essence of **Blanket**, but retold with different tools, for different platforms, and with a different heartbeat — mine.

Designing for minimalism doesn’t mean removing life. It means focusing on **what truly matters**, and letting nature speak, softly and clearly.

![Nightly screenshot](repo_images/screenshot.webp)
---

## 🌼 Features

- 🎵 A curated library of ambient nature sounds: rain, fire, birds, forest, and more
- 🎛️ Click on any sound to play it; mix multiple tracks freely — they blend seamlessly
- 🎚 Per-sound volume sliders + master volume control
- 🕰 Built-in timer to gently fade out after a chosen duration (perfect for sleep)
- 🪟 Inspired by GTK aesthetics — minimal, calm, elegant
- 💻 Runs on Desktop (Linux, Windows, macOS, all via JVM) and Web (WASM/JS)

---

## 🌻 Setup

- Clone and build the project locally.

```bash
git clone https://github.com/kosail/aria.git
cd aria

# For WEB
./gradlew wasmJsBrowserDistribution

# For Desktop
./gradlew packageReleaseUberJarForCurrentOs
```

---

## 🔧 Stack & Resources
### Stack
- **Compose Multiplatform (Desktop + WASM/JS)** — UI framework
- **libgdx Audio Module** — Audio library for Desktop Target (JVM) (Expected to be used) 
- **GitHub Pages** — For deployment

### Resources

<details>
<summary>Show/Hide</summary>

#### -> Icons
- [Original icons from Blanket, on GitHub](https://github.com/rafaelmardojai/blanket)
- [Lucide cat icon](https://lucide.dev)


#### -> Fonts
- Font used in the banner: [Alegreya Sans SC. Designed by Juan Pablo del Peral, Huerta Tipográfica. Available on Google Fonts](https://fonts.google.com/specimen/Alegreya+Sans+SC)
- Main font: [Alegreya Sans. Designed by Juan Pablo del Peral, Huerta Tipográfica. Available on Google Fonts](https://fonts.google.com/specimen/Alegreya+Sans)


#### -> Audios
- [Cat meow audio by DRAGON-STUDIO, on Pixabay](https://pixabay.com/es/sound-effects/cat-meow-321642/)
- All the audios (except for the above) are the original ones used in Blanket. To see more information about the authors and licensing, please check [SOUNDS_LICENSING](SOUNDS_LICENSING.md).

</details>

---

## 💐 Contributing
Contributions are welcome!
Feel free to fork the repository and submit pull requests.
If you have ideas, suggestions, or bug reports, open an issue on GitHub.

Sound designers: If you have original nature loops and would love to contribute them to Aria, reach out!

[//]: # (---)

[//]: # ()
[//]: # (## 🎒 What I learned from this project)

---

## 📜 License
![GPLv3 License logo. Copyright © 2012 Christian Cadena](repo_images/license-logos-by-christian-candena-GNU_GPLv3_License.webp)

[GPLv3 (GNU General Public License v3)](COPYING.txt) – Free to use, modify, and distribute as long as this remains open source, and it is not use for profitable purposes.

GPLv3 Logos:

    Copyright © 2012 Christian Cadena
    Available under the Creative Commons Attribution 3.0 Unported License.

---

Aria Copyright © 2025, kosail
<br>
With love, from Honduras.
