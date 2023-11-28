package com.chunhui.web.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 资源文件;
 *
 * @author : genghui
 * @date : 2023-11-28
 */
@Data
@TableName("res_file")
public class ResFile extends BaseDO {


    /**
     * 资源id;res_info.id
     */
    private String resId;

    /**
     * 文件id;sys_file.id
     */
    private String fileId;

}