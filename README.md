![Aria logo](repo_assets/banner.webp)

Nature’s silent symphony.
---

**Aria** is an ambient sound app.

Built from scratch using [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform), **Aria** is designed to flow across platforms. From Linux to Windows, from web to Android, while honoring the quiet beauty of the original [Blanket app](https://github.com/rafaelmardojai/blanket), built by Rafael Mardojai for the GNOME desktop.

I wanted to bring peace, calm, and ambient beauty to more people, on more platforms. Blanket is currently only available for Linux, and I wanted to bring it to the rest of the world.

So this is my handcrafted tribute to it, to all the wonderful people who made possible Blanket exist, and thus, made Aria possible too.

---

## 🌼 Features

**Everything that Blanket already bring to us:** 
- A curated library of ambient nature sounds: rain, fire, birds, forest, and more
- Per-sound volume sliders + master volume control

**But Aria brings this too:**
- Built-in timer to gently fade out after a chosen duration (perfect for sleep)
- Runs on Desktop (Linux, Windows, macOS, all via JVM), Android and Web (WASM/JS)

### Current state
- [x] **Web version (WASM/JS)**: Completed! Tho, needs accent color persistence.
- [ ] **Desktop version (JVM)**: Works, but user-added audio files are yet to be supported. I'm thinking about dropping JVM target due severe limitations I encountered with JVM audio libs. javax.sample is just not good enough, KorGe and TinySound use javax.sample too, so it's the same thing.
- [ ] **Android version (WIP)**: Works, so far so good. User-added audio files are yet to be supported.

### Screenshots of the nightly version.
Web version
![Nightly screenshot, dark mode](repo_assets/screenshot_web.webp)


<details>
<summary>Desktop and Android previews here:</summary>
<br/>

Desktop & Android version

![Nightly screenshot, dark mode](repo_assets/screenshot_jvm_android.webp)

<br/>

</details>


---

## 🌻 Setup

- Clone and build the project locally.

```bash
git clone https://github.com/kosail/aria.git
cd aria

# For WEB builds
./gradlew wasmJsBrowserDistribution

# For Desktop builds
./gradlew packageReleaseUberJarForCurrentOs
```

---
## Current issues
#### 1. The Desktop version is memory-heavy
Finding audio libraries for Java or Kotlin that were modern, performant, and compatible with many formats was just... ugh. It was a nightmare. At the end, I couldn't make javax.sample work at all, and I gave up. I end up giving another try to [KorGe](https://github.com/korlibs/korge), even though I knew that it used javax.sample as its backend on JVM. Surprisingly, it worked. Thus, I assumed this whole issue was a skill issue, not a tooling one.
Still, there were two big issues using KorGe:
- OGG audio files are not supported. Due to limitations inherited from javax.sample, only WAV and MP3 are supported. 
- The audio files took literally 1–2 seconds to load in the first play per session. This may not sound like a real issue... but I like blazing fast apps. I couldn't help but notice it.

I solved the first issue by just using WAV files instead of OGG. Though, this made the app around 100MB larger in disk space.
The second one, however, was a bit more tricky. I found that using `the streaming = true` flag helped a bit, but just a bit. I tried preloading the files at startup into memory, but loading them into resourcesVfs (KorGe's global memory) was not enough, yet actually started playing them. I noticed that after the first play per session, consecutive ones were much faster. Like, almost instantly. So my workaround was to set all files to streaming, and to start them all for an instant. It had no extra startup penalty to the app, as everything is done in asynchronously, but memory usage... hmm, well... 650MB. I hate RAM-heavy apps, but this time I literally did my best trying to find a better solution, but I just couldn't. Audio libs in Java are a thing. 


---

## 🔧 Stack & Resources
### Stack
- **Compose Multiplatform (Android, Desktop JVM and Web WASM/JS)** — UI framework
- **Audio library** — The audio library for Desktop Target (JVM) is [KorGe](https://github.com/korlibs/korge), while on web target uses the browser API via kotlinx-browser. On Android, it currently uses Exoplayer.
- **GitHub Pages** — For deployment of the web target
- **Play Store** - Expected to be released soon.

### Resources

<details>
<summary>Show/Hide</summary>

#### -> Icons
- [Original icons from Blanket, on GitHub](https://github.com/rafaelmardojai/blanket)
- [volume icon from Lucide](https://lucide.dev)


#### -> Fonts
- Font used in the banner: [Alegreya Sans SC. Designed by Juan Pablo del Peral, Huerta Tipográfica. Available on Google Fonts](https://fonts.google.com/specimen/Alegreya+Sans+SC)
- Main font: [Alegreya Sans. Designed by Juan Pablo del Peral, Huerta Tipográfica. Available on Google Fonts](https://fonts.google.com/specimen/Alegreya+Sans)


#### -> Audios
- All the audios are the original ones used in Blanket. To see more information about the authors and licensing, please check [SOUNDS_LICENSING](LICENSES/SOUNDS_LICENSING.md).

</details>

---

## 💐 Contributing
Contributions are welcome!
Feel free to fork the repository and submit pull requests.
If you have ideas, suggestions, or bug reports, open an issue on GitHub.

Sound designers: If you have original nature loops and would love to contribute them to Aria, reach out!

---


## 🎒 What I have learned so far from this project
1. I'm still a beginner in Compose Multiplatform, not to say in Kotlin. It's such a difficult language...
2. LocalCompositions are a very powerful feature. I'm still learning how to use them properly, and I take them similarly as Zustand stores in the React world. Anyway, I love how easy the code becomes when you use them.
3. Hell, Kotlin-JS wrappers are god-sent but also diabolic to use.
4. Kotlin async programming is hellish difficult. Like, I felt JS async was difficulty level = ok. But Kotlin? Kotlin's difficulty level = Ninja Gaiden 3.

---

## 📜 License
![GPLv3 License logo. Copyright © 2012 Christian Cadena](repo_assets/license-logos-by-christian-candena-GNU_GPLv3_License.webp)

[GPLv3 (GNU General Public License v3)](COPYING.txt) – Free to use, modify, and distribute as long as this remains open source.

GPLv3 Logos:

    Copyright © 2012 Christian Cadena
    Available under the Creative Commons Attribution 3.0 Unported License.

---

Aria Copyright © 2025-2026, kosail
<br>
With love, from Honduras.
