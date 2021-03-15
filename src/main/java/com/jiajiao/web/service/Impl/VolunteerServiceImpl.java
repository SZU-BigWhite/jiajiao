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
    public ResponseVo deleteVolunteerThings(Integer id, Integer uId) {
        VolunteerThings volunteerThings = volunteerThingsMapper.selectByPrimaryKey(id);
        if(!volunteerThings.getuId().equals(uId)){
            Integer cId = volunteerThings.getcId();
            VolunteerCollection volunteerCollection = volunteerCollectionMapper.selectByPrimaryKey(cId);
            if(!volunteerCollection.getuId().equals(uId)){
                return ResponseVo.error("没有相关的操作权限");
            }
        }
        Integer outId = volunteerThings.getId();
        thingsMapper.deleteByOutKey(outId);

        volunteerThingsMapper.deleteByPrimaryKey(id);
        return ResponseVo.success("删除捐赠物品成功");
    }

    @Override
    public ResponseVo getVolunteerThingsByUId(Integer uId) {
        List<VolunteerThings> volunteerThings = volunteerThingsMapper.selectByUId(uId);
        List<VolunteerThingsForm> res=new ArrayList<>();
        for(VolunteerThings temp:volunteerThings){
            //获取捐赠的书籍
            Integer outId = temp.getId();
            List<Things> thingsList = thingsMapper.selectByOutKey(outId);
            List<String> thingsListString=new ArrayList<>();
            for(Things tempThings:thingsList){
                thingsListString.add(tempThings.getName());
            }
            //创建返回对象
            VolunteerThingsForm volunteerThingsForm=new VolunteerThingsForm();
            volunteerThingsForm.setBooks(thingsListString);
            volunteerThingsForm.setVolunteerThings(temp);
            //加入到返回对象
            res.add(volunteerThingsForm);
        }
        return ResponseVo.success("获取捐赠物品成功",res);
    }

    @Override
    public ResponseVo getVolunteerThingsByCId(Integer cId) {
        List<VolunteerThings> volunteerThings = volunteerThingsMapper.selectByCId(cId);
        List<VolunteerThingsForm> res=new ArrayList<>();
        for(VolunteerThings temp:volunteerThings){
            //获取捐赠的书籍
            Integer outId = temp.getId();
            List<Things> thingsList = thingsMapper.selectByOutKey(outId);
            List<String> thingsListString=new ArrayList<>();
            for(Things tempThings:thingsList){
                thingsListString.add(tempThings.getName());
            }
            //创建返回对象
            VolunteerThingsForm volunteerThingsForm=new VolunteerThingsForm();
            volunteerThingsForm.setBooks(thingsListString);
            volunteerThingsForm.setVolunteerThings(temp);
            //加入到返回对象
            res.add(volunteerThingsForm);
        }
        return ResponseVo.success("获取捐赠物品成功",res);
    }

    @Override
    public ResponseVo addVolunteerCollection(VolunteerCollection collection) {
        volunteerCollectionMapper.insertSelective(collection);
        return ResponseVo.success("增加成功");
    }

    public ResponseVo updateVolunteerCollection(VolunteerCollection volunteerCollection,Integer uId){
        VolunteerCollection getCollection = volunteerCollectionMapper.selectByPrimaryKey(volunteerCollection.getId());
        if(!getCollection.getuId().equals(uId)){
            return ResponseVo.error("无法操作他人的需求");
        }
        volunteerCollection.setuId(uId);
        volunteerCollectionMapper.updateByPrimaryKeySelective(volunteerCollection);
        return ResponseVo.success("更新成功");
    }

    @Override
    public ResponseVo getAllVolunteerCollection() {
        List<VolunteerCollection> volunteerCollections = volunteerCollectionMapper.selectAll();
        return ResponseVo.success("义工收集列表返回成功",volunteerCollections);
    }

    @Override
    public ResponseVo getMyVolunteerCollection(Integer uId) {
        List<VolunteerCollection> volunteerCollections = volunteerCollectionMapper.selectByUId(uId);
        return ResponseVo.success("个人义工收集列表返回成功",volunteerCollections);
    }

    @Override
    public ResponseVo deleteVolunteerCollection(Integer id, Integer uId) {
        VolunteerCollection getCollection = volunteerCollectionMapper.selectByPrimaryKey(id);
        if (!getCollection.getuId().equals(uId)) {
            return ResponseVo.error("没有相关权限");
        }
        volunteerCollectionMapper.deleteByPrimaryKey(id);
        return ResponseVo.success("删除成功");
    }
}
