/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.088+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.mapper

import mbg.test.mb3.generated.dsql.kotlin.mapper.FieldsonlyDynamicSqlSupport.Fieldsonly
import mbg.test.mb3.generated.dsql.kotlin.mapper.FieldsonlyDynamicSqlSupport.Fieldsonly.doublefield
import mbg.test.mb3.generated.dsql.kotlin.mapper.FieldsonlyDynamicSqlSupport.Fieldsonly.floatfield
import mbg.test.mb3.generated.dsql.kotlin.mapper.FieldsonlyDynamicSqlSupport.Fieldsonly.integerfield
import mbg.test.mb3.generated.dsql.kotlin.model.FieldsonlyRecord
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun FieldsonlyMapper.count(completer: CountCompleter) =
    countFrom(this::count, Fieldsonly, completer)

fun FieldsonlyMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, Fieldsonly, completer)

fun FieldsonlyMapper.insert(record: FieldsonlyRecord) =
    insert(this::insert, record, Fieldsonly) {
        map(integerfield).toProperty("integerfield")
        map(doublefield).toProperty("doublefield")
        map(floatfield).toProperty("floatfield")
    }

fun FieldsonlyMapper.insertMultiple(records: Collection<FieldsonlyRecord>) =
    insertMultiple(this::insertMultiple, records, Fieldsonly) {
        map(integerfield).toProperty("integerfield")
        map(doublefield).toProperty("doublefield")
        map(floatfield).toProperty("floatfield")
    }

fun FieldsonlyMapper.insertMultiple(vararg records: FieldsonlyRecord) =
    insertMultiple(records.toList())

fun FieldsonlyMapper.insertSelective(record: FieldsonlyRecord) =
    insert(this::insert, record, Fieldsonly) {
        map(integerfield).toPropertyWhenPresent("integerfield", record::integerfield)
        map(doublefield).toPropertyWhenPresent("doublefield", record::doublefield)
        map(floatfield).toPropertyWhenPresent("floatfield", record::floatfield)
    }

private val columnList = listOf(integerfield, doublefield, floatfield)

fun FieldsonlyMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, Fieldsonly, completer)

fun FieldsonlyMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, Fieldsonly, completer)

fun FieldsonlyMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, Fieldsonly, completer)

fun FieldsonlyMapper.update(completer: UpdateCompleter) =
    update(this::update, Fieldsonly, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: FieldsonlyRecord) =
    apply {
        set(integerfield).equalTo(record::integerfield)
        set(doublefield).equalTo(record::doublefield)
        set(floatfield).equalTo(record::floatfield)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: FieldsonlyRecord) =
    apply {
        set(integerfield).equalToWhenPresent(record::integerfield)
        set(doublefield).equalToWhenPresent(record::doublefield)
        set(floatfield).equalToWhenPresent(record::floatfield)
    }