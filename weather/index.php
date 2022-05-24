<?php
include ("../rick.php");
$res =rick("weather");
if($res["status"]=="0"){
header("content-Type: application/json,charset=utf-8");
$city=$_GET['city'];
$url="http://wthrcdn.etouch.cn/weather_mini?city=".$city;
$str = file_get_contents($url);
$result= gzdecode($str);
//end
echo $result;
}
else{
    echo "接口已禁用";
}

?>