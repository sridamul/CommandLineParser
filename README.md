# Command Line Argument Parser

Apache Commons CLI is used to parse the command line arguments.
For eg. 

```
java -jar jar_file --plugins script-security,git --recipes ModernizeJenkinsfile --dry-run
```
would return the plugins, recipes and dry-run (boolean) in the respective getter function
