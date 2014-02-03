Open Data API For Open Data in Trentino project
====

Java interface for Open Data in Trentino project

**Project status**: developing [milestone 1.0](https://github.com/opendatatrentino/open-data-api/issues?milestone=1&state=open)

**Roadmap**: see [project issues](https://github.com/opendatatrentino/open-data-api/issues)

**Documentation**: see [the wiki](https://github.com/opendatatrentino/open-data-api/wiki)


Credits:

* Juan Pane - DISI at University of Trento - pane@disi.unitn.it
* Moaz Reyad - DISI at University of Trento - moaz.reyad@unitn.it
* David Leoni - Trento Rise - david.leoni@trentorise.eu 
* Alberto Zanella - Trento Rise - alberto.zanella@trentorise.eu


### Usage

#### SNAPSHOTS

WARNING: THIS IS A DEVELOPMENT VERSION THAT CAN CHANGE AT ANY TIME, DO NOT USE IT IN PRODUCTION

To use snapshots version in Maven, add this to your pom.xml:

```
  <repositories>
    <repository>
        <id>sonatype-nexus-snapshots</id>
        <name>project</name>
        <url>https://oss.sonatype.org/content/repositories/snapshots/</url>            
    </repository>        
</repositories>
```

in the _dependencies_ section, add:

```        
    <dependency>
      <groupId>eu.trentorise.opendata</groupId>
      <artifactId>api</artifactId>
      <version>1.0-SNAPSHOT</version>
    </dependency>
```


