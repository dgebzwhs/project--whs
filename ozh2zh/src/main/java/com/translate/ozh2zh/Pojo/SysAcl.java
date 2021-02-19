package com.translate.ozh2zh.Pojo;

    import java.time.LocalDateTime;
    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 
    * </p>
*
* @author whs
* @since 2020-05-19
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class SysAcl implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * id
            */
    private Integer id;

    private String name;

    private String url;

            /**
            * 备注
            */
    private String remark;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
