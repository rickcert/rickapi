<?php

//定义方法

function rick($name)
{

$servername = "localhost";  
//数据库账号
$username = "root";  
//数据库密码
$password = "111111";  
//数据库名
$dbname = "springboot-vue";  
  
// 创建连接  
$conn =new mysqli($servername, $username, $password, $dbname);  
// 检测连接  
if ($conn->connect_error) {  
    die("Connection failed: " . $conn->connect_error);  
}  
//连接数据库表  
$sql = "SELECT id,status,calltimes FROM hqh_api where api_name='$name'"; 
$res = $conn->query($sql);  
  $arr = array();  
// 输出每行数据  
// $arr["code"]="200";
while($row = $res->fetch_assoc()) {  
    $count=count($row);//不能在循环语句中，由于每次删除row数组长度都减小  
    for($i=0;$i<$count;$i++){  
        unset($row[$i]);//删除冗余数据  
    }  
    $arr=$row;  
  
}  
$count=(int)$arr["calltimes"];
$count=$count+1;
$sql = "UPDATE `hqh_api` SET `calltimes` = '$count' WHERE api_name='$name'"; 
$res = $conn->query($sql);  
$conn->close();
return $arr;


}