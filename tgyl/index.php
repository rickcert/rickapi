<?php
include ("../rick.php");
$res =rick("tg");
if($res["status"]=="0"){
header("Access-Control-Allow-Origin:*");//解决跨域请求问题
header('Access-Control-Allow-Methods:POST');
header('Access-Control-Allow-Headers:x-requested-with, content-type');  
header("content-Type:application/json; charset=utf-8");//字符编码设置  
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
$sql = "SELECT * FROM hqh_tgyl WHERE del_flag =0 ORDER BY RAND() LIMIT 1;"; 
$result = $conn->query($sql);  
  
$arr = array();  
// 输出每行数据  
$arr["code"]="200";
while($row = $result->fetch_assoc()) {  
    $count=count($row);//不能在循环语句中，由于每次删除row数组长度都减小  
    for($i=0;$i<$count;$i++){  
        unset($row[$i]);//删除冗余数据  
    }  
    $arr["data"]=$row;  
  
}  
echo json_encode($arr,JSON_UNESCAPED_UNICODE);//json编码  
$conn->close();  
}
else{
    echo "接口已禁用";
}

?>  
