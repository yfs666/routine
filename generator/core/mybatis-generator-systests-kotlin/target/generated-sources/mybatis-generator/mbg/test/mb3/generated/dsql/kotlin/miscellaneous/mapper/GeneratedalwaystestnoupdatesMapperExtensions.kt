/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.109+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper

import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.GeneratedalwaystestnoupdatesDynamicSqlSupport.Generatedalwaystestnoupdates
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.GeneratedalwaystestnoupdatesDynamicSqlSupport.Generatedalwaystestnoupdates.id
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.GeneratedalwaystestnoupdatesDynamicSqlSupport.Generatedalwaystestnoupdates.idPlus1
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.GeneratedalwaystestnoupdatesDynamicSqlSupport.Generatedalwaystestnoupdates.idPlus2
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.model.GeneratedalwaystestnoupdatesRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun GeneratedalwaystestnoupdatesMapper.count(completer: CountCompleter) =
    countFrom(this::count, Generatedalwaystestnoupdates, completer)

fun GeneratedalwaystestnoupdatesMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, Generatedalwaystestnoupdates, completer)

fun GeneratedalwaystestnoupdatesMapper.deleteByPrimaryKey(id_: Int) =
    delete {
        where(id, isEqualTo(id_))
    }

fun GeneratedalwaystestnoupdatesMapper.insert(record: GeneratedalwaystestnoupdatesRecord) =
    insert(this::insert, record, Generatedalwaystestnoupdates) {
        map(id).toProperty("id")
    }

fun GeneratedalwaystestnoupdatesMapper.insertMultiple(records: Collection<GeneratedalwaystestnoupdatesRecord>) =
    insertMultiple(this::insertMultiple, records, Generatedalwaystestnoupdates) {
        map(id).toProperty("id")
    }

fun GeneratedalwaystestnoupdatesMapper.insertMultiple(vararg records: GeneratedalwaystestnoupdatesRecord) =
    insertMultiple(records.toList())

fun GeneratedalwaystestnoupdatesMapper.insertSelective(record: GeneratedalwaystestnoupdatesRecord) =
    insert(this::insert, record, Generatedalwaystestnoupdates) {
        map(id).toPropertyWhenPresent("id", record::id)
    }

private val columnList = listOf(id, idPlus1, idPlus2)

fun GeneratedalwaystestnoupdatesMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, Generatedalwaystestnoupdates, completer)

fun GeneratedalwaystestnoupdatesMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, Generatedalwaystestnoupdates, completer)

fun GeneratedalwaystestnoupdatesMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, Generatedalwaystestnoupdates, completer)

fun GeneratedalwaystestnoupdatesMapper.selectByPrimaryKey(id_: Int) =
    selectOne {
        where(id, isEqualTo(id_))
    }

fun GeneratedalwaystestnoupdatesMapper.update(completer: UpdateCompleter) =
    update(this::update, Generatedalwaystestnoupdates, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: GeneratedalwaystestnoupdatesRecord) =
    apply {
        set(id).equalTo(record::id)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: GeneratedalwaystestnoupdatesRecord) =
    apply {
        set(id).equalToWhenPresent(record::id)
    }