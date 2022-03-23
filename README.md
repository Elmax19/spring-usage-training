## MongoDB

_**MongoDB**_ is the document database designed to make it easy for developers to work with data in any form, and from
any programming language.

As you can see, MongoDB stores BSON documents and for each key-value pairs, the BSON contains the key and the value
along with its type. This is how MongoDB knows that class_id is actually a double and not an integer, which is not
explicit in the Mongo Shell representation of this document.

Also, the Java driver would have generated the _id field with an ObjectId for us if we didn't explicitly create one here
but it's good practise to set the _id ourselves. This won't change our life right now but it makes more sense when we
directly manipulate POJOs and we want to create a clean REST API.

# Useful links:

- https://www.mongodb.com/developer/quickstart/java-setup-crud-operations
- https://www.programmerall.com/article/4200291822
- https://www.bing.com/videos/search?q=how+to+use+docker&docid=608020472876391126&mid=86614974D941C1613D7286614974D941C1613D72&view=detail&FORM=VIRE
- https://stackoverflow.com/questions/44878605/mongodb-basicdbobject-vs-document-in-java
- https://stackabuse.com/definitive-guide-to-jackson-objectmapper-serialize-and-deserialize-java-objects