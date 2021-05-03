# Swiftly Android Coding Exercise

## Preface

To introduce Swiftly to your coding style and technique, we'd like you to do a small coding exercise. This is not a coding test where we give you a score or run it through a suite of tests for correctness.

Our hope is that we can use this to seed a discussion that provides us with an understanding of your design and coding abilities and you with an idea of what working at Swiftly will be like. We believe this better reflects what engineers do than coding common patterns on a white board does.

## What We're Looking For

Think of this as real code you would be checking in. Swiftly values self-descriptive, [SOLID](https://en.wikipedia.org/wiki/SOLID), maintainable, testable designs and code.

## Overview
Grocery store managers often pick products to put on "manager's special." These products are chosen in order to drive customers to the store and increase basket size. Swiftly has a tool that allows managers to select which product they want to place under "manager's specials" as well as the dimensions of product frame.

## Product Requirements
We need an Android app that allows customers to see which products are on "Manager's Special". Given an endpoint that returns a JSON response of all products currently on "Manager's Special", build a page that displays all products and follows the provided mock-up.

* Manager's Specials change periodically; at any moment a store manager could decide to add or remove a product
* The products must be displayed in the order they are provided
* The size of each product tile must be the size specified by the endpoint (See "Endpoint" for more information about sizing)
* Display as many products as possible while still following sizing parameters
* If the combined width of the product frames exceeds the full width of the phone move the product frame to the next line and center any frames that do not fit in the full width frame.
* The endpoint must be used to get data: https://raw.githubusercontent.com/Swiftly-Systems/code-exercise-android/master/backup

## Mockup
<img src="https://github.com/Swiftly-Systems/code-exercise-android/blob/master/managerSpecial.png" width="400"/>

### Endpoint
* https://raw.githubusercontent.com/Swiftly-Systems/code-exercise-android/master/backup
* The endpoint will provide a 'canvasUnit' which is used to specified the dimensions of each product frame (the height and width of the frame will be using canvasUnit). The canvasUnit will determine how many divisible units fits into the full width of the phone. 
** For example: if the canvasUnit is 8 and the total width fo the phone is 360px then each unit is 360px/8 = 45px. Which means given a product frame a width of 6 canvasUnit and a height of 3 canvasUnit, the product frame would be 270px (width) by 135px (height).
* No product frame will have a width greater than the canvasUnit

### Sample Response
```
{  
   "canvasUnit":16,
   "managerSpecials":[  
      {  
         "imageUrl":"https://raw.githubusercontent.com/Swiftly-Systems/code-exercise-android/master/images/L.png",
         "width":16,
         "height":8,
         "display_name":"Noodle Dish with Roasted Black Bean Sauce",
         "original_price":"2.00",
         "price":"1.00"
      },
      {  
         "imageUrl":"https://raw.githubusercontent.com/Swiftly-Systems/code-exercise-android/master/images/J.png",
         "width":8,
         "height":8,
         "display_name":"Onion Flavored Rings",
         "original_price":"2.00",
         "price":"1.00"
      },
      {  
         "imageUrl":"https://raw.githubusercontent.com/Swiftly-Systems/code-exercise-android/master/images/K.png",
         "width":8,
         "height":8,
         "display_name":"Kikkoman Less Sodium Soy Sauce",
         "original_price":"2.00",
         "price":"1.00"
      }
   ]
}
```


## Standard Requirements
* Complete this project on your own
* Use a GitHub repository for source control
  * Email the link to the GitHub repository when you are finished
  * The readme.md file should include (at a minimum)
    * Instructions for running the project
    * Any other information you would normally include when you author a readme
  * This code is your intellectual property
    * License it however you'd like (MIT, Apache or Public Domain are great choices)
    * Ensure the license you choose allows Swiftly employees to read, build, and execute the code for free
* Email your Swiftly contact the link to the GitHub repository when you're ready to submit your project

If you have any questions, don't hesitate to reach out to your Swiftly contact

