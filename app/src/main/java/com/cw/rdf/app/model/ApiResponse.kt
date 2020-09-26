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

/**
 *
 * @Description:微信公众号
 * @Author: wanglejun
 * @CreateDate：2020/9/24 11:57 PM
 *
 */
data class ArticleChapter(
    var id: Int,
    var courseId: Int,
    var name: String,
    var order: Int,
    var parentChapterId: Int,
    var userControlSetTop: Boolean,
    var visible: Int
)

/**
 *
 * @Description: 用户信息
 * @Author: wanglejun
 * @CreateDate：2020/9/24 11:57 PM
 *
 */
data class UserInfo(
    var userName: String,
    var passwd: String
)

/**
 *
 * @Description: 首页 Banner
 * @Author: wanglejun
 * @CreateDate：2020/9/24 11:59 PM
 *
 */
data class Banner(
    var id: Int,
    var desc: String,
    var imagePath: String,
    var isVisible: Int,
    var order: Int,
    var title: String,
    var type: Int,
    var url: String
)