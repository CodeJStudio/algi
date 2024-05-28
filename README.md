# Artificial Logical General Intelligence (ALGI) v1.0.0 (Prototype "Holiday")
Prototype "Holiday" - The real road to AGI  
The first AI model prototype to implement that lets a machine learn the algorithm of doing addition of two natural numbers, which follows logicism, rather than connectionism.  
真正通向通用人工智能之路——这是首个基于**逻辑主义模式**，而非**联结主义模式**，实现让机器学会两个自然数相加算法的AI模型原型。


Theoretically, it has better reasoning skills, and the whole thinking process can be traced.  
理论上**推理能力更强**，**思考过程全程可追溯**。

![dialog1](https://github.com/CodeJStudio/algi/assets/114736018/938a8b6a-e2e9-4871-bff6-370ed93566be)

----------

### Refer to: 

[Theory of Logical Information Model & Logical Information Network / 《逻辑信息模型与逻辑信息网络》](https://github.com/jhjiang/lim_lin)

[From Logical Information Model, to Logical Information Network, to the realization of Artificial General Intelligence (AGI)](https://www.reddit.com/user/JeffreyJiang/comments/upcloh/from_logical_information_model_to_logical/)

[《从逻辑信息模型，到逻辑信息网络，直至实现通用人工智能》](https://zhuanlan.zhihu.com/p/497443483)

----------

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

----------

### Launch: 

1. Deploy projects "algi-neuros", "algi-sensors", "algi-web" to web server, and launch the server.  
部署web应用：“algi-neuros”，“algi-sensors”，“algi-web”，并启动web服务器。

2. Browse web application "algi-web", and start dialog. (eg: "[http://localhost:8080/algi-web/](http://localhost:8080/algi-web/)")  
在浏览器中访问“algi-web”服务，例如 “[http://localhost:8080/algi-web/](http://localhost:8080/algi-web/)”。

3. In the beginning, you won't get the correct answer you want, until the prototype data is imported.  
在最初，你不会得到想要的正确的答案，直到原型数据被导入。  
![dialog2](https://github.com/CodeJStudio/algi/assets/114736018/0b93b250-2602-4543-a0cb-369c3615da2f)  
Import the default prototype data "algi-samples/src/main/resources/samples/HelloImporter.json" in console via below command.  
用以下命令在控制台中导入默认的原型数据“algi-samples/src/main/resources/samples/HelloImporter.json”。  
&emsp;&emsp;(*Refer to "[HelloImporter](#HelloImporter)")  
![import1](https://github.com/CodeJStudio/algi/assets/114736018/1950857d-2cf2-447f-95bd-17b52f241390)  
Finish importing.  
导入完成。  
![import2](https://github.com/CodeJStudio/algi/assets/114736018/84c5b668-afab-4f7c-baa3-5407f9623241)  
Through the imported raw information and algorithm data, the result can be calculated correctly.  
通过导入的原始信息和算法数据，可正确计算得到结果。  
![dialog3](https://github.com/CodeJStudio/algi/assets/114736018/3dfabdca-096a-4d15-a35c-d06edb496006)

4. If it's willing to retain the raw and calculated data after restarting the web server, the persistence switch in "algi-neuros/src/main/resources/properties/neuros.properties" should be opened in advance, and arg "-s" should be appended to the import operation. (eg: "import d:\HelloImporter.json -s")  
如果希望在重启web服务器后，依然可以保留原始数据和计算数据，则需要提前打开“algi-neuros/src/main/resources/properties/neuros.properties”配置文件中的持久化开关；并在执行导入操作时，加上参数“-s”，例如“import d:\HelloImporter.json -s”。


#### See ALGI-Samples for details on how this works.  
具体的工作原理，请参见ALGI-Samples。

----------

# ALGI Samples

### HelloHoliday

"HelloHoliday"s show how to implement a universal algorithm of natural number's addition with logicistic approach, by using a set of addition instances with different properties and algorithms. 
Among them, the changes of the algorithms at different stages also reflect several different ways that human intelligence commonly uses in thinking processes.  
“HelloHoliday”s用一组性质不同、算法不同的加法计算实例，展示了如何用逻辑主义方法实现通用的自然数加法算法的过程。
这其中，在不同阶段的算法的变化，同样也反映出了人类智能在思考过程中常用的几种不同方式。

![table1](https://github.com/CodeJStudio/algi/assets/114736018/b2fe2841-62a3-4dec-ad10-925172e8d3e5)


1. Retrieval (检索)

Retrieval, that is, retrieve directly in memory (database / dataset) to obtain the corresponding result.  
检索，即直接在记忆（数据库/数据集）中搜索，获取相应结果。

"HelloHoliday1_X" is a set of "1+1" operation instances. 
"1+1" is one of the simplest and most fundamental addition operations to natural numbers. 
In general, "1+1=2" is also regarded as an axiom or theorem of the axiom system of natural numbers, which serves as a basis for other natural number operations. 
In this way, we can assume that the statement "1+1=2" and the result of "1+1", concept "2", already exist in memory. 
When thinking, we just need to get it directly from memory (which may be short-term memory or long-term memory), without having to calculate (or deduce) this result through other algorithms.  
“HelloHoliday1_X”是一组“1+1”的运算实例。
“1+1”是最简单、也是最基础的自然数加法运算之一。
通常，“1+1=2”也会被看做是自然数公理系统的公理或定理，作为其它自然数运算的根基。
如此，我们便可以认为，“1+1=2”这一陈述，以及“1+1”的结果，概念“2”，都已存在于记忆之中。
在思考时，我们只需直接从记忆（可能是短时记忆，也可能是长时记忆）中去获取即可，而无需通过其它算法来计算（或者推演）得到这一结果。


2. Trace (追踪)

Trace, that is, trace the clues, and lead to the final result step by step.  
追踪，也就是追踪线索，依据线索来一步步地导向最终的结果。

"HelloHoliday4_X", "HelloHoliday5_X", "HelloHoliday6_X" and "HelloHoliday7_X" are groups of operation instances of "9+1", "10+1", "16+7" and "XXXXX+XXXX" respectively. 
Assuming that the results of these calculations are not directly retrieved, we can also rely on pre-learned computational methods (configured algorithms) to calculate the results. 
These groups of operations respectively represent one-digit plus one-digit, and the result carries to a two-digit; two-digit plus one-digit, the result does not carry; Two-digit plus one-digit, resulting in a carry. 
Operations of different levels have algorithms of different pertinency and complexity. 
Layer by layer, and the last group is a universal algorithm of natural number's addition.  
“HelloHoliday4_X”、“HelloHoliday5_X”、“HelloHoliday6_X”、“HelloHoliday7_X”分别是“9+1”、“10+1”、“16+7”、“XXXXX+XXXX”的几组运算实例。
假设没有直接检索到这些计算的结果，我们还可以依靠预先学习的计算方法（配置的算法），来算出结果。
这几组运算分别代表了，一位数加一位数，结果进位成为两位数；两位数加一位数，结果不进位；两位数加一位数，结果进位。
不同级别的运算有不同针对性和复杂度的算法，层层递进，最后一组是通用的自然数加法算法。


3. Synthesis (合成)

Synthesis， that is, merge various clues with a certain degree of relevance together to form new information that meets the requirements of the result.  
合成，就是将各种不同的、有一定相关度的线索合成到一起，形成新的、符合结果要求的信息。

"HelloHoliday2_X" and "HelloHoliday3_X" are operation instances of "3+2" and "5+4". 
It is also assumed that the results are not directly retrieved, and that there is no corresponding algorithm to deduce the results. 
Then, it is necessary to try to synthesize new valid results from existing information. 
There are many paths from existing information to synthesis, and there are many ways to synthesize. 
A single optimal path is demonstrated in "HelloHoliday2_1" and "HelloHoliday2_2", while an automatic synthesis operation is demonstrated in "HelloHoliday2_3" and "HelloHoliday3_3". 
The latter relies on "SimpleThinkAction" for automatic synthesis. This is an extremely simplified simulation of the thinking process. And because there is no optimization at all, the execution is inefficient (Same goes for "SimpleSentenceThinkAction"). 
"algi-samples/src/main/resources/samples/HelloHoliday2_3.log" is a log file of synthesizing the results of "3+2", for reference. 
Because of the random algorithm involved, each synthesis operation process is different, time-consuming, and even fails due to stack overflow. 
"5+4" in "HelloHoliday3_3" is more difficult to calculate, takes longer, and has a lower success rate.  
“HelloHoliday2_X”和“HelloHoliday3_X”是“3+2”和“5+4”的运算实例。
同样也假设没有直接检索到计算结果，另外也没有可供推演出结果的相应算法。
此时，就需要用已有信息来尝试合成出新的、有效的结果。
从已有信息到合成出结果，可以有很多条路径，合成的方式也可以有很多种。
在“HelloHoliday2_1”和“HelloHoliday2_2”中演示了单条最优路径，而在“HelloHoliday2_3”和“HelloHoliday3_3”中则演示了一种自动合成的操作。
后者依靠“SimpleThinkAction”完成自动合成。这是一种极简化的思考过程模拟，由于完全没有进行任何优化，因此执行效率很低（“SimpleSentenceThinkAction”也是如此）。
“algi-samples/src/main/resources/samples/HelloHoliday2_3.log”是一份通过合成来求得“3+2”结果的日志，供参考。
由于其中涉及随机算法，因此每一次的合成操作过程都不尽相同，耗时也较长，甚至会因堆栈溢出而失败。
“HelloHoliday3_3”中“5+4”的运算难度更高，耗时更长，成功率更低。

Algorithms of automatic synthesis have great room for improvement in the future, and some of the maturer connectionist algorithms currently used in AIGC may also play a role in this.  
自动合成的算法在将来有极大的提升空间，目前AIGC中所采用的一些较为成熟的联结主义的算法也可能会在其中发挥一定作用。

----------

### HelloImporter

"HelloImporter" is used to demonstrate generating the data for import by coding, and the demonstration data content comes from "HelloHoliday7_X". 
The data format is json, and "algi-samples/src/main/resources/samples/HelloImporter.json" is the pre-generated import data, which can be directly used for the demonstration of Prototype "Holiday".  
“HelloImporter”用来演示通过代码生成可供导入的数据，演示数据内容出自“HelloHoliday7_X”。
生成数据的格式为json，“algi-samples/src/main/resources/samples/HelloImporter.json”为预先生成的导入数据，可直接用于“Holiday”原型模型的演示。


