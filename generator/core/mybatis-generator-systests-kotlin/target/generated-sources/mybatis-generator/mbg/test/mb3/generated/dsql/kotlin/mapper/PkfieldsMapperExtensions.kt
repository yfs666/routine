/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.098+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.mapper

import mbg.test.mb3.generated.dsql.kotlin.mapper.PkfieldsDynamicSqlSupport.Pkfields
import mbg.test.mb3.generated.dsql.kotlin.mapper.PkfieldsDynamicSqlSupport.Pkfields.birthDate
import mbg.test.mb3.generated.dsql.kotlin.mapper.PkfieldsDynamicSqlSupport.Pkfields.datefield
import mbg.test.mb3.generated.dsql.kotlin.mapper.PkfieldsDynamicSqlSupport.Pkfields.decimal100field
import mbg.test.mb3.generated.dsql.kotlin.mapper.PkfieldsDynamicSqlSupport.Pkfields.decimal155field
import mbg.test.mb3.generated.dsql.kotlin.mapper.PkfieldsDynamicSqlSupport.Pkfields.decimal30field
import mbg.test.mb3.generated.dsql.kotlin.mapper.PkfieldsDynamicSqlSupport.Pkfields.decimal60field
import mbg.test.mb3.generated.dsql.kotlin.mapper.PkfieldsDynamicSqlSupport.Pkfields.firstname
import mbg.test.mb3.generated.dsql.kotlin.mapper.PkfieldsDynamicSqlSupport.Pkfields.id1
import mbg.test.mb3.generated.dsql.kotlin.mapper.PkfieldsDynamicSqlSupport.Pkfields.id2
import mbg.test.mb3.generated.dsql.kotlin.mapper.PkfieldsDynamicSqlSupport.Pkfields.lastname
import mbg.test.mb3.generated.dsql.kotlin.mapper.PkfieldsDynamicSqlSupport.Pkfields.stringboolean
import mbg.test.mb3.generated.dsql.kotlin.mapper.PkfieldsDynamicSqlSupport.Pkfields.timefield
import mbg.test.mb3.generated.dsql.kotlin.mapper.PkfieldsDynamicSqlSupport.Pkfields.timestampfield
import mbg.test.mb3.generated.dsql.kotlin.mapper.PkfieldsDynamicSqlSupport.Pkfields.wierdField
import mbg.test.mb3.generated.dsql.kotlin.model.PkfieldsRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun PkfieldsMapper.count(completer: CountCompleter) =
    countFrom(this::count, Pkfields, completer)

fun PkfieldsMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, Pkfields, completer)

fun PkfieldsMapper.deleteByPrimaryKey(id2_: Int, id1_: Int) =
    delete {
        where(id2, isEqualTo(id2_))
        and(id1, isEqualTo(id1_))
    }

fun PkfieldsMapper.insert(record: PkfieldsRecord) =
    insert(this::insert, record, Pkfields) {
        map(id2).toProperty("id2")
        map(id1).toProperty("id1")
        map(firstname).toProperty("firstname")
        map(lastname).toProperty("lastname")
        map(datefield).toProperty("datefield")
        map(timefield).toProperty("timefield")
        map(timestampfield).toProperty("timestampfield")
        map(decimal30field).toProperty("decimal30field")
        map(decimal60field).toProperty("decimal60field")
        map(decimal100field).toProperty("decimal100field")
        map(decimal155field).toProperty("decimal155field")
        map(wierdField).toProperty("wierdField")
        map(birthDate).toProperty("birthDate")
        map(stringboolean).toProperty("stringboolean")
    }

fun PkfieldsMapper.insertMultiple(records: Collection<PkfieldsRecord>) =
    insertMultiple(this::insertMultiple, records, Pkfields) {
        map(id2).toProperty("id2")
        map(id1).toProperty("id1")
        map(firstname).toProperty("firstname")
        map(lastname).toProperty("lastname")
        map(datefield).toProperty("datefield")
        map(timefield).toProperty("timefield")
        map(timestampfield).toProperty("timestampfield")
        map(decimal30field).toProperty("decimal30field")
        map(decimal60field).toProperty("decimal60field")
        map(decimal100field).toProperty("decimal100field")
        map(decimal155field).toProperty("decimal155field")
        map(wierdField).toProperty("wierdField")
        map(birthDate).toProperty("birthDate")
        map(stringboolean).toProperty("stringboolean")
    }

fun PkfieldsMapper.insertMultiple(vararg records: PkfieldsRecord) =
    insertMultiple(records.toList())

fun PkfieldsMapper.insertSelective(record: PkfieldsRecord) =
    insert(this::insert, record, Pkfields) {
        map(id2).toPropertyWhenPresent("id2", record::id2)
        map(id1).toPropertyWhenPresent("id1", record::id1)
        map(firstname).toPropertyWhenPresent("firstname", record::firstname)
        map(lastname).toPropertyWhenPresent("lastname", record::lastname)
        map(datefield).toPropertyWhenPresent("datefield", record::datefield)
        map(timefield).toPropertyWhenPresent("timefield", record::timefield)
        map(timestampfield).toPropertyWhenPresent("timestampfield", record::timestampfield)
        map(decimal30field).toPropertyWhenPresent("decimal30field", record::decimal30field)
        map(decimal60field).toPropertyWhenPresent("decimal60field", record::decimal60field)
        map(decimal100field).toPropertyWhenPresent("decimal100field", record::decimal100field)
        map(decimal155field).toPropertyWhenPresent("decimal155field", record::decimal155field)
        map(wierdField).toPropertyWhenPresent("wierdField", record::wierdField)
        map(birthDate).toPropertyWhenPresent("birthDate", record::birthDate)
        map(stringboolean).toPropertyWhenPresent("stringboolean", record::stringboolean)
    }

private val columnList = listOf(id2, id1, firstname, lastname, datefield, timefield, timestampfield, decimal30field, decimal60field, decimal100field, decimal155field, wierdField, birthDate, stringboolean)

fun PkfieldsMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, Pkfields, completer)

fun PkfieldsMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, Pkfields, completer)

fun PkfieldsMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, Pkfields, completer)

fun PkfieldsMapper.selectByPrimaryKey(id2_: Int, id1_: Int) =
    selectOne {
        where(id2, isEqualTo(id2_))
        and(id1, isEqualTo(id1_))
    }

fun PkfieldsMapper.update(completer: UpdateCompleter) =
    update(this::update, Pkfields, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: PkfieldsRecord) =
    apply {
        set(id2).equalTo(record::id2)
        set(id1).equalTo(record::id1)
        set(firstname).equalTo(record::firstname)
        set(lastname).equalTo(record::lastname)
        set(datefield).equalTo(record::datefield)
        set(timefield).equalTo(record::timefield)
        set(timestampfield).equalTo(record::timestampfield)
        set(decimal30field).equalTo(record::decimal30field)
        set(decimal60field).equalTo(record::decimal60field)
        set(decimal100field).equalTo(record::decimal100field)
        set(decimal155field).equalTo(record::decimal155field)
        set(wierdField).equalTo(record::wierdField)
        set(birthDate).equalTo(record::birthDate)
        set(stringboolean).equalTo(record::stringboolean)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: PkfieldsRecord) =
    apply {
        set(id2).equalToWhenPresent(record::id2)
        set(id1).equalToWhenPresent(record::id1)
        set(firstname).equalToWhenPresent(record::firstname)
        set(lastname).equalToWhenPresent(record::lastname)
        set(datefield).equalToWhenPresent(record::datefield)
        set(timefield).equalToWhenPresent(record::timefield)
        set(timestampfield).equalToWhenPresent(record::timestampfield)
        set(decimal30field).equalToWhenPresent(record::decimal30field)
        set(decimal60field).equalToWhenPresent(record::decimal60field)
        set(decimal100field).equalToWhenPresent(record::decimal100field)
        set(decimal155field).equalToWhenPresent(record::decimal155field)
        set(wierdField).equalToWhenPresent(record::wierdField)
        set(birthDate).equalToWhenPresent(record::birthDate)
        set(stringboolean).equalToWhenPresent(record::stringboolean)
    }

fun PkfieldsMapper.updateByPrimaryKey(record: PkfieldsRecord) =
    update {
        set(firstname).equalTo(record::firstname)
        set(lastname).equalTo(record::lastname)
        set(datefield).equalTo(record::datefield)
        set(timefield).equalTo(record::timefield)
        set(timestampfield).equalTo(record::timestampfield)
        set(decimal30field).equalTo(record::decimal30field)
        set(decimal60field).equalTo(record::decimal60field)
        set(decimal100field).equalTo(record::decimal100field)
        set(decimal155field).equalTo(record::decimal155field)
        set(wierdField).equalTo(record::wierdField)
        set(birthDate).equalTo(record::birthDate)
        set(stringboolean).equalTo(record::stringboolean)
        where(id2, isEqualTo(record::id2))
        and(id1, isEqualTo(record::id1))
    }

fun PkfieldsMapper.updateByPrimaryKeySelective(record: PkfieldsRecord) =
    update {
        set(firstname).equalToWhenPresent(record::firstname)
        set(lastname).equalToWhenPresent(record::lastname)
        set(datefield).equalToWhenPresent(record::datefield)
        set(timefield).equalToWhenPresent(record::timefield)
        set(timestampfield).equalToWhenPresent(record::timestampfield)
        set(decimal30field).equalToWhenPresent(record::decimal30field)
        set(decimal60field).equalToWhenPresent(record::decimal60field)
        set(decimal100field).equalToWhenPresent(record::decimal100field)
        set(decimal155field).equalToWhenPresent(record::decimal155field)
        set(wierdField).equalToWhenPresent(record::wierdField)
        set(birthDate).equalToWhenPresent(record::birthDate)
        set(stringboolean).equalToWhenPresent(record::stringboolean)
        where(id2, isEqualTo(record::id2))
        and(id1, isEqualTo(record::id1))
    }