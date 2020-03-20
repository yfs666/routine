/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.109+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper

import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.GeneratedalwaystestDynamicSqlSupport.Generatedalwaystest
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.GeneratedalwaystestDynamicSqlSupport.Generatedalwaystest.blob1
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.GeneratedalwaystestDynamicSqlSupport.Generatedalwaystest.id
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.GeneratedalwaystestDynamicSqlSupport.Generatedalwaystest.idPlus1
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.GeneratedalwaystestDynamicSqlSupport.Generatedalwaystest.idPlus2
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.GeneratedalwaystestDynamicSqlSupport.Generatedalwaystest.name
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.model.GeneratedalwaystestRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun GeneratedalwaystestMapper.count(completer: CountCompleter) =
    countFrom(this::count, Generatedalwaystest, completer)

fun GeneratedalwaystestMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, Generatedalwaystest, completer)

fun GeneratedalwaystestMapper.deleteByPrimaryKey(id_: Int) =
    delete {
        where(id, isEqualTo(id_))
    }

fun GeneratedalwaystestMapper.insert(record: GeneratedalwaystestRecord) =
    insert(this::insert, record, Generatedalwaystest) {
        map(id).toProperty("id")
        map(name).toProperty("name")
        map(blob1).toProperty("blob1")
    }

fun GeneratedalwaystestMapper.insertMultiple(records: Collection<GeneratedalwaystestRecord>) =
    insertMultiple(this::insertMultiple, records, Generatedalwaystest) {
        map(id).toProperty("id")
        map(name).toProperty("name")
        map(blob1).toProperty("blob1")
    }

fun GeneratedalwaystestMapper.insertMultiple(vararg records: GeneratedalwaystestRecord) =
    insertMultiple(records.toList())

fun GeneratedalwaystestMapper.insertSelective(record: GeneratedalwaystestRecord) =
    insert(this::insert, record, Generatedalwaystest) {
        map(id).toPropertyWhenPresent("id", record::id)
        map(name).toPropertyWhenPresent("name", record::name)
        map(blob1).toPropertyWhenPresent("blob1", record::blob1)
    }

private val columnList = listOf(id, name, idPlus1, idPlus2, blob1)

fun GeneratedalwaystestMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, Generatedalwaystest, completer)

fun GeneratedalwaystestMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, Generatedalwaystest, completer)

fun GeneratedalwaystestMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, Generatedalwaystest, completer)

fun GeneratedalwaystestMapper.selectByPrimaryKey(id_: Int) =
    selectOne {
        where(id, isEqualTo(id_))
    }

fun GeneratedalwaystestMapper.update(completer: UpdateCompleter) =
    update(this::update, Generatedalwaystest, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: GeneratedalwaystestRecord) =
    apply {
        set(id).equalTo(record::id)
        set(name).equalTo(record::name)
        set(blob1).equalTo(record::blob1)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: GeneratedalwaystestRecord) =
    apply {
        set(id).equalToWhenPresent(record::id)
        set(name).equalToWhenPresent(record::name)
        set(blob1).equalToWhenPresent(record::blob1)
    }

fun GeneratedalwaystestMapper.updateByPrimaryKey(record: GeneratedalwaystestRecord) =
    update {
        set(name).equalTo(record::name)
        set(blob1).equalTo(record::blob1)
        where(id, isEqualTo(record::id))
    }

fun GeneratedalwaystestMapper.updateByPrimaryKeySelective(record: GeneratedalwaystestRecord) =
    update {
        set(name).equalToWhenPresent(record::name)
        set(blob1).equalToWhenPresent(record::blob1)
        where(id, isEqualTo(record::id))
    }