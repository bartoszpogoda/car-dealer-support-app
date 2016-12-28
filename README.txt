App should help running small car dealer company.

Specification:
- Car - brand, price, production date
- Cars can have a state : Avaliable, Reserved, Sold
- User can add a car, reserve a car (asked for reservation date) and sell the car (asked for sell date)
- User should be able to filter the list of cars (ex. Produced before... Produced after.. Price.. filters)

- Model and GUI should be separated.

So I have designed the app based on MVC architecture. View events fire Controllers methods which are capable of updating the model through the interface and also calls data update in proper Views. 

I've also implemented filters in easy expandable way. It is something based on Strategy Pattern - I belive. 

Another design pattern i used was Factory pattern. There are 2 factories that Controller uses to create objects which are then passed to the model. In my app factories are also designed to be capable of data validation (and throwing exceptions).


--Dependencies
Used jdatepicker-1.3.4 https://jdatepicker.org/ 
