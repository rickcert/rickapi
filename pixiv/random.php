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

//连接数据库表  
// $idid = $_GET["id"];
$id= rand(1, 30);

$url="https://pan.rickblog.icu/image/$id.png";
header("Location:$url", TRUE, 302);
exit();
}
else{
    echo "接口已禁用";
}

?>  
