package cn.itcast.solr.itcast.solrCould;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Mary on 2018/2/1.
 */
public class SolrCouldIndexCreate {


    @Test
    public void createIndexTest() throws IOException, SolrServerException {
        CloudSolrServer server=new CloudSolrServer("192.168.206.101:2181,192.168.206.102:2181,192.168.206.103:2181");

        //指定核心
        server.setDefaultCollection("myCollection1");

        SolrInputDocument document=new SolrInputDocument();
        document.addField("id","1");
        document.addField("title","月薪上万那是个梦");

        server.add(document);
        server.commit();

    }


    @Test
    public void queryTest() throws IOException, SolrServerException {
        CloudSolrServer server=new CloudSolrServer("192.168.206.101:2181,192.168.206.102:2181,192.168.206.103:2181");

        //指定核心
        server.setDefaultCollection("myCollection1");
        SolrQuery query=new SolrQuery("薪水");
        QueryResponse response = server.query(query);
        SolrDocumentList solrDocuments = response.getResults();
        for (SolrDocument solrDocument : solrDocuments) {
            String id = (String) solrDocument.getFieldValue("id");
            Object title = solrDocument.getFieldValue("title"); //title是一个字符串数组
            System.out.println("id = " + id+", title = " + title);
            System.out.println(solrDocument);
        }
    }

    @Test
    public void deleteTest() throws IOException, SolrServerException {
        CloudSolrServer server=new CloudSolrServer("192.168.206.101:2181,192.168.206.102:2181,192.168.206.103:2181");

        //指定核心
        server.setDefaultCollection("myCollection1");

        server.deleteById("1");
        server.commit();
    }
}
