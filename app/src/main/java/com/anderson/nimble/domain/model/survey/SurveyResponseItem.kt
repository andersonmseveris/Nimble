package com.anderson.nimble.domain.model.survey

import com.anderson.nimble.data.model.survey.Question
import com.anderson.nimble.data.model.survey.QuestionData
import com.anderson.nimble.data.model.survey.Relationships
import com.anderson.nimble.data.model.survey.Survey
import com.anderson.nimble.data.model.survey.SurveyAttributes
import com.anderson.nimble.data.model.survey.SurveyResponse

data class SurveyResponseItem(
    val data: List<SurveyItem>,
    val meta: MetaItem
)

data class SurveyItem(
    val id: String,
    val type: String,
    val attributes: SurveyAttributesItem?,
    val relationships: RelationshipsItem?
)

data class SurveyAttributesItem(
    val title: String?,
    val description: String?,
    val thank_email_above_threshold: String?,
    val thank_email_below_threshold: String?,
    val is_active: Boolean?,
    val cover_image_url: String?,
    val created_at: String?,
    val active_at: String?,
    val inactive_at: String?,
    val survey_type: String?
)

data class RelationshipsItem(
    val questions: QuestionDataItem?
)

data class QuestionDataItem(
    val data: List<QuestionItem>?
)

data class QuestionItem(
    val id: String?,
    val type: String?
)

data class MetaItem(
    val page: Int,
    val pages: Int,
    val page_size: Int,
    val records: Int
)

fun SurveyAttributes.toSurveyAttributesItem(): SurveyAttributesItem {
    return SurveyAttributesItem(
        title = this.title,
        description = this.description,
        thank_email_above_threshold = this.thank_email_above_threshold,
        thank_email_below_threshold = this.thank_email_below_threshold,
        is_active = this.is_active,
        cover_image_url = this.cover_image_url,
        created_at = this.created_at,
        active_at = this.active_at,
        inactive_at = this.inactive_at,
        survey_type = this.survey_type
    )
}

fun QuestionData.toQuestionDataItem(): QuestionDataItem {
    return QuestionDataItem(
        data = this.data?.map { it.toQuestionItem() } ?: emptyList()
    )
}

fun Question.toQuestionItem(): QuestionItem {
    return QuestionItem(
        id = this.id,
        type = this.type
    )
}

fun Relationships.toRelationshipsItem(): RelationshipsItem {
    return RelationshipsItem(
        questions = this.questions?.toQuestionDataItem()
    )
}

fun Survey.toSurveyItem(): SurveyItem {
    return SurveyItem(
        id = this.id,
        type = this.type,
        attributes = this.attributes?.toSurveyAttributesItem(),
        relationships = this.relationships?.toRelationshipsItem()
    )
}

fun SurveyResponse.toSurveyResponseItem(): SurveyResponseItem {
    return SurveyResponseItem(
        data = this.data.map { it.toSurveyItem() },
        meta = MetaItem(
            page = this.meta.page,
            pages = this.meta.pages,
            page_size = this.meta.page_size,
            records = this.meta.records
        )
    )
}
