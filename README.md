Gatling plugin for Maven - Java demo project
============================================

A simple showcase of a Maven project using the Gatling plugin for Maven. Refer to the plugin documentation
[on the Gatling website](https://gatling.io/docs/current/extensions/maven_plugin/) for usage.

This project is written in Java, others are available for [Kotlin](https://github.com/gatling/gatling-maven-plugin-demo-kotlin)
and [Scala](https://github.com/gatling/gatling-maven-plugin-demo-scala).

It includes:

* [Maven Wrapper](https://maven.apache.org/wrapper/), so that you can immediately run Maven with `./mvnw` without having
  to install it on your computer
* minimal `pom.xml`
* latest version of `io.gatling:gatling-maven-plugin` applied
* sample [Simulation](https://gatling.io/docs/gatling/reference/current/general/concepts/#simulation) class,
  demonstrating sufficient Gatling functionality
* proper source file layout

**Here are the commands to run tests**

1. To use recorder, use command:
```
mvn gatling:recorder
```

2. To run tests, use command:
```
./mvnw gatling:test
```
or
```
mvn gatling:test
```
3. To run all the tests without choosing the simulation class, uncomment the line <code>\<runMultipleSimulations>true\</runMultipleSimulations></code>
   in the <code>pom.xml</code> file
4. Run the shell file <code>clean_old_gatling_reports.sh</code> to delete old Gatling reports
```
 ./clean_old_gatling_reports.sh

```
