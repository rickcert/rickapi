<?php
header("Access-Control-Allow-Origin:*");//解决跨域请求问题
header('Access-Control-Allow-Methods:POST');
header('Access-Control-Allow-Headers:x-requested-with, content-type');  
header("content-Type: application/json,charset=utf-8");//字符编码设置  
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
$sql = "SELECT SUM(calltimes)  `callcount` FROM hqh_api"; 
$result = $conn->query($sql);  
  
$arr = array();  
$arr["data"]= array();
// 输出每行数据  
$arr["code"]="200";
$arr["data"][2]= array();
$row = $result->fetch_assoc();
$arr["data"][0]=$row; 
$sql = "SELECT COUNT(api_name)  `apicount` FROM hqh_api "; 
$result = $conn->query($sql); 
$row = $result->fetch_assoc();
$arr["data"][1]=$row;  

  
$sql = "SELECT description `label`,calltimes ` data` FROM hqh_api ORDER BY calltimes DESC
LIMIT 1, 6"; 
$result = $conn->query($sql);  

while($row = $result->fetch_assoc()) {  
    $count=count($row);//不能在循环语句中，由于每次删除row数组长度都减小  
    for($i=0;$i<$count;$i++){  
        unset($row[$i]);//删除冗余数据  
    }  
    array_push($arr["data"][2],$row);  
  
}  
  
//print_r($arr);  
echo json_encode($arr,JSON_UNESCAPED_UNICODE);//json编码  
$conn->close();  


?>  
