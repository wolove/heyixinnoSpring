package hyx.controller;

import hyx.repository.mybatis.entity.Name;
import hyx.repository.mybatis.mapper.NameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hyx(610302) on 2017/6/24 0024.
 */
@RestController
public class FamilyController {

//    @Autowired
//    private NameMapper nameRepository;
//
//    @RequestMapping(value = "/name/{name_id}", method = RequestMethod.GET)
//    public Name getName(@PathVariable(value = "name_id") Long nameId) {
//        return nameRepository.selectByPrimaryKey(nameId);
//    }
//
//    @RequestMapping(value = "/name", method = RequestMethod.POST)
//    public Name create() {
//        Name name = new Name();
//        name.setId(102L);
//        name.setFamilyName("zym");
//        name.setName("hyx");
//        int what = nameRepository.insert(name);
//
//        return name;
//    }
}
