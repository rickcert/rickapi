<!-- article-title样式为居中 -->
<!-- no-number标记后该标题不会自动生成编号 -->

<h1 class="article-title no-number">天气</h1>

> [!note]
>
> 接口状态正常



## 🌄请求URL


```
https://api.rickblog.icu/weather

```



## 🌋请求示例 GET

```java
https://api.rickblog.icu/weather/?city=湘潭
```



## 🗻返回数据

```
 "data": {
    "yesterday": {
      "date": "20日星期五",
      "high": "高温 21℃",
      "fx": "西北风",
      "low": "低温 15℃",
      "fl": "<![CDATA[2级]]>",
      "type": "阴"
    },
    "city": "湘潭",
    "forecast": [
      {
        "date": "21日星期六",
        "high": "高温 27℃",
        "fengli": "<![CDATA[1级]]>",
        "low": "低温 17℃",
        "fengxiang": "东北风",
        "type": "小雨"
      },
      ],
    "ganmao": "感冒易发期，外出请适当调整衣物，注意补充水分。",
    "wendu": "22"
  },
  "status": 1000,
  "desc": "OK"
}
```



## 🌅调用效果



> 暂无 


------

| 参数名称 | 类型 | 参数值 | 描述 |
| -------- | ---- | ------ | ---- |
|city  | 必填 | city | 即需要查询的城市 |

