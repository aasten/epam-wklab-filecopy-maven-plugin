# Task

Build Apache Maven plugin that allow to copy file between directories.

Required configuration:
* inputFile
* outputFile

Deploy developed Maven Plugin to local repo.

Create pom.xml with build section containing execution of developed plugin only.

Plugin should be configured to copy ./input/my.properties file to ./output/my_new.properties

Please follow instructions and conventions provided in Guide java plugin development

# Solution

First, run 
```
mvn install
```
for this cloned repository. 

Next, to check the plugin, one may use repository https://github.com/aasten/epam-wklab-test-filecopy-plugin to clone it and run there
```
mvn filecopy:filecopy
```
