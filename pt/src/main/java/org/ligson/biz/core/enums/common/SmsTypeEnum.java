package org.ligson.biz.core.enums.common;

/**
 * Created by ligson on 2015/11/27.
 * 短信类型
 */
public enum SmsTypeEnum {
    RESET_PWD(1, "重置密码"),
    VALID_MOBILE(2, "验证手机号"),
    BIND_ACCOUNT(3, "绑定账号");


    private int type;
    private String name;

    SmsTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static SmsTypeEnum get(final int type) {
        for (SmsTypeEnum em : SmsTypeEnum.values()) {
            if (em.getType() == type) {
                return em;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "SmsTypeEnum{" +
                "type=" + type +
                ", name='" + name + '\'' +
                '}';
    }
}
