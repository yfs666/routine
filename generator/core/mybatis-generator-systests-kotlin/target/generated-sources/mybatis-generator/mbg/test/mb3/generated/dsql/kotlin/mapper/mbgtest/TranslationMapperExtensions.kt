/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.103+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.mapper.mbgtest

import mbg.test.mb3.generated.dsql.kotlin.mapper.mbgtest.TranslationDynamicSqlSupport.Translation
import mbg.test.mb3.generated.dsql.kotlin.mapper.mbgtest.TranslationDynamicSqlSupport.Translation.id
import mbg.test.mb3.generated.dsql.kotlin.mapper.mbgtest.TranslationDynamicSqlSupport.Translation.translation
import mbg.test.mb3.generated.dsql.kotlin.model.mbgtest.TranslationRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun TranslationMapper.count(completer: CountCompleter) =
    countFrom(this::count, Translation, completer)

fun TranslationMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, Translation, completer)

fun TranslationMapper.deleteByPrimaryKey(id_: Int) =
    delete {
        where(id, isEqualTo(id_))
    }

fun TranslationMapper.insert(record: TranslationRecord) =
    insert(this::insert, record, Translation) {
        map(id).toProperty("id")
        map(translation).toProperty("translation")
    }

fun TranslationMapper.insertMultiple(records: Collection<TranslationRecord>) =
    insertMultiple(this::insertMultiple, records, Translation) {
        map(id).toProperty("id")
        map(translation).toProperty("translation")
    }

fun TranslationMapper.insertMultiple(vararg records: TranslationRecord) =
    insertMultiple(records.toList())

fun TranslationMapper.insertSelective(record: TranslationRecord) =
    insert(this::insert, record, Translation) {
        map(id).toPropertyWhenPresent("id", record::id)
        map(translation).toPropertyWhenPresent("translation", record::translation)
    }

private val columnList = listOf(id, translation)

fun TranslationMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, Translation, completer)

fun TranslationMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, Translation, completer)

fun TranslationMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, Translation, completer)

fun TranslationMapper.selectByPrimaryKey(id_: Int) =
    selectOne {
        where(id, isEqualTo(id_))
    }

fun TranslationMapper.update(completer: UpdateCompleter) =
    update(this::update, Translation, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: TranslationRecord) =
    apply {
        set(id).equalTo(record::id)
        set(translation).equalTo(record::translation)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: TranslationRecord) =
    apply {
        set(id).equalToWhenPresent(record::id)
        set(translation).equalToWhenPresent(record::translation)
    }

fun TranslationMapper.updateByPrimaryKey(record: TranslationRecord) =
    update {
        set(translation).equalTo(record::translation)
        where(id, isEqualTo(record::id))
    }

fun TranslationMapper.updateByPrimaryKeySelective(record: TranslationRecord) =
    update {
        set(translation).equalToWhenPresent(record::translation)
        where(id, isEqualTo(record::id))
    }