<?php
include ("../rick.php");
$res =rick("pixiv");
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
// $idid = $_GET["id"];
$id = rand(1, 1000);
$status=0;
$sql = 'SELECT * 
FROM `hqh_pixiv` AS t1 JOIN (SELECT ROUND(RAND() * ((SELECT MAX(id) FROM `hqh_pixiv`)-(SELECT MIN(id) FROM `hqh_pixiv`))+(SELECT MIN(id) FROM `hqh_pixiv`)) AS id) AS t2 
WHERE t1.id >= t2.id  AND `tags` NOT LIKE "%裸%" AND `r18`="0" AND `tags` NOT LIKE "%18%" AND `tags` NOT LIKE "%乳%"AND `tags` NOT LIKE "%脱%" AND `tags` NOT LIKE "%臀%" AND `tags` NOT LIKE "%足%" AND `tags` NOT LIKE "%阴%" AND `tags` NOT LIKE "%幼%" AND `tags` NOT LIKE "%little%" AND `tags` NOT LIKE "%萝%" AND `tags` NOT LIKE "%洗%" AND `tags` NOT LIKE "%孩%" 
ORDER BY t1.id LIMIT 1 '; 
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
//print_r($arr);  
echo json_encode($arr,JSON_UNESCAPED_UNICODE);//json编码  
$conn->close();  
}else{
    echo "接口已禁用";
}


?>  
