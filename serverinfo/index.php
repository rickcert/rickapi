
<?php
include ("../rick.php");
$res =rick("serverinfo");
if($res["status"]=="0"){

header('Access-Control-Allow-Origin:*');
header('Content-type: application/json');
error_reporting(0);
!empty($_GET['url']) ? $_GET['url'] : exit(json_encode(array(
    "msg"=>"请输入网址！"
)));
function span($str){
    $result = substr($str,strripos($str,"0\">")+3);
    $result = substr($result,0,strrpos($result,"</span>"));
    return $result;
}
$str = file_get_contents("http://ip.tool.chinaz.com/".$_GET['url']);
preg_match_all("/<span (.*?)>(.*?)<\/span>/ism", $str, $matches)  ;
$arr = array_unique($matches);
$ip1=str_replace("an class=\"Whwtdhalf w15-0 lh45\" style=\"cursor:pointer;\" onclick=\"AiWenIpData('","", span($arr[0][9]));
$ip2=str_replace("')\">","",$ip1);
$ip3=mb_substr($ip2,0,mb_strlen($ip2)/2) ;
if (span($arr[0][7])==""){
    exit("{\"msg\":\"查询失败\"}");
}else{
    exit(json_encode(array(
        "msg"=>"ok",
        "host"=>str_replace("an class=\"Whwtdhalf w15-0 lh45\">","",span($arr[0][8])),
        "ip"=>$ip3,
        "position"=>str_replace("an class=\"Whwtdhalf w15-0 lh45\">","", span($arr[0][10]))
    )));
}}
else{
    echo "接口已禁用";
}
?>
