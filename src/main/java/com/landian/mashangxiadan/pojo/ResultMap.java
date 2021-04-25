package com.landian.mashangxiadan.pojo;

import lombok.Data;

import java.util.List;

/**
 * 统一封装一个返回结果集
 * @author Yu W
 * @date 2020/11/2 11:28
 * 自定义响应数据结构
 *
 *
 *                  其他自行处理
 *                  200：表示成功
 *                  500：表示错误，错误信息在msg字段中
 *                  501：bean验证错误，不管多少个错误都以map形式返回
 *                  502：拦截器拦截到用户token出错
 *                  555：异常抛出信息
 */
@Data
public class ResultMap<T> {
    /**
     * 返回状态码
     * 200 成功200
     *
     */
    private Integer code;

    /**
     * 返回的结果集合LIST
     */
    private Object data;

    private String msg;



}
