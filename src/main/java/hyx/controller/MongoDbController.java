package hyx.controller;

import hyx.repository.mongo.entity.Heros;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * 使用mongoTemplate 进行操作
 *
 * @author hyx(610302) on 2017/7/6 0006.
 */
@RestController
@RequestMapping("/mongo")
public class MongoDbController {


    @Autowired
    private MongoTemplate template;

    @RequestMapping(value = "/heros", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Heros save() {
        Heros hero = new Heros();
        hero.setName("mff");
        hero.setAge(18);
        hero.setQ("nanshou");
        hero.setRole("loler");

        template.insert(hero);
        Heros result = template.findById(hero.getId(), Heros.class);
        return result;
    }

    @RequestMapping(value = "/heros", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Heros> find(@RequestParam(value = "name", required = false) String name) {
        List<Heros> heros = new ArrayList<>();
        if (!StringUtils.isEmpty(name)) {
            heros = template.find(query(where("name").is(name)), Heros.class);
        } else {
            heros = template.find(null, Heros.class);
        }
        return heros;
    }

}
