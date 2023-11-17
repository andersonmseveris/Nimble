package com.anderson.nimble.data.model.survey
data class SurveyDetailsResponse(
    val data: SurveyItem,
    val included: List<QuestionItem>
)

data class SurveyItem(
    val id: String,
    val type: String,
    val attributes: SurveyDetailsAttributes,
    val relationships: SurveyRelationships
)

data class SurveyDetailsAttributes(
    val title: String,
    val description: String,
    val thank_email_above_threshold: String,
    val thank_email_below_threshold: String,
    val is_active: Boolean,
    val cover_image_url: String,
    val created_at: String,
    val active_at: String,
    val inactive_at: String?,
    val survey_type: String
)

data class SurveyRelationships(
    val questions: QuestionDetailsData
)

data class QuestionDetailsData(
    val data: List<QuestionDetails>
)

data class QuestionDetails(
    val id: String,
    val type: String
)

data class QuestionItem(
    val id: String,
    val type: String,
    val attributes: QuestionAttributes,
    val relationships: QuestionRelationships
)

data class QuestionAttributes(
    val text: String,
    val help_text: String?,
    val display_order: Int,
    val short_text: String,
    val pick: String,
    val display_type: String,
    val is_mandatory: Boolean,
    val correct_answer_id: String?,
    val facebook_profile: String?,
    val twitter_profile: String?,
    val image_url: String?,
    val cover_image_url: String?,
    val cover_image_opacity: Double,
    val cover_background_color: String?,
    val is_shareable_on_facebook: Boolean,
    val is_shareable_on_twitter: Boolean,
    val font_face: String?,
    val font_size: String?,
    val tag_list: String
)

data class QuestionRelationships(
    val answers: AnswerData
)

data class AnswerData(
    val data: List<Answer>
)

data class Answer(
    val id: String,
    val type: String,
    val attributes: AnswerAttributes
)

data class AnswerAttributes(
    val text: String,
    val help_text: String?,
    val input_mask_placeholder: String?,
    val short_text: String,
    val is_mandatory: Boolean,
    val is_customer_first_name: Boolean,
    val is_customer_last_name: Boolean,
    val is_customer_title: Boolean,
    val is_customer_email: Boolean,
    val prompt_custom_answer: Boolean,
    val weight: Any?,
    val display_order: Int,
    val display_type: String,
    val input_mask: String?,
    val date_constraint: Any?,
    val default_value: Any?,
    val response_class: String,
    val reference_identifier: Any?,
    val score: Any?,
    val alerts: List<Any>
)