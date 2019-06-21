package com.zexinchen.myapplication.data.bean

/**
 * @author czx
 * 包名:pospp.newland.mbaselibrary.data.protocol
 * 文件名:BaseResp
 * 创建时间:2018/11/8 15:25
 * DONE:
 */


data class BaseResp<out T>(val resultcode: String, val reason: String, val result: T, val error_code: String)
