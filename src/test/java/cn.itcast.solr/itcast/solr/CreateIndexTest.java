package cn.itcast.solr.itcast.solr;

import cn.itcast.solr.Item;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by Mary on 2018/1/30.
 */
public class CreateIndexTest {
    /**
     * 创建索引
     * @throws IOException
     * @throws SolrServerException
     */
    @Test
    public void  testCreateIndex() throws IOException, SolrServerException {
        SolrServer server= new HttpSolrServer("http://localhost:8080/solr/core2");
        SolrInputDocument document=new SolrInputDocument();
        document.addField("id","1000");
        document.addField("title","idea测试");
        document.addField("price","1999999");
        server.add(document);
        server.commit();
    }

    @Test
    public void  testCreateIndexWithBean() throws IOException, SolrServerException {
        SolrServer server= new HttpSolrServer("http://localhost:8080/solr/core2");
        Item item = new Item("300", "乌龙茶", 90000L);
        server.addBean(item);
        server.commit();
    }

    @Test
    public void  testQueryTest() throws IOException, SolrServerException {
        SolrServer server= new HttpSolrServer("http://localhost:8080/solr/core2");
        SolrQuery query=new SolrQuery("title:手机");
        //执行查询并响应
        QueryResponse response = server.query(query);
        //从响应中获取数据 并解析
        SolrDocumentList documents = response.getResults();
        for (SolrDocument document : documents) {
            String id = (String) document.getFieldValue("id");
            String title = (String) document.getFieldValue("title");
            Long price = Long.valueOf(document.getFieldValue("price").toString());
            System.out.println("id: "+id+" ,title: "+title+" ,price: "+price);
        }

    }

    @Test
    public void  testQueryTestWithBean() throws IOException, SolrServerException {
        SolrServer server= new HttpSolrServer("http://localhost:8080/solr/core2");
        SolrQuery query=new SolrQuery("title:手机");
        QueryResponse response = server.query(query);
        List<Item> items = response.getBeans(Item.class);
        System.out.println("本次共搜索到" + items.size() + "条数据。");
        for(Item item:items){
            System.out.println(item);
        }
    }
}
