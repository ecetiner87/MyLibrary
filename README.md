# Bibliophile

Bibliophile is a JAVA desktop application and used for keeping records of any personal library. The idea behind the application is keeping a database of book records, add/delete book to/from db, generating wishlists/searching wished books for online shopping and to determine the exact number of books that you read or not. 

![image](https://user-images.githubusercontent.com/43776056/59436390-30b3b400-8df8-11e9-9d8f-d999a81da168.png)

## Getting Started

Related application is a GUI application based on JAVA Swing. It also uses embedded APACHE DERBY DB. 

### Prerequisites

#### For Linux Users:

* Download "MyLibrary.jar" to /home/USER on your PC. 

* You should have JRE 1.6.0 at minimum.

#### For Windows Users:

* Download "Bibliophile.exe" to any folder on your PC.

* You should have latest Java Runtime Environment.


### Execution of Application

#### For Linux Users:
```
java -jar MyLibrary.jar
```

#### For Windows Users:
```
Double Click on related exe file!
```

## Usage

#### Show All Library Content:
* All Books' details are seen on this screen. 
* You can track total number of your books and number of read books. 
* Also you can follow your total expenses.
* You can export all library as CSV file.
* You can also import your books as txt file into your library. See https://github.com/ecetiner87/MyLibrary/blob/master/test.txt for example structure.

![Show All Library](https://user-images.githubusercontent.com/43776056/59436939-3eb60480-8df9-11e9-9c32-8cafe24d81ec.png)

#### Add New Book Into Library
* Enter Book Name, Author First Name, Author Last Name, Publisher, Publish Date(optional) in Year format, Price(optional), category and sub-category, read status and translator(optional)

![Add New Book](https://user-images.githubusercontent.com/43776056/59437344-18dd2f80-8dfa-11e9-8e0e-93ef94941066.png)

#### Delete Existing Book
* You can delete single book with "Delete Book" function.
* You can delete ALL books with "Delete ALL" function. You should be careful when using this; no rollback for this option.

![Delete Existing Book](https://user-images.githubusercontent.com/43776056/59437505-622d7f00-8dfa-11e9-82ea-0ae53401e268.png)

#### Search Book
* You can search books through your book database by author first name or bookname

![Search Book](https://user-images.githubusercontent.com/43776056/59437714-bdf80800-8dfa-11e9-8e99-a3f6a4f25ce1.png)

#### Update Book
* You can update book record details.
* If you update your book record with Read=YES options; it will be automatically added to Read History Menu.

![Update Book](https://user-images.githubusercontent.com/43776056/59437860-09aab180-8dfb-11e9-8ca1-08eebf147e96.png)

#### Wish List
* You can create a wishlist for your desired books.
* You can buy them online through google-books with BUY option.

![WishList](https://user-images.githubusercontent.com/43776056/59437984-42e32180-8dfb-11e9-8082-b78460b13b37.png)

#### Read History
* You can track your reading history through this function. 
* It will updated automatically when you UPDATE a book record with Read?=YES option on Update Book menu.

![Read History](https://user-images.githubusercontent.com/43776056/59438113-85a4f980-8dfb-11e9-9a91-e19ce11d341b.png)

#### Statistics
* You can visualize your Library details in Statistics menu:
      * Read/Not Read Books pie chart
      * Books by Category pie chart

![Statistics](https://user-images.githubusercontent.com/43776056/59438229-c866d180-8dfb-11e9-92f7-97efe93fcb50.png)

#### Borrowed Books
* You can visualize your borrowed books:
      * To whom you borrow your books
      * How long it passed
      * When you borrow your books
      * Action: You can clear borrowed status when a book returned.


## Built With

* Eclipse Photon

* Java

* Apache Derby DB



## Authors

* **Erkan Cetiner** - [ecetiner87](https://github.com/ecetiner87)


## License

This project is publicly available. You can download it and make any changes for your own purposes.

