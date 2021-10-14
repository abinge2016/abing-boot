package com.abinge.boot.staging.checkauth;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: Dozer Bean复制工具
 * @Author: TUYONG
 * @Date: 2019/7/27 18:12
 * @Version V1.0.0
 */
@Slf4j
public class BeanUtil {

    protected static final Mapper mapper;

    static {
        mapper = DozerBeanMapperBuilder.buildDefault();
    }

    /**
     * @param s   数据对象
     * @param clz 复制目标类型
     * @param <T>
     * @param <S>
     * @return
     * @Description: 单个对象的深度复制及类型转换，dto/domain , po
     */
    public static <T, S> T convert(S s, Class<T> clz) {
        if (s == null) {
            return null;
        }
        return mapper.map(s, clz);
    }


    /**
     * @param s   数据对象
     * @param clz 复制目标类型
     * @param <T>
     * @param <S>
     * @return
     * @Description: list深度复制
     */
    public static <T, S> List<T> convert(List<S> s, Class<T> clz) {
        if (s == null) {
            return null;
        }
        List<T> list = new ArrayList<T>();
        for (S vs : s) {
            list.add(mapper.map(vs, clz));
        }
        return list;
    }

    /**
     * @param s   数据对象
     * @param clz 复制目标类型
     * @param <T>
     * @param <S>
     * @return
     * @Description: Set深度复制
     */
    public static <T, S> Set<T> convert(Set<S> s, Class<T> clz) {
        if (s == null) {
            return null;
        }
        Set<T> set = new HashSet<T>();
        for (S vs : s) {
            set.add(mapper.map(vs, clz));
        }
        return set;
    }

    /**
     * @param s   数据对象
     * @param clz 复制目标类型
     * @param <T>
     * @param <S>
     * @return
     * @Description: 数组深度复制
     */
    public static <T, S> T[] convert(S[] s, Class<T> clz) {
        if (s == null) {
            return null;
        }
        @SuppressWarnings("unchecked")
        T[] arr = (T[]) Array.newInstance(clz, s.length);
        for (int i = 0; i < s.length; i++) {
            arr[i] = mapper.map(s[i], clz);
        }
        return arr;
    }

    /**
     * 根据属性名获取属性值
     *
     * @param fieldName
     * @param t
     * @return
     */
    public static <T> String getStringByFieldName(String fieldName, T t) {
        Object obj = getValueByFieldName(fieldName, t);
        if (null == obj) {
            return null;
        }
        return obj.toString();

    }

    /**
     * 根据属性名获取属性值
     *
     * @param fieldName
     * @param t
     * @return
     */
    public static <T> Object getValueByFieldName(String fieldName, T t) {
        try {
            Field field = t.getClass().getDeclaredField(fieldName);
            //设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            Object obj = field.get(t);
            if (null == obj) {
                return null;
            }
            return obj;
        } catch (Exception e) {
            log.error("获取对象值失败：", e);
        }
        return null;
    }

}
