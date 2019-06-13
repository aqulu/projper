# projper

project-idea helper sample app with DDD based multi-module architecture

Module structure:

```
app         // android base project, contains MainActivity
/modules
|-- core    // contains all not android-related core & support functionality (java-library)
|
|-- /features
|   |
|   |-- /auth
|   |   |-- auth (android-library)          // auth ui layer
|   |   |-- auth-usecase (java-library)  // auth usecases
|   |   |-- auth-infra (android-library)    // auth infrastructure layer
|   |   |-- auth-domain (java-library)      // auth domain layer
|   |
|   |-- /project
|   |   |-- project (android-library)       // project ui layer
|   |   |-- project-usecase (java-library)  // project usecases
|   |   |-- project-infra (java-library)    // project infrastructure layer
|   |   |-- project-domain (java-library)   // project domain layer
|
|-- shared  // shared resources and classes (android-library)

```

A reference multi-module architecture can be found here: https://github.com/kgmyshin/annict-android

Compared this, projper tries to avoid cross-module dependencies by injecting with feature-module independent interfaces into the respective Components. Modules are created as java-libraries whereever the use of android-libraries could be avoided.
