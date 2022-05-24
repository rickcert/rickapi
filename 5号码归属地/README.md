<!-- article-title样式为居中 -->
<!-- no-number标记后该标题不会自动生成编号 -->

<h1 class="article-title no-number">号码归属地</h1>

> [!note]
>
> 接口状态正常



## 🌄请求URL


```
https://api.rickblog.icu/tel

```



## 🌋请求示例 GET

```java
https://api.rickblog.icu/tel/?tel=13086542743
```



## 🗻返回数据

```
{
  "code": "200",
  "tel": "13086542743",
  "local": "归属地：四川省宜宾市",
  "duan": "号码段：1308654",
  "type": "卡类型：四川联通GSM卡",
  "yys": "运营商：中国联通",
  "bz": "通信标准：GSM (全球移动通信系统)"
}
```



## 🌅调用效果



> 暂无 


------

| 参数名称 | 类型 | 参数值 | 描述 |
| -------- | ---- | ------ | ---- |
|tel   | 必填 | tel | 即需要查询的号码 |

