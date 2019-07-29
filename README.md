# Book Rental System
Book rental system created with Spring and Angular

## How to run it?
This application uses PostgreSQL so configure your database first and set username/password in the application.properties file stored in resources.  
Launch SystemApplication class from book/system directory. Remember to have Lombok plugin installed.   
To start angular app, you need to type 'ng serve' in angular console. Make sure you are in the \BookRentalSystem\front folder!  
The app will start at http://localhost:4200/  
Swagger doc is available on http://localhost:8080/swagger-ui.html  
## Example login data
Admin account
* login: admin password: admin1

User accounts:
* login: user1 password: usertest
* login: user2 password: usertest2

Admin has ability to remove users and create new books.  
user2 account is unable to borrow new books because he has penalty for not returning book back on time

# Example screens:  

Book list page:  
![alt text](https://i.imgur.com/j96d8dn.png)  

User's rented books page:  
![alt text](https://i.imgur.com/ZK1bH90.png)

User's rented books page (has penalty):   
![alt text](https://imgur.com/qaNc4n2.png)
