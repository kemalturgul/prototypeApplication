# prototypeApplication
This is a project about REST, SOAP Services, Cache and also integrated with Apache Karaf


#USAGE
1. Clone project into your workspace.
2. Go to the project directory and run <pre>mvn clean install</pre>
3. When build is success, you should see a prototypeApplication.war under target directory of the project
4. You can deploy prototypeApplication.war into Wildfly Application Server (AS) or another AS
5. On browser enter below URL for SOAP Web Service WSDL
	<pre>http://localhost:8080/prototypeApplication/SubscriberServiceImpl?wsdl</pre>
6. Save this SOAP WSDL or use link on SOAP-UI application to create requests
7. There are two WS requests getAllSubscribers and getSubscriberById
   getAllSubscribers request does not have any input parameter, just click run to get all subscribers on cache
   getSubscriberById request include subscriber ID to make service request and returns subscriber if exist on cache otherwise returns Subscriber Not Found
   
8. There are three Rest Services with below URL:
	<pre>http://localhost:8080/prototypeApplication/rest/subscriber</pre>
	[POST] method require a subscriber json data in its body. Attributes (id,name,msisdn) as below sample.
	This method creates a new subscriber in cache and returns 200 OK if successful creation.
	<pre>
		{ 
		   "id":"2",
		   "name":"Alice Gracy",
		   "msisdn":"905552551133"
		}
	</pre>	
	[PUT] method require a subscriber json data in its body. Attributes are (id,name,msisdn) as below sample
	Updates if cache has data corresponding requested ID and returns 200 OK, returns 'Subscriber Not Found' if it is not found in cache.
	<pre>{"id":"1","name":"Stephan King-2","msisdn":"05554006079"}</pre>
	[DELETE] method require a subscriber json data in its body. Attribute is (id) of the subscriber to remove from cache as below sample
	<pre>{"id":"4"}</pre>
	
9. There is a configuration file (app.conf) and it is located under project's src/main/resources directory.
   This file have two configuration parameters:
   	<pre>data_file_path:Location of the data.json file which application read this file and adds its subscription to cache at startup.File can be also empty at startup</pre>
	<pre>scheduler:This represents a cron expression. At this time all cached subscriptions would be written to data.json file. Existed data would be overwritten.</pre>
	Excample parameter values:
	<pre>data_file_path=D:\\MyFiles\\PrototypeApplication\\data.json</pre>
	<pre>scheduler=0 0/1 * 1/1 * ? *</pre>


	