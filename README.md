# Wicket Christmas
Welcome to our little christmas present. The application in this repo was done for the Senacor DevCon Conference this year. It showcases different approaches to asynchronously loaded contents.<br/>
__THIS CODE IS INTENDED TO SHOWCASE DIFFERENT APPROACHES AND IS NOT INTENDED FOR PRODUCTION USE__

## What it does
There is a common problem in a lot of web applications: At some point you will have to wait for a long running process to finish (e.g., a slow database query or another system integrated via webservices).
If your whole application gets stuck on a simple counter being loaded from the database you will be starting to look for better options. 
We tried out several ways of dealing with this problem and put together this little application to showcase the different approaches.<br/>
Under the hood we use _Spring-@Async_ to deal with long running processes. To interact with them, we created _com.senacor.wicket.async.christmas.widgets.api.model.AbstractAsyncModel_. We also added some delay-functionality to REALLY make sure things would take some time ;)<br/>
But the main focus lies on how to deal with these models which will be explained further down.

## Starting and running the application
After checking out, import it into the IDE of your liking. As it is based on the Wicket Maven Archetype, you will find __com.senacor.wicket.async.christmas.Start__ in the test-folder. You could run Start directly or use _mvn jetty:run_ to fire it up. Teh application is running in development mode by default, so there won't be any outgoing traffic. If you are feeling adventurous you could switch to _production_-mode by providing __-Dspring.profiles.active="prod"__ as a jre parameter.
Now go to [http://127.0.0.1:8080](http://127.0.0.1:8080).

## The application
Accessing the application, you will find four links leading to the different implementations. On each page you will see a _Request Counter_ displaying the amount of requests sent to the server (take a close look) and three panels.<br/>
The top most panel displays train delays in Germany taken from [http://zugmonitor.sueddeutsche.de](http://zugmonitor.sueddeutsche.de).<br/>
Number two uses Spring Social to display the results of a query to the public Twitter time line.<br/>
Third and last you get Dilbert comics from their RSS-feed.<br/>
Each one takes considerable time to start up, which we are trying to hide behind a spinning component.

### LazyLoading-Components
This version is built on _AjaxLazyLoadPanel_, which is part of the Wicket core distribution.<br/> We were trying to use some wicket _AjaxLazyLoadPanel_ and expected to have parallel requests to the application. 
<u>Advantages</u>
* easy to implement
* a single request per lazily loaded component
<u>Disadvantages</u>
* blocks on the first call
* components have to wait in call order
* depending on the order of requests, fast loading components will have to wait for slower ones to finish
* things can get very complicated in complex component hierarchies
* Conclusion: It's not possible to process real parallel requests from a page. So, in the next examples we decided to implement the async loading part on the model level instead of the component level. That way, the long time loading can be parallel as well as asynchronous to the alien systems (twitter, dilbert and zugmonitor), and we make short and efficient update-requests from our page to the application.

### Bad Heart
This version uses an _AjaxSelfUpdatingTimerBehavior_ to periodically check if the content is available. When the content is loaded, it replaces the spinner.
<u>Advantages</u>
* slightly more complex than AjaxLazyLoadPanel
* won't block on the first call, allowing components to be updated as they come
<u>Disadvantages</u>
* a lot more requests are sent to the server
About the name: you will understand the name after reading the next paragraph.

### Heart Beat
Instead of registering multiple AjaxSelfUpdatingTimerBehaviors we decided to register a single one and use it as a Heart Beat. Each time this _Behavior_ sends an update, we send a HeartBeatEvent into the component hierarchy. Each component listening to these events has to tell the application whether it needs further beats. If this does not happen, the Heart will stop beating.
<u>Advantages</u>
* requests are reduced to the bare minimum
* even complex hierarchies are easily handled as heart beat consumers can be placed anywhere
<u>Disadvantages</u>
* slightly more complex to implement

### Websockets
Using the wicket native websocket implementation.
<u>Advantages</u>
* Server push -  this is what we really want for our application
* cool stuff - everybody wants websockets :-)
<u>Disadvantages</u>
* the websocket implementation is experimental - during the implementation, we had a lot of strange exceptions and erros 
* the wicket native websocket implementation is not very well documented on the web. We found only two useful links:
[http://splitshade.wordpress.com/2012/11/04/wicket-6-2-websockets-und-jquery-visualize-die-richtige-atmosphare-schaffen/](http://splitshade.wordpress.com/2012/11/04/wicket-6-2-websockets-und-jquery-visualize-die-richtige-atmosphare-schaffen/) and [http://wicketinaction.com/2012/07/wicket-6-native-websockets/](http://wicketinaction.com/2012/07/wicket-6-native-websockets/) 
* The example from the web uses an _AjaxLink_ and sends a websocket message. But this is not the case we want, because there is no advantage compared to a normal ajax request. We need an active server push example.
* Our implementation uses custom threads on the server side, but it is impossible to update an wicket component from our thread, because there is no _RequestCycle_. Without a _RequestCycle_ wicket cannnot render components, so we are sending a simple update-text-message from the thread. If the Page gets this update message, an update request is created, and during the update request the components are rendered and replaced. 

Enjoy,
Olaf and Jochen

[Olaf Siefart](olaf.siefart@senacor.com)<br/>
[Jochen Mader](jochen.mader@senacor.com)<br/>
<br/>
Contributions:
Pictures included in the application were taken from Flickr and are licensed under Attribution 2.0 Generic:<br/>
User apparena [https://secure.flickr.com/photos/gigold/4241835169/](https://secure.flickr.com/photos/gigold/4241835169/)<br/>
User Thomas Gigold[https://secure.flickr.com/photos/gigold/4241835169/](https://secure.flickr.com/photos/gigold/4241835169/)