/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.104+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.mapper.mbgtest

import mbg.test.mb3.generated.dsql.kotlin.mapper.mbgtest.IdDynamicSqlSupport.Id
import mbg.test.mb3.generated.dsql.kotlin.mapper.mbgtest.IdDynamicSqlSupport.Id.description
import mbg.test.mb3.generated.dsql.kotlin.mapper.mbgtest.IdDynamicSqlSupport.Id.id
import mbg.test.mb3.generated.dsql.kotlin.model.mbgtest.IdRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun IdMapper.count(completer: CountCompleter) =
    countFrom(this::count, Id, completer)

fun IdMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, Id, completer)

fun IdMapper.deleteByPrimaryKey(id_: Int) =
    delete {
        where(id, isEqualTo(id_))
    }

fun IdMapper.insert(record: IdRecord) =
    insert(this::insert, record, Id) {
        map(id).toProperty("id")
        map(description).toProperty("description")
    }

fun IdMapper.insertMultiple(records: Collection<IdRecord>) =
    insertMultiple(this::insertMultiple, records, Id) {
        map(id).toProperty("id")
        map(description).toProperty("description")
    }

fun IdMapper.insertMultiple(vararg records: IdRecord) =
    insertMultiple(records.toList())

fun IdMapper.insertSelective(record: IdRecord) =
    insert(this::insert, record, Id) {
        map(id).toPropertyWhenPresent("id", record::id)
        map(description).toPropertyWhenPresent("description", record::description)
    }

private val columnList = listOf(id, description)

fun IdMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, Id, completer)

fun IdMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, Id, completer)

fun IdMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, Id, completer)

fun IdMapper.selectByPrimaryKey(id_: Int) =
    selectOne {
        where(id, isEqualTo(id_))
    }

fun IdMapper.update(completer: UpdateCompleter) =
    update(this::update, Id, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: IdRecord) =
    apply {
        set(id).equalTo(record::id)
        set(description).equalTo(record::description)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: IdRecord) =
    apply {
        set(id).equalToWhenPresent(record::id)
        set(description).equalToWhenPresent(record::description)
    }

fun IdMapper.updateByPrimaryKey(record: IdRecord) =
    update {
        set(description).equalTo(record::description)
        where(id, isEqualTo(record::id))
    }

fun IdMapper.updateByPrimaryKeySelective(record: IdRecord) =
    update {
        set(description).equalToWhenPresent(record::description)
        where(id, isEqualTo(record::id))
    }