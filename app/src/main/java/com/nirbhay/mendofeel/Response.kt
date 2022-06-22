package com.nirbhay.mendofeel

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("next")
	val next: String? = null,

	@field:SerializedName("previous")
	val previous: Any? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null
)

data class ChoicesItem(

	@field:SerializedName("percentage")
	val percentage: Int? = null,

	@field:SerializedName("choice_text")
	val choiceText: String? = null,

	@field:SerializedName("votes")
	val votes: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class ResultsItem(

	@field:SerializedName("anon")
	val anon: Boolean? = null,

	@field:SerializedName("forumauthor")
	val forumauthor: String? = null,

	@field:SerializedName("is_requestuserpost")
	val isRequestuserpost: Boolean? = null,

	@field:SerializedName("question")
	val question: String? = null,

	@field:SerializedName("profile_photo")
	val profilePhoto: String? = null,

	@field:SerializedName("is_followed")
	val isFollowed: Boolean? = null,

	@field:SerializedName("topcomments")
	val topcomments: List<Any?>? = null,

	@field:SerializedName("commentscount")
	val commentscount: String? = null,

	@field:SerializedName("post_photo")
	val postPhoto: Any? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("is_verified")
	val isVerified: Boolean? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("feed_id")
	val feedId: Int? = null,

	@field:SerializedName("likescount")
	val likescount: Int? = null,

	@field:SerializedName("publishtime")
	val publishtime: String? = null,

	@field:SerializedName("topic")
	val topic: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("fullname")
	val fullname: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("views")
	val views: Int? = null,

	@field:SerializedName("is_liked")
	val isLiked: Boolean? = null,

	@field:SerializedName("author")
	val author: Int? = null,

	@field:SerializedName("can_vote")
	val canVote: Boolean? = null,

	@field:SerializedName("question_text")
	val questionText: String? = null,

	@field:SerializedName("pollauthor")
	val pollauthor: String? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("is_anonymous")
	val isAnonymous: Boolean? = null,

	@field:SerializedName("publish")
	val publish: String? = null,

	@field:SerializedName("authorname")
	val authorname: String? = null,

	@field:SerializedName("choices")
	val choices: List<ChoicesItem?>? = null
)
