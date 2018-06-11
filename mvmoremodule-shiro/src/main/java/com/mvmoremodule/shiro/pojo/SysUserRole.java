package com.mvmoremodule.shiro.pojo;/**
 * Created by Administrator on 2018-05-23.
 */

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author xuzhiyong
 * @createDate 2018-05-23-9:35
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class SysUserRole {
    private Long userId;
    private Long roleId;
}
