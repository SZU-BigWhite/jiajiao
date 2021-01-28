package com.jiajiao.web.service;

import com.jiajiao.web.form.GetParentNeedOrderForm;
import com.jiajiao.web.vo.ParentNeedVo;
import com.jiajiao.web.vo.ParentSendStudentVo;
import com.jiajiao.web.vo.ResponseVo;

public interface IParentService {
    public ResponseVo getParentNeedByUId(int id);
    public ResponseVo getParentsNeed();
    public ResponseVo getParentsNeedByOrder(GetParentNeedOrderForm parentNeedOrderVo);
    public ResponseVo addParentNeedByUId(int id, ParentNeedVo parentNeedVo);
    public ResponseVo updateParentNeed(ParentNeedVo parentNeedVo);
    public ResponseVo deleteParentNeed(int id);
    public boolean checkIdAndUId(Integer id,Integer uId);
    public ResponseVo sendNeedToResume(ParentSendStudentVo parentSendStudentVo);
    public ResponseVo deleteSendNeedToResume(ParentSendStudentVo psend);
    public ResponseVo getParentReceive(Integer id);
    public ResponseVo getParentSent(Integer id);
    public ResponseVo getParentNeedSum();

}
