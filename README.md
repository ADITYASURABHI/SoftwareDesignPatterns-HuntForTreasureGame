                                                   
                          
In this final project Christopher Columbus Adventure, we have created a grid by adding dunk pirate ships,
pirate ship, roaming pirate ship, sea monsters, ship, special pirate, treasure, whirlpool images in the respective classes.
Ocean Explorer is the main class and we are creating islands, whirlpool and treasure images in this class.
The game ends if the Christopher Columbus is caught by any of the pirate ships or monsters. We have implemented 5 design patterns
mentioned below:
1.	PirateFactory: We have implemented factory design pattern to create pirates.
2.	Singleton: We have implemented singleton design pattern in OceanMap Class and we have created an object for this instance in various
class where it is required.
3.	Decorator : We have implemented two decorator design classes that is sailing decorator and static decorator in which movement of the 
ship is designed.
4.	Strategy : We have created a sailstrategy interface and inherited in the roamingPirate, mainpirate and specialpirate classes including 
the decorators.
5.	Observer: We have implemented the observer design pattern to observe the Christopher Columbus ship position and pass it to pirate 
ships which are observables.
                 
In addition to this, we have added the whirlpools functionality in which ,if the Christopher Columbus enters
into one of the whirlpool then it exits in the other whirlpool. Whenever when the Christopher Columbus is caught by the pirate ships
and the monsters, we will have a popup message that we have lost the game. If the Christopher Columbus inters the treasure without 
meeting the other pirate ships then we will get a popup message that we won the game and the game exits. 
