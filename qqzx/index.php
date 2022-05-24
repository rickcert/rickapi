<?php
include ("../rick.php");
$res =rick("qqzx");
if($res["status"]=="0"){
if ($_GET['qq']) {
$qq = $_GET['qq'];
$data = file_get_contents("http://webpresence.qq.com/getonline?type=1&$qq:");
$data || $data = strlen(file_get_contents("http://wpa.qq.com/pa?p=2:$qq:45"));
if(!$data) { return 0; } 
 
switch((string)$data){
  case 'online[0]=0;': exit('{"code":"201","msg":"电脑离线"}');return;
  case 'online[0]=1;': exit('{"code":"200","msg":"电脑在线"}') ;return;
 
} 
}
}
else{
    echo "接口已禁用";
}

?>