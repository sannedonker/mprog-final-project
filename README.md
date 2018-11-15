# mprog-final-project
Sanne Donker, 10780416
An app that let's you 'play' roller derby.

There aren't a lot of roller derby (a full body contact sport on rollerskates) related apps. 
Since derby often is a way of life I thought it would be fun to create an app that allows you to also play derby on your phone.
That way people can get invested in derby even more!

It's practically impossible to simulate real derby play in an app. It however is possible to simulate a jammer who has to avoid blockers
while being on track. My idea is that the screen is the track and if you get out of bounds (outside of the track) you die in the game.
While being on track you have to avoid the blockers which pop up at the top of your screen and go down at the speed you are skating (it 
will look like you are scrolling on yout screen through a track filled with blockers, but then automatically).

For every blocker you pass you earn a point. Blockers can move laterally (from left to right) over the screen. You can avoid them by
pressing the buttons to move left and right. When you get hit (you touch a blocker) or get out of bounds (are outside of the track) you die.
The amount of points you scored is your score. There will be a screen with the top 5 scores.

Hard parts of the problem are letting the blockers move laterally and making sure that the screen keeps scrolling (optionally faster when
you've earned more points). It also might be difficult to place the blockers on random places on the track.
