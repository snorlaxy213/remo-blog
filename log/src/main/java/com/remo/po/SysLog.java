package po;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class SysLog implements Serializable {

    private static final long serialVersionUID = -413824666728167766L;

    private Integer id;
    private String username;
    private String operation;
    private Integer time;
    private String method;
    private String params;
    private String ip;
    private Date createTime;
    private String createUser;
    private Date updateTime;
    private String updateUser;
}
