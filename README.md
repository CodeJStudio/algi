# Artificial Logical General Intelligence (ALGI) v1.0.0(Prototype)
Prototype "Holiday" - A prototype model to implement that lets a machine learn the algorithm of doing addition of two natural numbers, which follows logicism, rather than connectionism.  
这是一个使用逻辑主义模式，而非联结主义模式，实现让机器学会两个自然数相加算法的原型模型。

![dialog1](https://github.com/CodeJStudio/algi/assets/114736018/938a8b6a-e2e9-4871-bff6-370ed93566be)

---------

### Refer to: 

[Theory of Logical Information Model & Logical Information Network / 《逻辑信息模型与逻辑信息网络》](https://github.com/jhjiang/lim_lin)

[From Logical Information Model, to Logical Information Network, to the realization of Artificial General Intelligence (AGI)](https://www.reddit.com/user/JeffreyJiang/comments/upcloh/from_logical_information_model_to_logical/)

[《从逻辑信息模型，到逻辑信息网络，直至实现通用人工智能》](https://zhuanlan.zhihu.com/p/497443483)

---------

### Preparation: 

1. rabbitmq
>1). install & config "rabbitmq" (Recommanded Version: 3.0.4 or later)  
>2). update configuration: "algi-common-queue-rabbitmq\pom.xml",  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"algi-common-queue-rabbitmq\src\main\resources\properties\queue-config.properties"

2. neo4j  
>1). install & config "neo4j" (Recommanded Version: 3.5.35 or later)  
>2). update configuration: "lin4j-persistence-neo4j\pom.xml",  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"lin4j-persistence-neo4j\src\main\resources\properties\db-config.properties"

3. standford-corenlp
>1). install & config "standford-corenlp" (Recommanded Version: 4.5.1 or later)  
>2). update configuration: "algi-nlp-corenlp\pom.xml",  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"algi-nlp-corenlp\src\main\resources\properties\nlp-config.properties"

---------

### Launch: 

1. Deploy projects "algi-neuros", "algi-sensors", "algi-web" to web server, and launch the server.

2. Browse web application "algi-web", and start dialog. (eg: "http://localhost:8080/algi-web/")

3. In the beginning, you won't get the correct answer you want, until the prototype data is imported.
![dialog2](https://github.com/CodeJStudio/algi/assets/114736018/0b93b250-2602-4543-a0cb-369c3615da2f)  
Import the default prototype data "algi-samples/src/main/resources/samples/HelloImporter.json" in console via below command.
![import1](https://github.com/CodeJStudio/algi/assets/114736018/1950857d-2cf2-447f-95bd-17b52f241390)  
Finish importing.
![import2](https://github.com/CodeJStudio/algi/assets/114736018/84c5b668-afab-4f7c-baa3-5407f9623241)  
Through the imported raw information and algorithm data, the result can be calculated correctly.
![dialog3](https://github.com/CodeJStudio/algi/assets/114736018/3dfabdca-096a-4d15-a35c-d06edb496006)

4. If it's willing to retain the raw and calculated data after restarting the web server, the persistence switch in "algi-neuros/src/main/resources/properties/neuros.properties" should be opened in advance, and arg "-s" should be appended to the import operation. (eg: "import d:\HelloImporter.json -s")

5. See ALGI-Samples for details on how this works.


---------

# ALGI Samples

(To Be Continued)
