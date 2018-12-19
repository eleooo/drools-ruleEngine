package com.jy.modules.drools.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

public class DroolsResultDTO implements Serializable {

    private static final long serialVersionUID = 1l;
    /*** 返回标识   true 成功 ；空值为 失败*/
    private boolean retStatus;
    /*** 规则得分*/
    private float ruleScore;
    /*** 备注信息*/
    private String remark;

    public boolean isRetStatus() {
        return retStatus;
    }

    public void setRetStatus(boolean retStatus) {
        this.retStatus = retStatus;
    }

    public float getRuleScore() {
        return ruleScore;
    }

    public void setRuleScore(float ruleScore) {
        this.ruleScore = ruleScore;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        String hitDetail = "===规则执行结果===";
        if (retStatus) {
            hitDetail += " 命中结果:命中,规则得分: " + ruleScore + ",备注信息:" + remark;
        } else {
            hitDetail += " 命中结果:未命中！";
        }
        return hitDetail;
    }

}
