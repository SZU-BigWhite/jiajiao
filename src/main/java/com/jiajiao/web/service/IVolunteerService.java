package com.jiajiao.web.service;

import com.jiajiao.web.form.VolunteerThingsForm;
import com.jiajiao.web.pojo.VolunteerCollection;
import com.jiajiao.web.vo.ResponseVo;

public interface IVolunteerService {
    ResponseVo addVolunteerThings(VolunteerThingsForm form,Integer uId);
    ResponseVo deleteVolunteerThings(Integer id,Integer uId);
    ResponseVo getVolunteerThingsByUId(Integer uId);
    ResponseVo getVolunteerThingsByCId(Integer cId);
    ResponseVo addVolunteerCollection(VolunteerCollection volunteerCollection);
    ResponseVo updateVolunteerCollection(VolunteerCollection volunteerCollection,Integer uId);
    ResponseVo getAllVolunteerCollection();
    ResponseVo getMyVolunteerCollection(Integer uId);
    ResponseVo deleteVolunteerCollection(Integer id,Integer uId);
}
