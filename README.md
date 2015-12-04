# LessVariableAnalyzer
A maven project to count less variables

# Motivation
This maven project has been created to count less variables. In the current project I needed a way to track unused less variables, so these clsasses were born. A count of 1 means that the variable has been initialized, but not used at another place.

# How to use
To exeute the jar file you need to pass at least one parameter:

* Absolute path to (root) folder with less files
* A prefix for the less files

Why the prefix? Maybe you need to analyze a certain kind of less files. I tend to prefix my less variables (like namespacing). A complete call would be like this:

java -jar ./LessVariableAnalyzer.jar '[prefix]' '[absolute path to your less folder]'

Enjoy!
