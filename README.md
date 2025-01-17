# Frankenstein Adapation

## What will the application do?
This application will be a game. It will be an adaptation to Mary Shelley's *Frankenstein*.
I plan for the application to present different stories and gameplays for different characters of *Frankenstein*.
## Who will use it?
The ones who will play the game are those who are interested in games and/or literature such as *Frankenstein*.
## Why is this project of interest to you?
This project will be my first attempt at creating a game through code, which has been an interest of mine.
Furthermore, I plan to further develop the game to submit as my English final project which asks for a
creative adaptation of *Frankenstein*

## User Stories
    - I want the user to be able to kill bats which puts a batwing in their inventory
    - I want the user to be able to open their inventory which displays the batwings.
    - I want the user to to be able to drop, use, and read a short description of the batwings
    - I want the user to be able to freely move around
    - I want the user to be able to engage in set dialogue with created NPCs
    - I want there to be a different storyline depending on certain important choices in dialogue
    - I want the user to be able to select the quit option from the application menu, and be reminded to save their game while having the option to do so or not.
    - I want the user to have the choice between loading a previously saved file, or to start a new file.

# Instructions for End User
- You can add an X (batwing) to a Y (inventory) by killing bats to harvest their batwings
- You can interact with batwings by pressing I.
    - Right click the batwing to try and drop it.
    - Left click the batwing to try and use it.
    - Hover over the batwing to see a description of it. 
- You can locate my visual component everywhere.
- You can save the state of my application by pressing escape and clicking Save Game.
- You can end the game, after or without saving, by pressing escape and clicking End Game
- You can reload the state of my application by pressing Load Game at the start.
- You can start a fresh version of the game by pressing New Game at the start.

# Phase 4: Task 2
Sat Nov 23 16:24:55 PST 2024
A Bat has been harvested. Batwing added to inventory.

Sat Nov 23 16:25:02 PST 2024
A Batwing has been used

Sat Nov 23 16:25:03 PST 2024
A Batwing has been dropped

# Phase 4: Task 3
1) All of the GUI's could implement a parent interface, similar to how a majority of the classes in the model package extend the abstract class "GameObject". 
2) Perhaps, classes that originally extended JLabel could extend a parent class that extends JLabel, allowing for all original JLabel classes to inherit common methods. Same thing for classes that originally extended JPanel.
3) These would be hard to make useful and well implemented due to the differences between each GUI class.
