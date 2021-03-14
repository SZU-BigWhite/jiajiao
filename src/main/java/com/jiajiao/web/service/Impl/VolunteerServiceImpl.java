package com.jiajiao.web.service.Impl;

import com.jiajiao.web.dao.ThingsMapper;
import com.jiajiao.web.dao.VolunteerCollectionMapper;
import com.jiajiao.web.dao.VolunteerThingsMapper;
import com.jiajiao.web.form.VolunteerThingsForm;
import com.jiajiao.web.pojo.Things;
import com.jiajiao.web.pojo.VolunteerCollection;
import com.jiajiao.web.pojo.VolunteerThings;
import com.jiajiao.web.service.IVolunteerService;
import com.jiajiao.web.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VolunteerServiceImpl implements IVolunteerService {

    @Autowired
    ThingsMapper thingsMapper;
    @Autowired
    VolunteerThingsMapper volunteerThingsMapper;
    @Autowired
    VolunteerCollectionMapper volunteerCollectionMapper;

    @Override
    public ResponseVo addVolunteerThings(VolunteerThingsForm form,Integer uId) {
        VolunteerThings volunteerThings = form.getVolunteerThings();
        volunteerThings.setuId(uId);
        volunteerThingsMapper.insertSelective(volunteerThings);

        Integer outId = volunteerThings.getId();
        List<String> booksString = form.getBooks();
        List<Things> thingsList=new ArrayList<>();
        for(String bookName:booksString){
            Things bookThing=new Things();
            bookThing.setOutId(outId);
            bookThing.setName(bookName);
            thingsList.add(bookThing);
        }
        thingsMapper.insertList(thingsList);
        return ResponseVo.success("增加成功");
    }

    @Override
    public ResponseVo addVolunteerCollection(VolunteerCollection collection) {
        volunteerCollectionMapper.insertSelective(collection);
        return ResponseVo.success("增加成功");
    }

    @Override
    public ResponseVo deleteVolunteerConllection(Integer id, Integer u_id) {
        return null;
    }
}
