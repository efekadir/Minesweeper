# 💣 Minesweeper

A classic **Minesweeper game built with Java & JavaFX**, developed from scratch as a programming and game-development project.

> 🎯 Focus: **Java • JavaFX • OOP • Game Logic • Recursion**

---

## 🎮 Features

* 💣 Classic Minesweeper gameplay
* 🚩 Right-click flag system
* 🔢 Dynamic mine counter
* 🎚️ Multiple difficulty settings
* 🔄 Restart & Main Menu
* 🏆 Win & Game Over screens
* 🔁 Recursive cell opening
* 🎨 Custom JavaFX UI & graphics
* ⌨️ Development/debug controls

---

## 🛠️ Built With

| Technology          | Purpose                        |
| ------------------- | ------------------------------ |
| ☕ **Java**          | Core programming & game logic  |
| 🎨 **JavaFX**       | Graphical user interface       |
| 🧩 **OOP**          | Project structure & components |
| 🔁 **Recursion**    | Automatic cell opening         |
| 🐙 **Git / GitHub** | Version control                |

---

## 🎯 Controls

**Left Click** → Open cell
**Right Click** → Place / remove flag
**Restart** → Start a new game
**Main Menu** → Return to main menu

---

## 🧩 Game Logic

Each cell stores its own state:

* 💣 Mine status
* 🚩 Flag status
* 👆 Clicked status
* 🔢 Number of neighboring mines

When an empty cell is opened, the game automatically checks its neighbors and recursively opens connected empty cells.

Winning occurs when **all safe cells have been opened**.

---

## ⚙️ Customization

Game settings are managed through `GameSettings`, allowing the board to be configured without changing the core game logic.

```text
Rows
Columns
Mine Count
Cell Size
Mine Size
Flag Size
```

---

## 📚 What This Project Demonstrates

* Object-Oriented Programming
* 2D Arrays
* Recursion
* Event Handling
* JavaFX Scene Management
* UI Component Design
* Game State Management
* Separation of Logic & UI
* Git / GitHub Workflow

---

## 🚀 Future Plans

* ⏱️ Timer & best scores
* 🔊 Sound effects
* 🎵 Background music
* 🏅 High-score system
* ✨ More animations
* 🎚️ Additional game modes

---

## 👨‍💻 About

Built as a **learning project to improve Java, JavaFX, and software development skills**.

The goal was to go beyond simply making Minesweeper and understand how the different parts of a real application work together.

⭐ **If you like the project, feel free to star the repository!**
