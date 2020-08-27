#Main functionality:
>##User:
> 1. **registration**: registration is implemented by sign up page. User sets: name, surname, 
email, password, gender, phone. Password approval is required.
>2. **logging in**: users log in via "sign in" page. Filling email and password is necessary.
>3. **adding/deleting product to/from cart**: To add something to cart, user needs to 
>press **+**  button on the product block, in the catalog.
>4. **making an order**: user must be able to manage and order the products that 
>are in the cart. When the list of products is ready, the user is suggested to fill the form
> of order details(this form is on the next page): delivery  date, city, street and 
>pay type (checkbox that contains 2 options: cash or credit card). If cash is chosen, 
> order details form is submitted. Otherwise, the user's credit cards page appears and 
> suggests choosing existing card/s(if there are some), or bunding a new one. Then the form
> is submitted.          
>5. **adding/removing a credit card**: there should be an opportunity to add a new credit 
>card or to delete an existing one on the user's account page.  
>6. (optional impl) **viewing orders history**: the user is able to watch the history of his
>orders on his account page.
>7. (optional impl) **changing password**: to change current password the user needs enter 
>the old one and then set a new password. 
>8. (0optional impl) **resetting password**: to reset the current password the user should enter 
>email address that is bind to the page, and a new random one is sent to the email.

>## Administrator
>It's a kind of superuser that is able to do everything that a usual one can but also 
>do the following:
>1. **changing product status**: admin is able to change product status from active to 
>archieve type, if the product is archieve - no one is able to order it and it is not 
>displayed in the catalog. 
>2. (optional impl) **check orders statistics for a month**: in his account page there should 
>be an additional option or button to check statistics. 

>##Catalog
>1. **displaying the product list**: gets and displays the data from backend that contains all 
>tvs that are available.   
>2. **filters by different parameters**: everyone can set different filters for the products
>in the personal catalog view, for search simplification. 
>3. **adding product to the cart**: users must have an opportunity to add any product from 
>catalog to the card.   

>##Cart
>1. **displaying ordered products**: when the user is on the cart page, he is able to see and manage 
>the products.
>2. **displaying the total price**: there should be a special field for displaying total price.
