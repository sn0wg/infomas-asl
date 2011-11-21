# infomas-asl

INFOMAS ASL contains all open sources code from the INFOMAS PCM Application Suite. All code is licensed by the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0), so it can be used by both open source and commercial projects.

The INFOMAS PCM Application Suite is a commercial SaaS based Product Content Management (also known as PIM, Product Information Management) Application. For more information, visit http://www.xiam.nl.

## Quick Facts
+ Language: Java 6 SE
+ Build System: Maven 3
+ Apache License, Version 2.0
+ Maven Artifacts are (currently) not available from central maven

## Modules
Currently INFOMAS ASL contains the following modules:
+ annotation-detector
+ More to come ...

### annotation-detector
This library can be used to scan (part of) the class path for annotated classes, methods or instance variables.
Main advantages of this library compared with similar solutions are: light weight (**no dependencies**, simple API, **15 kb jar file**) and **very fast** (fastest annotation detection library as far as i know).

Maven configuration:
``` xml
<dependency>
   <groupId>eu.infomas</groupId>
   <artifactId>annotation-detector</artifactId>
   <version>3.0.0-SNAPSHOT</version>
</dependency>
```

Example Usage:

``` java
// Scan all .class files on the class path
// Report all .class files, with org.junit.Test annotated methods
final MethodReporter reporter = new MethodReporter() {

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Annotation>[] annotations() {
        return new Class[]{Test.class};
    }

    @Override
    public void reportMethodAnnotation(Class<? extends Annotation> annotation,
        String className, String methodName) {
        // do something
    }
    
};
final AnnotationDetector cf = new AnnotationDetector(reporter);
cf.detect();
```

That's all!

## License

Copyright (c) 2011 XIAM Solutions B.V.

Licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
