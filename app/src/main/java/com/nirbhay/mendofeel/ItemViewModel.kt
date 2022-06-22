package com.nirbhay.mendofeel


data class ItemViewModel(
    val text: String,
    val text2: String,
    val text3: String,
    val img: String,
    val postPic: Any,
    val likeCount: String,
    val commentCount: String,
    val isLiked: String,
    val type: String,
    val choiceList: List<ChoicesItem?>?,

    )
