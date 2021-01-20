package com.jiajiao.web.service;

import com.jiajiao.web.vo.GetParentNeedOrderVo;
import com.jiajiao.web.vo.ParentNeedVo;
import com.jiajiao.web.vo.ParentSendStudentVo;
import com.jiajiao.web.vo.ResponseVo;

public interface IParentService {
    public ResponseVo getParentNeedByUId(int id);
    public ResponseVo getParentsNeed();
    public ResponseVo getParentsNeedByOrder(GetParentNeedOrderVo parentNeedOrderVo);
    public ResponseVo addParentNeedByUId(int id, ParentNeedVo parentNeedVo);
    public ResponseVo updateParentNeed(ParentNeedVo parentNeedVo);
    public ResponseVo deleteParentNeed(int id);
    public ResponseVo sendNeedToResume(ParentSendStudentVo parentSendStudentVo);
}
