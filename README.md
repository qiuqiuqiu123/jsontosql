# 一个工具类，可以把json文件转化为对应创表语句

简单实例  
json文件
````
{
    "userInfo" : {
        "id" : "String",
        "name" : "String",
        "age" : "int"
    }
}
````

生成的sql文件
````
CREATE TABLE `userInfo` (
`name` varchar(255) ,
`id` varchar(255) ,
`age` int 
)ENGINE=InnoDB
````
