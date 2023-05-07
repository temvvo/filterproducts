# filterproducts
API was designed using StopLight to define the swagger api.</br>
2 endpoints where developed:</br>
The first one gets the visible products order by sequence.</br>
GET: /filter-products/api/v1/visible-products</br>
Result:</br>
![image](https://user-images.githubusercontent.com/16559193/236650407-49da31f8-4974-46bc-83da-18537b8cd780.png)
log output:</br>
![image](https://user-images.githubusercontent.com/16559193/236650191-0cdfe73a-ce00-4a49-921d-9dda99082123.png)

The second endpoint gets all products:</br>
GET: /filter-products/api/v1/products
![image](https://user-images.githubusercontent.com/16559193/236650459-e7d53db2-2a66-4531-ac79-b597a39984bc.png)

In the first approach due to a misunderstanding with the recruiter, csv files were migrated into a H2 database(with Flyway) instead of reading each file to solve the challenge. However I didn't delete it, if you want, you can take a look at the implemented migrtation.

All tests ar runing:
![image](https://user-images.githubusercontent.com/16559193/236660365-2a3ffa59-d7f8-4acf-afb2-2a659160db4c.png)

Coverage:
![image](https://user-images.githubusercontent.com/16559193/236661148-e0bf0558-5d68-4cd4-9cf7-d0965f6d1967.png)
![image](https://user-images.githubusercontent.com/16559193/236661188-41e92d5f-8b58-4b8b-a723-308ca1d91a79.png)





