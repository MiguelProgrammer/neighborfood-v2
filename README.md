<h2>Architecture used - Hezagonal - Ports and Adapters</h2><hr>

<h3>Neighborfood-app</h3>

* To upload the application, use the <b>docker-compose up</b> or <b>docker-compose up --build</b> command, use a client for the request, the results are also presented via the console to fill in the response.<hr>

<img src="https://codingcanvas.com/wp-content/uploads/2015/07/Image.png"><hr>

Through the monolith, I will create an application similar to Ifood.

This application is being developed to prove theories, software development with DDD, every flow developed here is intended only to put into technical practices of domais driven design, I will use docker to upload the application to the PostgreSql database.

We will apply the techniques to a monolith pattern to understand in practice the importance of clean architectural design beyond project patterns.

We know that exagonal architecture preaches business agnostic to frameworks and technologies, however, I use the benefit of spring to completely create this project and in the end I will decouple it from the technologies used.

Thinking in a pragmatic way, I see this definition of exagonal architecture as at least indifferent, think about it, it preaches the non-relationship with the technology used but depends on others for its purpose to be served, it's a kind of I want you here, but even give me help. LOL

* Doc DDD miro: <a href="https://miro.com/app/board/uXjVKUqbA08=/?share_link_id=950297951149">miro</a>
* Json doc to postman requests: <a href="https://drive.google.com/file/d/1GfCgxuSvyfNFsuZa6ZJAr42No8sQolPO/view?usp=sharing">request postman</a>
* Api-docs Swagger OpenPI <a href="http://localhost:8090/swagger-ui/index.html">swagger-ui</a> -> Access documentation only by running the application or by inserting the contents of the src/main/resources/api/resource.yml file on the swagger web editor website
* app in Aws Cloud <a href="http://ec2-3-145-4-81.us-east-2.compute.amazonaws.com/swagger-ui/index.html">Swagger UI - OpenAPI definition</a>
* app in Azure Cloud <a href="https://neighborfood-app.azurewebsites.net/swagger-ui/index.html">Swagger UI - OpenAPI definition</a>
<hr>

NEIGHBORFOOD MENU

* 1 - Register as administrator
     * 1.1 - If a user accesses the system and uses areas such as listing the menu or placing an order, the response will be empty and a
       notification will be sent to admin
     * 1.2 - The administrator will be able to list the orders entered regardless of their status, however, only orders with a finalized status will be able to check the waiting time.
* 2 - Register items in stock
     * 2.1 - The administrator can register one item per category per batch
     * 2.2 - When the customer places an order, the item selected in the order is subtracted from stock
* 3 - Register as a Customer
     * 3.1 - You can register using your name, email and CPF, if you do not want to, you will be identified by the order number
     * 3.2 – When registering and already having a login, the system will return your current username
* 4 - Log in as a customer
* 5 – List the options menu
* 6 - Place an order
     * 6.1 - If a product is missing, the system will notify the customer and the administrative area for replacement
  * 6.2 - The customer can make changes to items, updating their order
* 7 - Make payment
* 8 - Collect your order
* 9 - Receive treats
     * 9.1 - You will receive discount alerts on upcoming orders

<hr>
Happy flow:

* 1 - Register as an administrator
* 2 - Register products
* 3 - Register as a customer
* 4 - Place an order
* 5 - Update your order if you wish
* 6 - Make payment
* 7 - Check the order status
  * 7.1 - When you check the order status after payment, the system will move to the next statuses until the order is finalized
* 8 - Send gifts to the customer
* 9 - List the orders containing the average waiting time
* 10 - You can list the orders at any time, regardless of their status.
<hr>

<img src="https://i.imgur.com/i4nWA9q.png">
