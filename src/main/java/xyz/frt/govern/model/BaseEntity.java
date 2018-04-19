package xyz.frt.govern.model;

import java.io.Serializable;

/**
 * @author phw
 * @date Created in 04-19-2018
 * @description
 */
public class BaseEntity implements Serializable {

    private Integer id;

    private Integer createTime;

    private Integer creator_id;

    private Integer isEnable;

    private String remark;
}
