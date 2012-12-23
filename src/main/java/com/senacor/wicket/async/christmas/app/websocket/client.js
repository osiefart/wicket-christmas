$(document).ready(function() {
  Wicket.Event.subscribe("/websocket/open", function(jqEvent) {
    // alert("connection opened");
   });
  
  Wicket.Event.subscribe("/websocket/message", function(jqEvent, message) {
	  Wicket.WebSocket.send(message);
	  // alert("message received " + message);
   });
   
  });