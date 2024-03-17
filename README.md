# 一个工具类，可以把json文件转化为对应创表语句

### 简单示例  
#### 1.创表
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

生成的创表sql文件
````
CREATE TABLE `userInfo` (
`name` varchar(255) ,
`id` varchar(255) ,
`age` int 
)ENGINE=InnoDB
````

#### 2.插入数据
json文件
````
{
    "userInfo" : [{
        "id" : "123",
        "name" : "Zhang San",
        "age" : "18"
    },{
        "id" : "456",
        "name" : "Li Ming",
        "age" : "12"
    }]
}
````

生成的sql文件
````
insert into userInfo (name,id,age) values('Zhang San','123','18')
````