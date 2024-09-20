# Customer Chat

A small Backend Chat from Customer Service to Customer (Frontend) over Websockets
It is a quick win solution. 

In this example the Customer Service is simulated by a thread which answers the oldest question in a list of Customer questions and waits for 3500 ms to go on with the next one. 

The customer Code (Frontend) is located in main/resources/index.html

There is no form of authentification for the user. The Backend only knows the session id of the user and with this information it sends the answer to the right user. 

