# CBS Webshop Project
### Final assignment for CBS course [KAN-CEBUO1000U](http://kursuskatalog.cbs.dk/2016-2017/KAN-CEBUO1000U.aspx)
#### Team: Aliz Gyüre :penguin:, André Jansson :sheep:

Template for this layout has been designed by Peter Lorensen; Problem inspired by Yifei Ge, Alexandru Catalin Lascar, Hao Hua Sun Yin, Klaus Christian Langenheldt, Rasmus Ege Nielsen

SYSTEM SPECIFICATION

CBS Collection is the official merchandise of CBS, run by CBS students. It has long been selling
merchandise via the traditional way. Now with the new school year starts, CBS Collection has
decided to build a web shop to boost sales. Your team has been assigned the task for developing
the web shop.

### VERSION 1.0
#### Functional requirements

The main requirements of the system is that it should allow a customer to browse available
products and make a purchase transaction .
- [x] The customer can create a profile (including address) and system will generate username
and password. They system should store customer details and password in variables. The
system should enable customer to login with their username and password.
- [ ] The customer needs to be able to browse the product catalogue, selects one or more products of
their choice. The selected product(s) should be stored in a shopping cart.
- [ ] The customers should be able to view and modify the shopping cart before they check out.
- [ ] The customer should be able to check out and pay for the items they have chosen. If shipping information is different from their default address, the system should ask the
customer which address to ship to.
- [ ] The web shop sells at least three types of products, e.g. T – shirts, caps and sweaters.
- [ ] Each product has certain attributes, such as color, size, price and so forth.
- [ ] When the order is completed, the customer should be displayed a confirmation/receipt.

#### Design Suggestions
The program must have the following classes:
- **WebShop**: represents the entire organisation with all the webshop. This can be used to
control all the interactions between classes. This class should not do any ‘work’, but instead
pass information and request to other classes and back to the user interface. This should
print out the welcome and thank you message to the customer. The interaction between
customer and the system can be done here for example where the use is and where user
wants to go. Print a welcome message and gives customer option to browse through the
catalogue and make purchase transaction. Give customer the option to view and
amend(delete or change) items in the shopping cart. You may like to have these operations
in ShoppingCart. Print confirmation and recipt of the transaction.
- **Product**: This should store details of product e.g T-Shirt, caps, sweaters, colour , size price
and so forth.
- **ShoppingCart**: should contain list of items (ArrayList) customer has ordered and calculates
the total price of items in the ShoppingCart. This class can also process payment.
- **Customer**: with attributes name, address and date of birth, should be able to register an
account and login with their username/password. In the case the user enters wrong login
credentials three times in a row, the program should display an error message and
terminate.

-----------------

### VERSION 2.0

#### Functional requirements
- Create a stock list of products in an external file (The code to work with files will be
provided to you). You might want to initially get a working version by using an ArrayList.
Therefore in Product class below there will be an extra attribute – “stock” of integer type.
When the customer selects an item, system should check the required number of item is
less than available stock, if so, place the item into shopping cart and subtract the number
from stock accordingly; otherwise, notify the customers that they couldn’t place an order
larger than the “stock”.
- Implement exception handling for file input and output, as well as for user input. The user
should be presented with the appropriate error messages.
- Revise the Product class into super class and create subclasses for three types of T-Shirt,
caps, sweaters and include other attributes relevant for the product type.
- When customer has entered credit card information, validate the format of the input, e.g.
(card number – 12 digits, expiry date xx/xx, CVC – three digits, card holder’s name –
String). If all input fulfills, the payment is considered to be approved. If any information
doesn’t fulfill, ask the customer to re-enter the information. You may like to create a
separate class called payment class.
- When the total sum of the order exceeds certain amount, customer receives a free item (e.g.
a cap) as a gift, which will be also added into shopping cart and get displayed in the receipt
after check out.

Hand-in:
The project must be answered in pairs.
