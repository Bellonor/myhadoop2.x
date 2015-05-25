模板
curl -XGET http://localhost:9200/megacorp/employee/1 
3.1 文档

3.2 索引、3.3 获取
curl -XPOST http://localhost:9200/megacorp/employee/1 -d '{
  "first_name": "John",
  "last_name": "Smith",
  "age": 25,
  "about": "I love to go rock climbing",
  "interests": [
    "sports",
    "music"
  ]
}'
新增
curl -XPUT http://localhost:9200/website/blog/123 -d '{

  "title": "My first blog entry",
  "text":  "Just trying this out...",
  "date":  "2014/01/01"
}'
不指定ID，用自增ID
curl -XPOST http://localhost:9200/website/blog/ -d '{

  "title": "ID 是自增的 My first blog entry",
  "text":  "Just trying this out...",
  "date":  "2014/01/01"
}'
# 讀取
curl -XGET http://localhost:9200/website/blog/123 
获取全部的文档
curl -XGET http://localhost:9200/website/blog/_search
获取单个文档的全部属性
curl -i -XGET http://localhost:9200/website/blog/123?pretty
相当于select title,text
curl -i -XGET http://localhost:9200/website/blog/123?_source=title,text
你只想得到_source字段而不要其他的元数据，你可以这样请求：
curl -i -XGET http://localhost:9200/website/blog/123/_source
3.3 获取

curl -XGET 'http://localhost:9200/_count?pretty' -d '
{
    "query": {
        "match_all": {}
    }
}
'
3.4
判断文档是否存在
curl -i -XHEAD http://localhost:9200/website/blog/123
3.5更新
更新一个已经存在文档，只是版本号改变，但你不能去访问它
curl -XPUT http://localhost:9200/website/blog/123 -d '{

  "title": "version3 My first blog entry",
  "text":  "Just trying this out...",
  "date":  "2014/01/01"
}'
3.6 删除
curl -i -XDELETE http://localhost:9200/website/blog/125

3.9 局部更新,增加两个字段
curl -i -XPOST http://localhost:9200/website/blog/123/_update -d '{
   "doc" : {
      "tags" : [ "testing" ],
      "views": 0
   }
}'

curl -i -XPOST http://localhost:9200/website/blog/123/_update -d '{
   "script" : "ctx._source.views+=1"
}'

3.10 检索多个文档,会返回两行文档
curl -i -XGET http://localhost:9200/website/blog/_mget -d '{
   "docs" : [
      {
         "_index" : "website",
         "_type" :  "blog",
         "_id" :    124
      },
      {
         "_index" : "website",
         "_type" :  "blog",     
         "_id" :    123,
         "_source": "views"
      }
   ]
}'

curl -i -XGET http://localhost:9200/website/blog/_mget -d '{
   "ids" : [ "123", "124" ]
}'
3.11
curl -i -XPOST http://localhost:9200/website/blog/_bulk -d '{ "delete": { "_index": "website", "_type": "blog", "_id": "123" }} <1>
{ "create": { "_index": "website", "_type": "blog", "_id": "123" }}
{ "title":    "My first blog post" }
{ "index":  { "_index": "website", "_type": "blog" }}
{ "title":    "My second blog post" }
{ "update": { "_index": "website", "_type": "blog", "_id": "123", "_retry_on_conflict" : 3} }
{ "doc" : {"title" : "My updated blog post"} } <2>'