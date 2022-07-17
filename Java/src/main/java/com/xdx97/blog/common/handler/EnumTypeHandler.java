package com.xdx97.blog.common.handler;



import com.xdx97.blog.common.enums.IBaseEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnumTypeHandler<T extends Enum<?> & IBaseEnum> extends BaseTypeHandler<IBaseEnum> {
    private Class<T> type;
    private final T[] enums;

    public EnumTypeHandler(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("【BaseEnumTypeHandler】Type argument cannot be null");
        } else {
            this.type = type;
            this.enums = (T[])type.getEnumConstants();
            if (this.enums == null) {
                throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
            }
        }
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String i = rs.getString(columnName);
        return rs.wasNull() ? null : this.locateEnumStatus(i);
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int i = rs.getInt(columnIndex);
        return rs.wasNull() ? null : this.locateEnumStatus(i);
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int i = cs.getInt(columnIndex);
        return cs.wasNull() ? null : this.locateEnumStatus(i);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, IBaseEnum parameter, JdbcType jdbcType) throws SQLException {
        JdbcType type = null;
        if (parameter.getCode() instanceof Integer) {
            type = JdbcType.TINYINT;
        }

        if (parameter.getCode() instanceof String) {
            type = JdbcType.VARCHAR;
        }

        ps.setObject(i, parameter.getCode(), type.TYPE_CODE);
    }

    private T locateEnumStatus(Object code) {
        Enum[] var2 = this.enums;
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            T status = (T)var2[var4];
            if (((IBaseEnum)status).getCode().toString().trim().equals(code.toString().trim())) {
                return status;
            }
        }

        throw new IllegalArgumentException("未知的枚举类型：" + code + ",请核对" + this.type.getSimpleName());
    }
}