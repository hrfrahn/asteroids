# asteroids
ASTEROIDS

by Harrison Frahn and Kai Hodge

This is a simple clone of the classic game Asteroids,, written in Java using the JavaFX graphics library. To run it, simply drag all the files into an eclipse project and hit Run.

Controls: 
W: Move forwards
A: Turn left
D: Turn right
Space: Fire (note: you need to hold it down)
Q: Quit

How to Play:
Destroy asteroids to gain points, but don’t run into them! You have 3 lives, and your score is based off of both how long you stay alive and how many asteroids you destroy.

Who Did What:
Harrison wrote the Main and Ship classes(and this file).
Kai wrote the GraphicsObject, Bullet, and Asteroid classes, and also made the UML diagrams.


Troubleshooting:
On some versions of Eclipse, JavaFX is restricted by default, and you need to allow access to it manually. To do this, follow these instructions:

right click on the project in the package explorer

click 'properties' (it's at the bottom)

select ‘Java Build Path’ from the left sidebar

click Libraries

open the dropdown on JRE System Library, then click Access Rules

click 'Edit' on the right sidebar

click 'Add' in the window that opens

in the text box, type 'javafx/**'

set the resolution to 'Accessible' from the above dropdown

click 'okay' to everything 


If this doesn’t work, make sure that the eclipse project that the game is running in is using the JRE 1.8, not 1.7. To do this:

right click on the project in the package explorer

click 'properties' (it's at the bottom)

select ‘Java Build Path’ from the left sidebar

click Libraries

click on the JRE System Library

click ‘Edit’ on the right sidebar

change the execution environment from ‘JavaSE-1.7’ to ‘JavaSE-1.8’

click ‘okay’ to everything
