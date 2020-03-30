## Design Patterns - Assignment 5 - Joshua Kronemeyer
#### Console Arena Game
I made a console arena game that includes the selection of 3 classes, each loaded out with different items. 3 different bosses,
each with their own names, damage, and health values. Some of the values such as damage and armor have slight random
variations to them on each playthrough.

#### Design Patterns Used
Abstract Factory - I used this design pattern with Player and Item. Both of these required slightly different variations.
Players needed classes and Items needed subtypes Armor, Weapon, Potion. Each of them requiring slightly different setups, but
all falling under the same base class of Item. This makes things like HashMaps easy to build pre-defined lists. (See below)

Object Pool - This was done with items and bosses. I created a class with a HashMap with already setup items/bosses to
choose from. Since there's only one of each in this game, there's no need to generate more than one.

Mediator - The Combat class/system takes in Player and Npc and has functions to have them interact with each other based
on properties within both the classes.