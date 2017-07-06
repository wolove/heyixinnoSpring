package mongodb.local.test;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import org.bson.Document;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;

/**
 * @author hyx(610302) on 2017/7/3 0003.
 */
public class MongodbCRUD {
    public static void main(String[] args) {
        //默认mongo服务端口27017
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase("testdb");

        MongoCollection<Document> heros = db.getCollection("heros");

        //查询操作
        FindIterable<Document> docs = heros.find(and(gt("age", 28), eq("name", "hanzo")));
        docs.projection(Projections.include("name", "age"));

        docs.forEach(new MongodbCRUD.PrintBlock());

        //新增操作
        Document insertObj = new Document("name", "anna");
        insertObj.put("age", "unknown");
        insertObj.put("q", "nmjs");
        heros.insertOne(insertObj);
        System.out.println("===========新增后所有记录");
        heros.find().forEach(new MongodbCRUD.PrintBlock());

        //修改操作
        heros.updateOne(eq("name", "hanzo"), new Document("$inc", new Document("age", 1)));
        System.out.println("===========修改后所有记录");
        heros.find().forEach(new MongodbCRUD.PrintBlock());

        //删除操作
        heros.deleteOne(eq("name", "anna"));
        System.out.println("===========删除后所有记录");
        heros.find().forEach(new MongodbCRUD.PrintBlock());
    }

    private static class PrintBlock implements Block<Document> {
        @Override
        public void apply(Document document) {
            System.out.println(document.toString());
        }
    }
}

