/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.109+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper

import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.EnumtestDynamicSqlSupport.Enumtest
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.EnumtestDynamicSqlSupport.Enumtest.id
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.EnumtestDynamicSqlSupport.Enumtest.name
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.model.EnumtestRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun EnumtestMapper.count(completer: CountCompleter) =
    countFrom(this::count, Enumtest, completer)

fun EnumtestMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, Enumtest, completer)

fun EnumtestMapper.deleteByPrimaryKey(id_: Int) =
    delete {
        where(id, isEqualTo(id_))
    }

fun EnumtestMapper.insert(record: EnumtestRecord) =
    insert(this::insert, record, Enumtest) {
        map(id).toProperty("id")
        map(name).toProperty("name")
    }

fun EnumtestMapper.insertMultiple(records: Collection<EnumtestRecord>) =
    insertMultiple(this::insertMultiple, records, Enumtest) {
        map(id).toProperty("id")
        map(name).toProperty("name")
    }

fun EnumtestMapper.insertMultiple(vararg records: EnumtestRecord) =
    insertMultiple(records.toList())

fun EnumtestMapper.insertSelective(record: EnumtestRecord) =
    insert(this::insert, record, Enumtest) {
        map(id).toPropertyWhenPresent("id", record::id)
        map(name).toPropertyWhenPresent("name", record::name)
    }

private val columnList = listOf(id, name)

fun EnumtestMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, Enumtest, completer)

fun EnumtestMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, Enumtest, completer)

fun EnumtestMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, Enumtest, completer)

fun EnumtestMapper.selectByPrimaryKey(id_: Int) =
    selectOne {
        where(id, isEqualTo(id_))
    }

fun EnumtestMapper.update(completer: UpdateCompleter) =
    update(this::update, Enumtest, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: EnumtestRecord) =
    apply {
        set(id).equalTo(record::id)
        set(name).equalTo(record::name)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: EnumtestRecord) =
    apply {
        set(id).equalToWhenPresent(record::id)
        set(name).equalToWhenPresent(record::name)
    }

fun EnumtestMapper.updateByPrimaryKey(record: EnumtestRecord) =
    update {
        set(name).equalTo(record::name)
        where(id, isEqualTo(record::id))
    }

fun EnumtestMapper.updateByPrimaryKeySelective(record: EnumtestRecord) =
    update {
        set(name).equalToWhenPresent(record::name)
        where(id, isEqualTo(record::id))
    }