package com.lyx.common.base.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.lyx.common.base.exception.BizException;
import com.lyx.common.base.result.ResultCode;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * @author： anhc
 */
public class AssertUtil {


    /**
     * 断言为空 支持各种类型
     */
    public static void empty(Object value, ResultCode msg) {
        isTrue(ObjectUtil.isEmpty(value), msg);
    }

    /**
     * 断言不为空,支持多种类型
     */
    public static void notEmpty(Object value, ResultCode msg) {
        isTrue(ObjectUtil.isNotEmpty(value), msg);
    }


    /**
     * 断言等于0
     */
    public static void eqZero(Object value, ResultCode msg) {
        BigDecimal num = Convert.toBigDecimal(value);
        notEmpty(value, msg);
        equal(BigDecimal.ZERO, num, msg);
    }

    /**
     * 断言大于0
     */
    public static void gtZero(Object value, ResultCode msg) {
        BigDecimal valueBigDecimal = Convert.toBigDecimal(value);
        notEmpty(value, msg);
        isTrue(NumberUtil.isGreater(valueBigDecimal, BigDecimal.ZERO), msg);
    }

    /**
     * 断言大于等于0
     */
    public static void gteZero(Object value, ResultCode msg) {
        BigDecimal valueBigDecimal = Convert.toBigDecimal(value);
        notEmpty(value, msg);
        isTrue(NumberUtil.isGreaterOrEqual(valueBigDecimal, BigDecimal.ZERO), msg);
    }

    /**
     * 断言小于0
     */
    public static void ltZero(Object value, ResultCode msg) {
        BigDecimal num = Convert.toBigDecimal(value);
        notEmpty(value, msg);
        isTrue(NumberUtil.isLess(num, BigDecimal.ZERO), msg);
    }

    /**
     * 断言小于等于0
     */
    public static void lteZero(Object value, ResultCode msg) {
        BigDecimal num = Convert.toBigDecimal(value);
        notEmpty(value, msg);
        isTrue(NumberUtil.isLessOrEqual(num, BigDecimal.ZERO), msg);
    }

    /**
     * 断言value1 小于等于 value2
     */
    public static void lte(Object value1, Object value2, ResultCode msg) {
        BigDecimal big1 = Convert.toBigDecimal(value1);
        BigDecimal big2 = Convert.toBigDecimal(value2);
        isTrue(NumberUtil.isLessOrEqual(big1, big2), msg);
    }

    /**
     * 断言value1小于value2
     */
    public static void lt(Object value1, Object value2, ResultCode msg) {
        BigDecimal big1 = Convert.toBigDecimal(value1);
        BigDecimal big2 = Convert.toBigDecimal(value2);
        isTrue(NumberUtil.isLess(big1, big2), msg);
    }

    /**
     * 断言value1 >= value2
     */
    public static void gte(Object value1, Object value2, ResultCode msg) {
        BigDecimal big1 = Convert.toBigDecimal(value1);
        BigDecimal big2 = Convert.toBigDecimal(value2);
        isTrue(NumberUtil.isGreaterOrEqual(big1, big2), msg);
    }

    /**
     * 断言value1 > value2
     */
    public static void gt(Object value1, Object value2, ResultCode msg) {
        BigDecimal big1 = Convert.toBigDecimal(value1);
        BigDecimal big2 = Convert.toBigDecimal(value2);
        isTrue(NumberUtil.isGreater(big1, big2), msg);
    }

    /**
     * 断言两者相同
     */
    public static void equal(Object obj1, Object obj2, ResultCode msg) {
        isTrue(ObjectUtil.equal(obj1, obj2), msg);
    }

    /**
     * 断言两者不相同
     */
    public static void notEqual(Object obj1, Object obj2, ResultCode msg) {
        isTrue(ObjectUtil.notEqual(obj1, obj2), msg);
    }

    /**
     * 断言两者相同
     */
    public static void equalsAnyIgnoreCase(String value, String[] array, ResultCode msg) {
        isTrue(StrUtil.equalsAnyIgnoreCase(value, array), msg);
    }

    /**
     * 断言小于等于max
     */
    public static void max(Number value, int max, ResultCode msg) {
        notEmpty(value, msg);
        double valueDouble = value.doubleValue();
        isTrue(valueDouble <= max, msg);
    }

    /**
     * 断言小于等于max
     */
    public static void min(Number value, int min, ResultCode msg) {
        notEmpty(value, msg);
        double valueDouble = value.doubleValue();
        isTrue(valueDouble >= min, msg);
    }

    /**
     * 断言value长度在指定范围或等于
     */
    public static void maxSize(Object value, int max, ResultCode msg) {
        int valueLength = ObjectUtil.length(value);
        isTrue(valueLength <= max, msg);
    }

    /**
     * 断言value长度在指定范围或等于
     */
    public static void minSize(Object value, int min, ResultCode msg) {
        int valueLength = ObjectUtil.length(value);
        isTrue(valueLength >= min, msg);
    }

    /**
     * 断言是否为真
     */
    public static void isTrue(boolean flag, ResultCode msg) {
        isFalse(!flag, msg);
    }

    /**
     * 断言是否为假
     */
    public static void isFalse(boolean flag, ResultCode msg) {
        if (flag == false) {
            return;
        }
        throw new BizException(msg);
    }

}