package com.anderson.nimble.data.model.survey

data class SurveyResponse(
    val data: List<Survey>,
    val meta: Meta
)

data class Survey(
    val id: String,
    val type: String,
    val attributes: SurveyAttributes,
    val relationships: Relationships
)

data class SurveyAttributes(
    val title: String,
    val description: String,
    val thank_email_above_threshold: String,
    val thank_email_below_threshold: String,
    val is_active: Boolean,
    val cover_image_url: String,
    val created_at: String,
    val active_at: String,
    val inactive_at: String,
    val survey_type: String
)

data class Relationships(
    val questions: QuestionData
)

data class QuestionData(
    val data: List<Question>
)

data class Question(
    val id: String,
    val type: String
)

data class Meta(
    val page: Int,
    val pages: Int,
    val page_size: Int,
    val records: Int
)