package hyx.controller;

import hyx.repository.mysql.NameRepository;
import hyx.repository.mysql.entity.NameEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hyx(610302) on 2017/6/24 0024.
 */
//@RestController
public class FamilyController {

    @Autowired
    private NameRepository nameRepository;

    @RequestMapping(value = "/name/{name_id}",method = RequestMethod.GET)
    public Object getName(@PathVariable(value = "name_id")Long nameId) {
        return nameRepository.findOne(nameId);
    }
    @RequestMapping(value = "/name",method = RequestMethod.POST)
    public Object saveName() {
        NameEntity ne = new NameEntity();
        ne.setFamilyName("he");
        ne.setName("xxxxxxxxxxxxxx");
        return nameRepository.save(ne);
    }
}
