# HW3 Part 1


## Queries:


### Model 1
```
SELECT product_id, price, is_available
FROM PRODUCT
WHERE category_name = "WATCH" and diameter = "44mm" and brand = "Tommy Hilfiger" and dial_color = "Beige";
```

### Model 2
```
SELECT p.product_id, p.price, p.is_available, w.*
FROM WATCHES w
WHERE w.diameter = "44mm" and w.brand = "Tommy Hilfiger" and Dial_Color = "Beige"
JOIN PRODUCT p ON w.product_id = p.product_id;
```

### Model 3
```
SELECT * 
FROM CATEGORY c
JOIN PROPERTY prop on c.category_name = "WATCHES" AND prop.category_id = c.category_id
JOIN PRODUCT prod on prop.product_id = prod.product_id
WHERE prod.product_id in (
	SELECT a.product_id
	FROM PROPERTY a JOIN PROPERTY b ON a.product_id = b.product_id
	JOIN PROPERTY c ON b.product_id = c.product_id
	WHERE a.key = 'Diameter' AND a.value = '44mm'
	AND b.key = 'Brand' AND b.value = 'Tommy_Hilfiger'
	AND c.key = 'Dial Color' AND c.value = 'Beige';
);
```
	
### Model 4 

General Outline: Our Redis store will have a similar structure to Model 2, where each category's name (or ID) is the
key. The value at that key is a hashmap-like object containing the products within that category, with each of the
values being another nested hashmap with different characteristics about the product. A search API would take in a
category, and relevant characteristics to search for. Retrieving category -> O(1), returning matching records -> O(n*k)
where n in the number of records in the table, and k is the number of characteristics for the category (assumes that
all items in a category have the same characteristics).

### Model 5 
```
db.products.find({
	'category': 'WATCHES',
	'characteristics.diameter': '44mm',
	'characteristics.brand': 'Tommy Hilfiger',
	'characteristics.dial_color': 'Beige
});
```

## Analysis of Options:

Model 1 has huge issues with storage. In most cases, a single product will use a tiny subset of the available fields in the database, even if the fields are generalized highly (`color_1` instead of `dial_color`, for example). Additionally, with the addition of new products, there will likely need to be new columns; these new columns will be added frequently in the case of large product catalog, meaning there will often need to be an additional n (often unused) cells being added to the table. 

Model 2 is better in terms of actually occupy space that is allocated in the db but still not very scalable; entire tables need to be added with each new category of product. From a query complexity standpoint, it is actually a pretty reasonable solution, but the need for so many tables makes it unusable. 

Model 3 has horrible query complexity that necessitates huge joins across tables, and that alone makes it a poor choice. It is basically a relational version of Mongo, with more restrictions. At this point, it would be better to go with a data storage  application that is specifically engineered for this use case (i.e. Mongo).

Model 4 would also essentially be a recreation of Mongo using Redis. That the time complexity of the queries would probably not be great unless we fundamentally changed Redis (by adding b-trees to speed up querying, etc). Redis should really be used for quick storage and grabbing objects that we know are already there, as opposed to searching for data.

Model 5 would be the best option because this is something Mongo is specifically made for. It is schemaless which provides the flexibility to model large product catalogs, and is built to provide fast searches of its data. 

