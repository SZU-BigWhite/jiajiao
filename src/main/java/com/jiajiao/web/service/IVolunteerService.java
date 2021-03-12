package com.jiajiao.web.service;

import com.jiajiao.web.form.VolunteerThingsForm;
import com.jiajiao.web.pojo.VolunteerCollection;
import com.jiajiao.web.vo.ResponseVo;

public interface IVolunteerService {
    ResponseVo addVolunteerThings(VolunteerThingsForm form,Integer uId);
    ResponseVo addVolunteerCollection(VolunteerCollection volunteerCollection);
    ResponseVo deleteVolunteerConllection(Integer id,Integer u_id);
}
