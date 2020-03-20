/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.106+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper

import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.MyObjectDynamicSqlSupport.MyObject
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.MyObjectDynamicSqlSupport.MyObject.birthDate
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.MyObjectDynamicSqlSupport.MyObject.decimal100field
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.MyObjectDynamicSqlSupport.MyObject.decimal155field
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.MyObjectDynamicSqlSupport.MyObject.decimal60field
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.MyObjectDynamicSqlSupport.MyObject.firstname
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.MyObjectDynamicSqlSupport.MyObject.id1
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.MyObjectDynamicSqlSupport.MyObject.id2
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.MyObjectDynamicSqlSupport.MyObject.lastname
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.MyObjectDynamicSqlSupport.MyObject.startDate
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.MyObjectDynamicSqlSupport.MyObject.stringboolean
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.MyObjectDynamicSqlSupport.MyObject.timefield
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.MyObjectDynamicSqlSupport.MyObject.timestampfield
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.MyObjectDynamicSqlSupport.MyObject.wierdField
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.model.MyObjectRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun MyObjectMapper.count(completer: CountCompleter) =
    countFrom(this::count, MyObject, completer)

fun MyObjectMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, MyObject, completer)

fun MyObjectMapper.deleteByPrimaryKey(id2_: Int, id1_: Int) =
    delete {
        where(id2, isEqualTo(id2_))
        and(id1, isEqualTo(id1_))
    }

fun MyObjectMapper.insert(record: MyObjectRecord) =
    insert(this::insert, record, MyObject) {
        map(id2).toProperty("id2")
        map(id1).toProperty("id1")
        map(firstname).toProperty("firstname")
        map(lastname).toProperty("lastname")
        map(startDate).toProperty("startDate")
        map(timefield).toProperty("timefield")
        map(timestampfield).toProperty("timestampfield")
        map(decimal60field).toProperty("decimal60field")
        map(decimal100field).toProperty("decimal100field")
        map(decimal155field).toProperty("decimal155field")
        map(wierdField).toProperty("wierdField")
        map(birthDate).toProperty("birthDate")
        map(stringboolean).toProperty("stringboolean")
    }

fun MyObjectMapper.insertMultiple(records: Collection<MyObjectRecord>) =
    insertMultiple(this::insertMultiple, records, MyObject) {
        map(id2).toProperty("id2")
        map(id1).toProperty("id1")
        map(firstname).toProperty("firstname")
        map(lastname).toProperty("lastname")
        map(startDate).toProperty("startDate")
        map(timefield).toProperty("timefield")
        map(timestampfield).toProperty("timestampfield")
        map(decimal60field).toProperty("decimal60field")
        map(decimal100field).toProperty("decimal100field")
        map(decimal155field).toProperty("decimal155field")
        map(wierdField).toProperty("wierdField")
        map(birthDate).toProperty("birthDate")
        map(stringboolean).toProperty("stringboolean")
    }

fun MyObjectMapper.insertMultiple(vararg records: MyObjectRecord) =
    insertMultiple(records.toList())

fun MyObjectMapper.insertSelective(record: MyObjectRecord) =
    insert(this::insert, record, MyObject) {
        map(id2).toPropertyWhenPresent("id2", record::id2)
        map(id1).toPropertyWhenPresent("id1", record::id1)
        map(firstname).toPropertyWhenPresent("firstname", record::firstname)
        map(lastname).toPropertyWhenPresent("lastname", record::lastname)
        map(startDate).toPropertyWhenPresent("startDate", record::startDate)
        map(timefield).toPropertyWhenPresent("timefield", record::timefield)
        map(timestampfield).toPropertyWhenPresent("timestampfield", record::timestampfield)
        map(decimal60field).toPropertyWhenPresent("decimal60field", record::decimal60field)
        map(decimal100field).toPropertyWhenPresent("decimal100field", record::decimal100field)
        map(decimal155field).toPropertyWhenPresent("decimal155field", record::decimal155field)
        map(wierdField).toPropertyWhenPresent("wierdField", record::wierdField)
        map(birthDate).toPropertyWhenPresent("birthDate", record::birthDate)
        map(stringboolean).toPropertyWhenPresent("stringboolean", record::stringboolean)
    }

private val columnList = listOf(id2, id1, firstname, lastname, startDate, timefield, timestampfield, decimal60field, decimal100field, decimal155field, wierdField, birthDate, stringboolean)

fun MyObjectMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, MyObject, completer)

fun MyObjectMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, MyObject, completer)

fun MyObjectMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, MyObject, completer)

fun MyObjectMapper.selectByPrimaryKey(id2_: Int, id1_: Int) =
    selectOne {
        where(id2, isEqualTo(id2_))
        and(id1, isEqualTo(id1_))
    }

fun MyObjectMapper.update(completer: UpdateCompleter) =
    update(this::update, MyObject, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: MyObjectRecord) =
    apply {
        set(id2).equalTo(record::id2)
        set(id1).equalTo(record::id1)
        set(firstname).equalTo(record::firstname)
        set(lastname).equalTo(record::lastname)
        set(startDate).equalTo(record::startDate)
        set(timefield).equalTo(record::timefield)
        set(timestampfield).equalTo(record::timestampfield)
        set(decimal60field).equalTo(record::decimal60field)
        set(decimal100field).equalTo(record::decimal100field)
        set(decimal155field).equalTo(record::decimal155field)
        set(wierdField).equalTo(record::wierdField)
        set(birthDate).equalTo(record::birthDate)
        set(stringboolean).equalTo(record::stringboolean)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: MyObjectRecord) =
    apply {
        set(id2).equalToWhenPresent(record::id2)
        set(id1).equalToWhenPresent(record::id1)
        set(firstname).equalToWhenPresent(record::firstname)
        set(lastname).equalToWhenPresent(record::lastname)
        set(startDate).equalToWhenPresent(record::startDate)
        set(timefield).equalToWhenPresent(record::timefield)
        set(timestampfield).equalToWhenPresent(record::timestampfield)
        set(decimal60field).equalToWhenPresent(record::decimal60field)
        set(decimal100field).equalToWhenPresent(record::decimal100field)
        set(decimal155field).equalToWhenPresent(record::decimal155field)
        set(wierdField).equalToWhenPresent(record::wierdField)
        set(birthDate).equalToWhenPresent(record::birthDate)
        set(stringboolean).equalToWhenPresent(record::stringboolean)
    }

fun MyObjectMapper.updateByPrimaryKey(record: MyObjectRecord) =
    update {
        set(firstname).equalTo(record::firstname)
        set(lastname).equalTo(record::lastname)
        set(startDate).equalTo(record::startDate)
        set(timefield).equalTo(record::timefield)
        set(timestampfield).equalTo(record::timestampfield)
        set(decimal60field).equalTo(record::decimal60field)
        set(decimal100field).equalTo(record::decimal100field)
        set(decimal155field).equalTo(record::decimal155field)
        set(wierdField).equalTo(record::wierdField)
        set(birthDate).equalTo(record::birthDate)
        set(stringboolean).equalTo(record::stringboolean)
        where(id2, isEqualTo(record::id2))
        and(id1, isEqualTo(record::id1))
    }

fun MyObjectMapper.updateByPrimaryKeySelective(record: MyObjectRecord) =
    update {
        set(firstname).equalToWhenPresent(record::firstname)
        set(lastname).equalToWhenPresent(record::lastname)
        set(startDate).equalToWhenPresent(record::startDate)
        set(timefield).equalToWhenPresent(record::timefield)
        set(timestampfield).equalToWhenPresent(record::timestampfield)
        set(decimal60field).equalToWhenPresent(record::decimal60field)
        set(decimal100field).equalToWhenPresent(record::decimal100field)
        set(decimal155field).equalToWhenPresent(record::decimal155field)
        set(wierdField).equalToWhenPresent(record::wierdField)
        set(birthDate).equalToWhenPresent(record::birthDate)
        set(stringboolean).equalToWhenPresent(record::stringboolean)
        where(id2, isEqualTo(record::id2))
        and(id1, isEqualTo(record::id1))
    }