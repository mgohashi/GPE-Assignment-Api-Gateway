# Introduction

This service has two dependencies:

## Deploy Freelancer Service

[Install Freelancer Service](https://github.com/mgohashi/GPE-Assignment-Freelancer-Service#deploy-project-service)

## Deploy Project Service

[Install Project Service](https://github.com/mgohashi/GPE-Assignment-Project-Service#deploy-project-service)

# Deploy Project Service on Openshift

```shell
$ mvn clean fabric8:deploy -Popenshift
```

# Tests on openshift

* Get freelancers
  ```shell
  $ ./test-get-freelancer.sh
  ```
* Get freelancer
  ```shell
  $ ./test-get-freelancer.sh 1
  ```
* Get projects
  ```shell
  $ ./test-get-projects.sh
  ```
* Get project
  ```shell
  $ ./test-get-projects.sh 1
  ```

# Execute Unit Tests

```shell
$ mvn clean test
```