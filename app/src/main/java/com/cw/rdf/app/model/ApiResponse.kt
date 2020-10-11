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

/**
 *
 * @Description:分页数据
 * @Author: wanglejun
 * @CreateDate：2020/9/30 12:12 AM
 *
 */
data class PageData<T>(
    var curPage: Int? = 0,
    var datas: T?,
    var offset: Int? = 0,
    var over: Boolean,
    var pageCount: Int? = 0,
    var size: Int? = 0,
    var total: Int? = 0
)

/**
 *
 * @Description:文章
 * @Author: wanglejun
 * @CreateDate：2020/9/30 12:12 AM
 *
 */
data class Article(
    var id: Int? = 0,
    var audit: Int? = 0,
    var author: String,
    var canEdit: Boolean,
    var chapterId: Int? = 0,
    var chapterName: String,
    var collect: Boolean,
    var courseId: Int? = 0,
    var fresh: Boolean,
    var link: String,
    var niceDate: String,
    var niceShareDate: String,
    var publishTime: Long,
    var realSuperChapterId: Int? = 0,
    var shareDate: Long,
    var shareUser: String,
    var title: String,
    var type: Int? = 0,
    var userId: Int? = 0
)