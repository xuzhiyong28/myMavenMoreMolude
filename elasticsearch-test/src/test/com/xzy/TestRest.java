package com.xzy;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xzy.search.Movie;
import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.get.GetResult;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.management.Query;
import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestRest {
    /***
     * 高阶RestClient
     */
    private RestHighLevelClient highLevelClient;
    /***
     * 低阶Rest Client
     */
    private RestClient restClient;

    @Before
    public void before() {
        highLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.135.131", 9200, "http")
                )
        );
        restClient = RestClient.builder(
                new HttpHost("192.168.135.131", 9200, "http")
        ).build();
    }

    @After
    public void close() throws IOException {
        if (highLevelClient != null) {
            highLevelClient.close();
        }
        if (restClient != null) {
            restClient.close();
        }
    }

    @Test
    public void index1() throws IOException {
        IndexRequest request = new IndexRequest("movies", "movie", "7");
        Movie movie = new Movie();
        movie.setGenres(Lists.newArrayList("k1", "k2"));
        movie.setTitle("中国机长");
        movie.setDirector("灾难性电影");
        //request.routing("指定到某一个路由");
        request.source(JSON.toJSON(movie).toString(), XContentType.JSON);
        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");
        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        request.setRefreshPolicy("wait_for");
        IndexResponse indexResponse = highLevelClient.index(request, RequestOptions.DEFAULT);
        if (indexResponse.status() == RestStatus.CREATED) {
            String index = indexResponse.getIndex();
            String type = indexResponse.getType();
            String id = indexResponse.getId();
            long version = indexResponse.getVersion();
            System.out.println("index = " + index);
            System.out.println("type = " + type);
            System.out.println("id = " + id);
            System.out.println("version = " + version);
        }
    }

    @Test
    public void index2() throws IOException {
        //采用map方式
        Map<String, Object> jsonMap = Maps.newHashMap();
        jsonMap.put("title", "我和我的祖国");
        jsonMap.put("director", "灾难性电影");
        jsonMap.put("genres", Lists.newArrayList("k1", "k2"));
        IndexRequest indexRequest = new IndexRequest("movies", "movie");
        indexRequest.source(jsonMap);
        indexRequest.timeout(TimeValue.timeValueSeconds(1));
        indexRequest.timeout("1s");
        indexRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        indexRequest.setRefreshPolicy("wait_for");
        IndexResponse indexResponse = highLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        if (indexResponse.status() == RestStatus.CREATED) {
            String index = indexResponse.getIndex();
            String type = indexResponse.getType();
            String id = indexResponse.getId();
            long version = indexResponse.getVersion();
            System.out.println("index = " + index);
            System.out.println("type = " + type);
            System.out.println("id = " + id);
            System.out.println("version = " + version);
        }
    }

    @Test
    public void index3() throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        builder.field("title", "烈火英雄");
        builder.field("director", "冒险类电影");
        builder.field("genres", Lists.newArrayList("k1", "k2"));
        builder.endObject();
        IndexRequest indexRequest = new IndexRequest("movies", "movie");
        indexRequest.source(builder);
        indexRequest.timeout(TimeValue.timeValueSeconds(1));
        indexRequest.timeout("1s");
        indexRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        indexRequest.setRefreshPolicy("wait_for");
        //同步执行
        IndexResponse indexResponse = highLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        //异步执行
        highLevelClient.indexAsync(indexRequest, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            public void onResponse(IndexResponse indexResponse) {
                if (indexResponse.status() == RestStatus.CREATED) {
                    String index = indexResponse.getIndex();
                    String type = indexResponse.getType();
                    String id = indexResponse.getId();
                    long version = indexResponse.getVersion();
                    System.out.println("index = " + index);
                    System.out.println("type = " + type);
                    System.out.println("id = " + id);
                    System.out.println("version = " + version);
                }
            }

            public void onFailure(Exception e) {
                System.err.println(e.getMessage());
            }
        });
        if (indexResponse.status() == RestStatus.CREATED) {
            String index = indexResponse.getIndex();
            String type = indexResponse.getType();
            String id = indexResponse.getId();
            long version = indexResponse.getVersion();
            System.out.println("index = " + index);
            System.out.println("type = " + type);
            System.out.println("id = " + id);
            System.out.println("version = " + version);
        }
    }


    @Test
    public void getApi1() throws IOException, InterruptedException {
        String id = "1";
        GetRequest request = new GetRequest("movies", "movie", id);
        //可选参数
        //request.fetchSourceContext(FetchSourceContext.DO_NOT_FETCH_SOURCE);
        //String[] includes = new String[]{"message", "*Date"};
        //String[] excludes = Strings.EMPTY_ARRAY;
        //FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
        //request.fetchSourceContext(fetchSourceContext);

        //同步写法
        //GetResponse getResponse = highLevelClient.get(request, RequestOptions.DEFAULT);
        //异步执行 listener的写法参照Index的异步执行的listener
        highLevelClient.getAsync(request, RequestOptions.DEFAULT, new ActionListener<GetResponse>() {
            public void onResponse(GetResponse documentFields) {
                String title = (String) documentFields.getSource().get("title");
                System.out.println(title);
            }

            public void onFailure(Exception e) {
                System.err.println(e.getMessage());
            }
        });
        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    public void existsApi() throws IOException, InterruptedException {
        GetRequest getRequest = new GetRequest("movies", "movie", "1");
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");

        //同步执行
        boolean exists = highLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println("记录是否存在:" + exists);

        //异步执行
        highLevelClient.existsAsync(getRequest, RequestOptions.DEFAULT, new ActionListener<Boolean>() {
            public void onResponse(Boolean aBoolean) {
                if (aBoolean) {
                    System.out.println("存在");
                } else {
                    System.out.println("不存在");
                }
            }

            public void onFailure(Exception e) {

            }
        });
        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    public void deleteApi() throws IOException {
        DeleteRequest request = new DeleteRequest("movies", "movie", "7");
        //同步执行
        //DeleteResponse deleteResponse = highLevelClient.delete(request, RequestOptions.DEFAULT);
        //异步执行
        highLevelClient.deleteAsync(request, RequestOptions.DEFAULT, new ActionListener<DeleteResponse>() {
            public void onResponse(DeleteResponse deleteResponse) {
                System.out.println(deleteResponse.status());
            }

            public void onFailure(Exception e) {

            }
        });
    }

    @Test
    public void updateApi() throws IOException {
        UpdateRequest request = new UpdateRequest("movies", "movie", "6");
        //方法一
        String jsonString = "{\"year\" : 1993}";
        request.doc(jsonString, XContentType.JSON);

        //同步执行
        //UpdateResponse updateResponse = highLevelClient.update(request, RequestOptions.DEFAULT);
        //异步执行
        highLevelClient.updateAsync(request, RequestOptions.DEFAULT, new ActionListener<UpdateResponse>() {
            public void onResponse(UpdateResponse updateResponse) {
                System.out.println(updateResponse);
            }

            public void onFailure(Exception e) {

            }
        });
    }

    @Test
    public void bulkApi() throws IOException, InterruptedException {
        BulkRequest request = new BulkRequest();
        /*Movie movie = new Movie();
        movie.setGenres(Lists.newArrayList("k1", "k2"));
        movie.setTitle("海贼王");
        movie.setDirector("海贼王");
        request.add(new IndexRequest("movies", "movie").source(JSON.toJSON(movie).toString(), XContentType.JSON));
        Movie movie2 = new Movie();
        movie2.setGenres(Lists.newArrayList("k1", "k2"));
        movie2.setTitle("海贼王2");
        movie.setDirector("海贼王2");
        request.add(new IndexRequest("movies", "movie").source(JSON.toJSON(movie2).toString(), XContentType.JSON));*/

        //删除
        request.add(new DeleteRequest("movies", "movie", "jbGLkW0B3xDBXKXvcxY5"));
        request.add(new DeleteRequest("movies", "movie", "jrGLkW0B3xDBXKXvcxY5"));

        //同步执行
        BulkResponse bulkResponse = highLevelClient.bulk(request, RequestOptions.DEFAULT);
        /*highLevelClient.bulkAsync(request, RequestOptions.DEFAULT, new ActionListener<BulkResponse>() {
            public void onResponse(BulkResponse bulkItemResponses) {
                System.out.println(bulkItemResponses.buildFailureMessage());
            }

            public void onFailure(Exception e) {

            }
        });
        TimeUnit.SECONDS.sleep(10);*/

        //批处理结果
        for (BulkItemResponse bulkItemResponse : bulkResponse) {
            DocWriteResponse itemResponse = bulkItemResponse.getResponse();
            if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.INDEX
                    || bulkItemResponse.getOpType() == DocWriteRequest.OpType.CREATE) {
                IndexResponse indexResponse = (IndexResponse) itemResponse;

            } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.UPDATE) {
                UpdateResponse updateResponse = (UpdateResponse) itemResponse;

            } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.DELETE) {
                DeleteResponse deleteResponse = (DeleteResponse) itemResponse;
            }
        }
    }


    @Test
    public void searchApi() throws IOException {
        //查询所有数据
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        searchRequest.indices("bank");
        searchRequest.types("account");
        //同步执行
        SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        //获取结果集
        SearchHits hits = searchResponse.getHits();
        long totalHits = hits.getTotalHits();
        float maxScore = hits.getMaxScore();
        SearchHit[] searchHits = hits.getHits();
        for(SearchHit hit : searchHits){
            String index = hit.getIndex();
            String type = hit.getType();
            String id = hit.getId();
            float score = hit.getScore();
            String sourceAsString = hit.getSourceAsString();
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(JSON.toJSON(sourceAsMap).toString());
        }
    }

    @Test
    public void searchApi2() throws IOException {
        //使用SearchSourceBuilder查询指定字段
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //term搜索，关键字搜索，这里要用小写，因为被分析成了小写
        //sourceBuilder.query(QueryBuilders.termQuery("lastname", "harding"));
        sourceBuilder.from(0);
        sourceBuilder.size(5);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        SearchRequest searchRequest2 = new SearchRequest();
        searchRequest2.indices("bank");
        searchRequest2.types("account");
        searchRequest2.source(sourceBuilder);
        SearchResponse searchResponse = highLevelClient.search(searchRequest2, RequestOptions.DEFAULT);
        //获取结果集
        SearchHits hits = searchResponse.getHits();
        long totalHits = hits.getTotalHits();
        float maxScore = hits.getMaxScore();
        SearchHit[] searchHits = hits.getHits();
        for(SearchHit hit : searchHits){
            String index = hit.getIndex();
            String type = hit.getType();
            String id = hit.getId();
            float score = hit.getScore();
            String sourceAsString = hit.getSourceAsString();
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(JSON.toJSON(sourceAsMap).toString());
        }
    }

    @Test
    public void searchApi3() throws IOException {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("lastname", "harding");
        //Fuzziness 模糊查找
        matchQueryBuilder.fuzziness(Fuzziness.AUTO);
        matchQueryBuilder.prefixLength(3);
        matchQueryBuilder.maxExpansions(10);
        sourceBuilder.query(matchQueryBuilder);
        //Specifying Sorting 指定排序
        sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
        sourceBuilder.sort(new FieldSortBuilder("_uid").order(SortOrder.ASC));
        sourceBuilder.fetchSource(false);
        String[] includeFields = new String[] {"account_number", "balance", "firstname"};
        String[] excludeFields = new String[] {"age"};
        sourceBuilder.fetchSource(includeFields, excludeFields);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("bank");
        searchRequest.types("account");
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        //获取结果集
        SearchHits hits = searchResponse.getHits();
        long totalHits = hits.getTotalHits();
        float maxScore = hits.getMaxScore();
        SearchHit[] searchHits = hits.getHits();
        for(SearchHit hit : searchHits){
            String index = hit.getIndex();
            String type = hit.getType();
            String id = hit.getId();
            float score = hit.getScore();
            String sourceAsString = hit.getSourceAsString();
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(JSON.toJSON(sourceAsMap).toString());
        }
    }

}
