# TalkableProductAutomationProject
*To run scenarios execute following command in the project root directory, where ${env.NAME} name of test env (possible values: VOID, PROD):*

```
mnv clean test -Denv.NAME=PROD
```
*or*
```
mnv clean test -Denv.NAME=VOID 
```

*Please note: In case of execution without parameter ${env.NAME} test will be executed on Void env*
Example: mvn clean test

