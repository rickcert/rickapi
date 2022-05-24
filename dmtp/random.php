<?php
include ("../rick.php");
$res =rick("tp");
if($res["status"]=="0"){
header("Access-Control-Allow-Origin:*");//解决跨域请求问题
header('Access-Control-Allow-Methods:POST');
header('Access-Control-Allow-Headers:x-requested-with, content-type');  
header("Expires: Mon, 26 Jul 1997 05:00:00 GMT");
header("Last-Modified: " . gmdate("D, d M Y H:i:s") . "GMT");
header("Cache-Control: no-cache, must-revalidate");
header("Pragma: no-cache");
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
$sql = "SELECT * FROM hqh_dmtp WHERE del_flag =0  ORDER BY RAND() LIMIT 1;"; 
$result = $conn->query($sql);  
$row = $result->fetch_assoc();
$conn->close(); 
$url=$row["content"];
header("Location:$url", TRUE, 302);
exit();
}
else{
    echo "接口已禁用";
}

?>  
