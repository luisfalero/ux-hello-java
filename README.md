# UX HELLO JAVA

- Access project

```shell
oc project <project-name>
```

- Create app UX

```shell
oc new-app --name=ux-hello-java \
    java:11~https://github.com/luisfalero/ux-hello-java.git \
    --as-deployment-config

oc logs -f bc/ux-hello-java
```

- Access logs

```shell
oc logs -f bc/ux-hello-java
```

- Expose Route HTTP

```shell
oc expose svc/ux-hello-java --name=ux-hello-java --port=8080
```

- Expose Route HTTPS

```shell
oc create route edge --service=ux-hello-java --port=8080
```