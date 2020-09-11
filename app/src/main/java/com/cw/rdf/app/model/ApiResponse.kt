package com.cw.rdf.app.model

/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 2020/9/11 7:34 AM
 *
 */
data class ApiResponse<T>(
    var errorCode: Int? = 0,
    var errorMsg: String? = "",
    var data: T?
)

data class ArticleChapter(
    var id:Int,
    var courseId:Int,
    var name:String,
    var order:Int,
    var parentChapterId:Int,
    var userControlSetTop:Boolean,
    var visible:Int
)