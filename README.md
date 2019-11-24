### 关于Spring Data ElasticSearch使用的几点说明：
---
1. ik_max_word与ik_smart区分
  - IK分词器（一个针对中文的分词器）的两种分词模式
  - 前者会将文本做最细粒度的拆分，比如会将“中华人民共和国人民大会堂”拆分为“中华人民共和国、中华人民、中华、华人、人民共和国、人民、共和国、大会堂、大会、会堂等词语
  - 后者会做最粗粒度的拆分，比如会将“中华人民共和国人民大会堂”拆分为中华人民共和国、人民大会堂
2. Iterable<T> search(QueryBuilder var1)与Page<T> search(SearchQuery var1)区别
     也就是使用NativeSearchQueryBuilder和直接使用QueryBuilder的区别
  - 前者可以完成一些基本的复杂条件查询
  - 后者可以将多个前者条件组合起来，并且还可以对结果进行特殊处理，例如分页、排序、过滤等
  - 使用时，一般使用后者
3. Spring Data ElasticSearch中方法一共可以分为以下几类：
  - 使用ElasticsearchTemplate
  - 使用ItemRepository中自带方法以及自定义方法
  - 使用NativeSearchQueryBuilder、ItemRepository中的search方法实现较为复杂的查询
4. 优秀链接收藏：
  - https://blog.csdn.net/chen_2890/article/details/83895646
  - https://blog.csdn.net/majun_guang/article/details/81103623
  - https://blog.csdn.net/chengyuqiang/article/details/86135795（该博客的博主有ElasticSearch 6.x的系列博文）   
