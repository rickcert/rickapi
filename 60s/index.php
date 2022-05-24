<?php

include ("../rick.php");
$res =rick("60s");
if($res["status"]=="0"){
header("content-Type: application/json,charset=utf-8");
$url="https://api.iyk0.com/60s/";
$str = file_get_contents($url);
$s1=json_decode($str);
$arr = array();  
$arr["code"]=$s1->code;
$arr["msg"]=$s1->msg;
$arr["imageUrl"]= $s1->imageUrl;
$arr["tips"]="注意:新闻数据是在每天凌晨一点更新，如设定定时任务请在凌晨一点后调用本接口！谢谢配合！";
echo json_encode($arr,JSON_UNESCAPED_UNICODE);
}
else{
    echo "接口已禁用";
}

?>