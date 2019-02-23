package com.cengel.code.wrapper;

import com.cengel.code.task.TypeConverter;

public interface TypeWrapper<T> {
    T convert(T t, TypeConverter typeConverter);
}
