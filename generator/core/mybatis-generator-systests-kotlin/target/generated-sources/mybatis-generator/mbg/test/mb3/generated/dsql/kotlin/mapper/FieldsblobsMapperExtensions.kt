/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.101+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.mapper

import mbg.test.mb3.generated.dsql.kotlin.mapper.FieldsblobsDynamicSqlSupport.Fieldsblobs
import mbg.test.mb3.generated.dsql.kotlin.mapper.FieldsblobsDynamicSqlSupport.Fieldsblobs.blob1
import mbg.test.mb3.generated.dsql.kotlin.mapper.FieldsblobsDynamicSqlSupport.Fieldsblobs.blob2
import mbg.test.mb3.generated.dsql.kotlin.mapper.FieldsblobsDynamicSqlSupport.Fieldsblobs.blob3
import mbg.test.mb3.generated.dsql.kotlin.mapper.FieldsblobsDynamicSqlSupport.Fieldsblobs.firstname
import mbg.test.mb3.generated.dsql.kotlin.mapper.FieldsblobsDynamicSqlSupport.Fieldsblobs.lastname
import mbg.test.mb3.generated.dsql.kotlin.model.FieldsblobsRecord
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun FieldsblobsMapper.count(completer: CountCompleter) =
    countFrom(this::count, Fieldsblobs, completer)

fun FieldsblobsMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, Fieldsblobs, completer)

fun FieldsblobsMapper.insert(record: FieldsblobsRecord) =
    insert(this::insert, record, Fieldsblobs) {
        map(firstname).toProperty("firstname")
        map(lastname).toProperty("lastname")
        map(blob1).toProperty("blob1")
        map(blob2).toProperty("blob2")
        map(blob3).toProperty("blob3")
    }

fun FieldsblobsMapper.insertMultiple(records: Collection<FieldsblobsRecord>) =
    insertMultiple(this::insertMultiple, records, Fieldsblobs) {
        map(firstname).toProperty("firstname")
        map(lastname).toProperty("lastname")
        map(blob1).toProperty("blob1")
        map(blob2).toProperty("blob2")
        map(blob3).toProperty("blob3")
    }

fun FieldsblobsMapper.insertMultiple(vararg records: FieldsblobsRecord) =
    insertMultiple(records.toList())

fun FieldsblobsMapper.insertSelective(record: FieldsblobsRecord) =
    insert(this::insert, record, Fieldsblobs) {
        map(firstname).toPropertyWhenPresent("firstname", record::firstname)
        map(lastname).toPropertyWhenPresent("lastname", record::lastname)
        map(blob1).toPropertyWhenPresent("blob1", record::blob1)
        map(blob2).toPropertyWhenPresent("blob2", record::blob2)
        map(blob3).toPropertyWhenPresent("blob3", record::blob3)
    }

private val columnList = listOf(firstname, lastname, blob1, blob2, blob3)

fun FieldsblobsMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, Fieldsblobs, completer)

fun FieldsblobsMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, Fieldsblobs, completer)

fun FieldsblobsMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, Fieldsblobs, completer)

fun FieldsblobsMapper.update(completer: UpdateCompleter) =
    update(this::update, Fieldsblobs, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: FieldsblobsRecord) =
    apply {
        set(firstname).equalTo(record::firstname)
        set(lastname).equalTo(record::lastname)
        set(blob1).equalTo(record::blob1)
        set(blob2).equalTo(record::blob2)
        set(blob3).equalTo(record::blob3)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: FieldsblobsRecord) =
    apply {
        set(firstname).equalToWhenPresent(record::firstname)
        set(lastname).equalToWhenPresent(record::lastname)
        set(blob1).equalToWhenPresent(record::blob1)
        set(blob2).equalToWhenPresent(record::blob2)
        set(blob3).equalToWhenPresent(record::blob3)
    }