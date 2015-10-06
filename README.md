# OnlinePortfolio
SE761 Project

##Readme for Final

A Live Demo Version is available [here](http://homepages.engineering.auckland.ac.nz/~thus056/)
The demo server used can take time to active completely.

---

The Final consists of a Login Page, Admin Main Page, Add/Edit/Duplicate Qual, Client Main Page, and SuperUser Page.

---

The demo can be logged in using the password "***3764***"

---

On the Admin Main Page, all projects related to the project are displayed which is the default behavior.  Using the Tabs at the top of the screen, you can swap between the 3 gridview displays: ***Quals***, ***Projects*** and ***Clients***.

Clicking on any client in the ***Client GridView*** will show all project associated to that client.
Clicking on any project in the ***Projects GridView*** will show all quals that have been associated with that project. 
Clicking on any qual in the ***Qual GridView*** opens a view of that qual's information.

In the Qual GridView new qual's can be added by clicking the ***Add New Qual button***. This opens up the Add Qual Page, which allows you to enter all relevant qual information and add it to the system.

On the ***Project GridView*** and ***Qual GridView***, projects and quals can be added to a project cart (With Quals in the projects added to the cart, instead of the actual project). This ***Project Cart*** can be accessed by clicking the ***Project Cart button*** at the top right of the screen. Here the added quals can be removed from the cart by clicking on the qual displayed in the cart list. To create the Project, need to click on the ***Create Project button*** which opens a window that allows you to name the Project and assign it to an existing Client or create a new Client.

Additionally clicking on the Pencil Icon allows you to edit that item, opening a modal with that items existing information.

---

To access the Superuser page you must be logged in as a Superuser Admin. If you are, a button will appear in the navbar allowing you to navigate to the Superuser View page.

Clicking on any of the Tabs at the top allows you to switch which table is being displayed, displaying either ***Admin Groups***, ***Admin***, ***Quals***, ***Project Groups***, ***Clients***. Each table shows all the items in their respective database table, and indicates if they are currently active or not. A superuser is also able to reactivate or deactivate any item in any of the tables via these tables.

Also on this page, there are two buttons, ***Add Admin*** and ***Add Admin Group*** which allow you to enter all relevant information and add an Admin Group or Admin to the system.

Qual's, Admin Groups and Admin accounts can also be edited from this page.

---
