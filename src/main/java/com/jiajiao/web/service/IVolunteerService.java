package com.jiajiao.web.service;

import com.jiajiao.web.form.VolunteerThingsForm;
import com.jiajiao.web.pojo.VolunteerCollection;
import com.jiajiao.web.vo.ResponseVo;

public interface IVolunteerService {
    ResponseVo addVolunteerThings(VolunteerThingsForm form,Integer uId);
    ResponseVo deleteVolunteerThings(Integer id,Integer uId);
    ResponseVo getVolunteerThingsByUId(Integer uId);
    ResponseVo getVolunteerThingsByCId(Integer cId,Integer status);
    ResponseVo addVolunteerCollection(VolunteerCollection volunteerCollection);
    ResponseVo updateVolunteerCollection(VolunteerCollection volunteerCollection,Integer uId);
    ResponseVo getAllVolunteerCollection();
    ResponseVo getVolunteerCollectionById(Integer id);
    ResponseVo getMyVolunteerCollection(Integer uId);
    ResponseVo deleteVolunteerCollection(Integer id,Integer uId);
    ResponseVo setThingsStatus(Integer id,Integer status);
    ResponseVo setCollectionStatus(Integer id,Integer status,Integer uId);
}
