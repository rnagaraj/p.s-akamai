# p.s-akamai
**Akamai CCU V2 API Implementation**

Assuming AEM server is running @ [http://localhost:4502](http://localhost:4502), run below maven command

```
mvn clean install -P autoInstallBundle
```

verify bundle is deployed to local server & active. 

Go to configuration manager [http://localhost:4502/system/console/configMgr](http://localhost:4502/system/console/configMgr) , find **com.sapient.akamai.service**
and configure Akamai credentials and save the configuration.

In your OSGI project add below dependency

```
    <dependency>
        <groupId>com.sapient</groupId>
        <artifactId>akamai-purge</artifactId>
        <version>1.0.0-SNAPSHOT</version>    
        <scope>provided</scope>
    </dependency>
```

Now you can use the service AkamaiPurgeService using SCR reference annotation as below
in your OSGI component.

```
@Reference
AkamaiPurgeService akamaiPurgeService;
```
